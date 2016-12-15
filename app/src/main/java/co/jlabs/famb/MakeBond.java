package co.jlabs.famb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import co.jlabs.famb.frag.AnimationSet;
import co.jlabs.famb.frag.TabFragment;
import co.jlabs.famb.frag.TabStacker;

public class MakeBond extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView closeDrawer;
    private static final String TAG = "MainActivity";

    private TabStacker mTabStacker;
    private AnimationSet mAddAnimation = new Anims.SlideFromBottom();
    private AnimationSet mReplaceAnimation = new Anims.SlideFromRight();
    private enum Tab {
        TAB_A(R.id.tabA, R.color.colorBot),
        TAB_B(R.id.tabB, R.color.colorBot),
        TAB_C(R.id.tabC, R.color.colorBot);

        private int mButtonResId;
        private int mColor;

        Tab(@IdRes int buttonResId, @ColorRes int color) {
            mButtonResId = buttonResId;
            mColor = color;
        }
    }
    private void onClickOnTab(Tab clickedTab) {

        Log.i(TAG, "switch to tab " + clickedTab.name());

        // Update Button state (white / black)
        for(final Tab tab : Tab.values()) {
            View button = findViewById(tab.mButtonResId);
            button.setSelected((tab == clickedTab));
        }

        // switch to Tab Stack
        String tabName = clickedTab.name();
        if (!mTabStacker.switchToTab(tabName)) {    // switch to the new Tab Stack
            // the stack is empty -> push the 1st fragment to the stack
            TabFragment fragment = createFragment();
            mTabStacker.replaceFragment(fragment, null);
        }

    }



    private TabFragment createFragment() {
        String tabName = mTabStacker.getCurrentTabName();
        int number = mTabStacker.getCurrentTabSize();
        String title = "Fragment " + tabName + " " + number;
        int color = getResources().getColor(Tab.valueOf(tabName).mColor);
        color &= 0x00FFFFFF;
        color |= 0x7F000000;
        TabFragment fragment = TabFragment.createInstance(title, color);
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_bond);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mTabStacker = new TabStacker(getSupportFragmentManager(), R.id.fragmentHolder);
        if (savedInstanceState == null) {
            // new Activity: select the first Tab
            onClickOnTab(Tab.TAB_A);
        } else {
            // restoring Activity: restore the TabStacker, and select the saved selected tab
            mTabStacker.restoreInstance(savedInstanceState);
            Tab selectedTab = Tab.valueOf(mTabStacker.getCurrentTabName());
            onClickOnTab(selectedTab);
        }

        for (final Tab tab : Tab.values()) {
            final ImageView button = (ImageView) findViewById(tab.mButtonResId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickOnTab(tab);
                }
            });
        }





       final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) drawer.findViewById(R.id.recyclerView);
        closeDrawer = (ImageView) drawer.findViewById(R.id.close_drawer);
        closeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapter(1));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) navigationView.getLayoutParams();
        params.width = metrics.widthPixels;
        navigationView.setLayoutParams(params);
    }

    private static class RecyclerViewAdapter extends RecyclerView.Adapter<FakeViewHolder> {

        int[] drawables;
        int[] text;
        int[] notif_count;






        public RecyclerViewAdapter(int index) {

            if (index==1) {
                drawables = new int[] {
                        R.drawable.settings,
                        R.drawable.calendar,
                        R.drawable.fambond_white,
                        R.drawable.vault,
                        R.drawable.contacts,
                        R.drawable.feeds,
                        R.drawable.polls,
                        R.drawable.events,
                        R.drawable.tasks
                };
                text = new int[] {
                        R.string.settings,
                        R.string.calendar,
                        R.string.bonds,
                        R.string.vault,
                        R.string.contacts,
                        R.string.feed,
                        R.string.polls,
                        R.string.events,
                        R.string.tasks

                };
                notif_count = new int[] {
                        R.string.settings1,
                        R.string.calendar1,
                        R.string.bonds1,
                        R.string.vault1,
                        R.string.contacts1,
                        R.string.feed1,
                        R.string.polls1,
                        R.string.events1,
                        R.string.tasks1
                };
            }

        }

        @Override
        public FakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FakeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_dashboard, parent, false));
        }

        @Override
        public void onBindViewHolder(final FakeViewHolder holder, final int position) {
            holder.imageView.setImageResource(drawables[position]);
            holder.text.setText(text[position]);
            holder.notif_count.setText(notif_count[position]);
            holder.grid.setOnClickListener(new View.OnClickListener() {
                Intent intent;
                @Override
                public void onClick(View v) {
                    switch (position){

                        case 6: /** Start a new Activity MyCards.java */
                             intent = new Intent(v.getContext(), NewPoll.class);
                            v.getContext().startActivity(intent);
                            break;
                        case 8: /** Start a new Activity MyCards.java */
                             intent = new Intent(v.getContext(), NewTask.class);
                            v.getContext().startActivity(intent);
                            break;

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return 9;
        }
    }
    private static class FakeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView text,notif_count;
        RelativeLayout grid;
        public FakeViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            text = (TextView) itemView.findViewById(R.id.text);
            grid = (RelativeLayout) itemView.findViewById(R.id.grid);
            notif_count = (TextView) itemView.findViewById(R.id.notif_count);


        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (!mTabStacker.onBackPressed()) {
            super.onBackPressed();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }



    public void restoreView(Fragment fragment, View view) {
        mTabStacker.restoreView(fragment, view);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // (Keep this first) Saves the TabStacker instance
        mTabStacker.saveInstance(outState);

        super.onSaveInstanceState(outState);
    }


}
