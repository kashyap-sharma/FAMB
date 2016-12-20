package co.jlabs.famb;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import co.jlabs.famb.Rounded.CircularImageView;
import co.jlabs.famb.checkBox.CircleCheckBox;

public class NewBond extends AppCompatActivity implements ShareInf, View.OnClickListener {

    private ImageView back;
    private TextView up;
    private TextView ppl_num;
    private ImageView search;
    private RelativeLayout action_bar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RelativeLayout activity_add_ppl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        up = (TextView) findViewById(R.id.up);
        ppl_num = (TextView) findViewById(R.id.ppl_num);
        search = (ImageView) findViewById(R.id.search);
        action_bar = (RelativeLayout) findViewById(R.id.action_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        activity_add_ppl = (RelativeLayout) findViewById(R.id.activity_add_ppl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(1, this));
        fab.setOnClickListener(this);
        search.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                break;
            case R.id.search:

                break;
            case R.id.back:

                break;
        }
    }



    private static class RecyclerViewAdapter extends RecyclerView.Adapter<FakeViewHolder> {

        int[] drawables;
        public ShareInf mAdapterCallback;
        int[] names;





        public RecyclerViewAdapter(int index,ShareInf mAdapterCallback) {
            this.mAdapterCallback = mAdapterCallback;
            if (index==1) {
                drawables = new int[] {
                        R.drawable.plant1,
                        R.drawable.plant2,
                        R.drawable.plant3
                };
                names = new int[] {
                        R.string.name1,
                        R.string.name2,
                        R.string.name3
                };
            }

        }

        @Override
        public FakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FakeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adap_add_ppl, parent, false));
        }

        @Override
        public void onBindViewHolder(final FakeViewHolder holder, final int position) {
            holder.imageView.setImageResource(drawables[position % 3]);
            holder.name_ppl.setText(names[position % 3]);
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( holder.circleCheckBox.getVisibility()==View.VISIBLE){
                        holder.circleCheckBox.setVisibility(View.GONE);
                        holder.add.setBackgroundColor(Color.parseColor("#175E7B"));
                    }
                      else  {
                        holder.circleCheckBox.setVisibility(View.VISIBLE);
                        //holder.circleCheckBox.performClick();
                        holder.add.setBackgroundColor(Color.parseColor("#216480"));
                        holder.circleCheckBox.setChecked(true);
                    }


                    //mAdapterCallback.onMethodCallback(holder.name_ppl.getText().toString());

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
        TextView name_ppl;
        RelativeLayout add;
        CircleCheckBox circleCheckBox;
        public FakeViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_ppl);
            name_ppl = (TextView) itemView.findViewById(R.id.name_ppl);
            add = (RelativeLayout) itemView.findViewById(R.id.papa);
            circleCheckBox = (CircleCheckBox) itemView.findViewById(R.id.circle_check_box);

        }
    }
    public  void onMethodCallback(String i) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        LinearLayout whatsapp=(LinearLayout)dialog.findViewById(R.id.whatsapp);
        TextView w=(TextView) dialog.findViewById(R.id.w);
        w.setText(i);
        LinearLayout sms=(LinearLayout)dialog.findViewById(R.id.sms);
        whatsapp.setOnClickListener(this);
        sms.setOnClickListener(this);

        dialog.show();


    }
}
