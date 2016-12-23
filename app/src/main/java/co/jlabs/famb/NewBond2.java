package co.jlabs.famb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import co.jlabs.famb.Rounded.CircularImageView;


public class NewBond2 extends AppCompatActivity implements View.OnClickListener {
    
   // private ArrayList<String> ar;
    private ImageView back;
    private TextView up,ppl_num;
    private RelativeLayout action_bar;
    private CircularImageView bond_img;
    private RelativeLayout upper;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RelativeLayout activity_add_ppl;
    private EditText bondname;
    private CoordinatorLayout coordinatorLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_new_bond2);
        initView();
//        Bundle extras = getIntent().getExtras();

//        if (extras != null) {

//        }
    }

    private void initView() {
        ArrayList<String> ar = getIntent().getStringArrayListExtra("ar");
        ArrayList<Integer> arInt = getIntent().getIntegerArrayListExtra("arInt");
        Log.e(ar.size()+"","dats");
        back = (ImageView) findViewById(R.id.back);
        up = (TextView) findViewById(R.id.up);
        ppl_num = (TextView) findViewById(R.id.ppl_num);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        bondname = (EditText) findViewById(R.id.bond_name);
        action_bar = (RelativeLayout) findViewById(R.id.action_bar);
        bond_img = (CircularImageView) findViewById(R.id.bond_img);
        upper = (RelativeLayout) findViewById(R.id.upper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        activity_add_ppl = (RelativeLayout) findViewById(R.id.activity_add_ppl);
        fab.setOnClickListener(this);
        ppl_num.setText(ar.size()+"/50");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(ar,arInt));
    }

    private static class RecyclerViewAdapter extends RecyclerView.Adapter<FakeViewHolder> {


        private ArrayList<String> mCustomObjects;
        private ArrayList<Integer> arInts;




        public RecyclerViewAdapter(ArrayList<String> ar,ArrayList<Integer> arInt) {
            mCustomObjects=ar;
            arInts=arInt;


        }
        @Override
        public int getItemCount() {
            return mCustomObjects.size();
        }

        @Override
        public FakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FakeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adap_add_ppl, parent, false));
        }

        @Override
        public void onBindViewHolder(final FakeViewHolder holder, final int position) {
            //holder.imageView.setImageResource(drawables[position % 3]);
            holder.name_ppl.setText(mCustomObjects.get(position));
            holder.imageView.setImageResource(arInts.get(position));

        }


    }
    private static class FakeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name_ppl;
        Button add;
        public FakeViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_ppl);
            name_ppl = (TextView) itemView.findViewById(R.id.name_ppl);
            add = (Button) itemView.findViewById(R.id.add);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                String bondnm=bondname.getText().toString().trim();
                if(TextUtils.isEmpty(bondnm)){
                    Log.e("hi","ssa");
                    Snackbar.make(coordinatorLayout, "Give your bond a name", Snackbar.LENGTH_LONG).show();

                }else     Log.e("his","ssa");

                break;
        }
    }
    public void submit(View v){
        String bondnm=bondname.getText().toString().trim();
        if(TextUtils.isEmpty(bondnm)){
            Snackbar.make(v, "Give your bond a name", Snackbar.LENGTH_LONG).show();
        }

    }
}
