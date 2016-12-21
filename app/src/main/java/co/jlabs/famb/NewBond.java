package co.jlabs.famb;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

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
    private List<Models> mModelList;
    RecyclerView.Adapter mAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
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
        mAdapter=new RecyclerViewAdapter(getListData(),this);

        recyclerView.setAdapter(mAdapter);




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

    private List<Models> getListData() {
        mModelList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            mModelList.add(new Models("TextView " + i));
        }
        return mModelList;
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

    public  void onMycall(final ArrayList<String> ar) {
        Log.e("meda:"+ar.size(),"");
        if (ar.size()>0){
            fab.setVisibility(View.VISIBLE);
            ppl_num.setText(ar.size()+" of 9 selected");

        }else {
            fab.setVisibility(View.GONE);
            ppl_num.setText("Add People");
        }
        try {
            for(int i=0;i<=ar.size();i++ ){
                Log.e("meda"+ar.size(),""+ ar.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewBond2.class);
                intent.putExtra("ar", ar);
                startActivity(intent);

            }
        });

    }

}
