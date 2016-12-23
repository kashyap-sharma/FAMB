package co.jlabs.famb;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextView;

import java.util.ArrayList;

public class AddPpl extends AppCompatActivity implements ShareInf,View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter rc;
    private TextView skip;
    private LinearLayout activity_add_ppl;
    public static final String ARG_INDEX = "indexArg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ppl);
        initView();
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



    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        activity_add_ppl = (LinearLayout) findViewById(R.id.activity_add_ppl);
        skip = (TextView) findViewById(R.id.skip);
        AddPpl sf = new AddPpl();
        skip.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(1, this));
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
                        R.string.plant1,
                        R.string.plant2,
                        R.string.plant3
                };
            }

        }

        @Override
        public FakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FakeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adap_invite_ppl, parent, false));
        }

        @Override
        public void onBindViewHolder(final FakeViewHolder holder, final int position) {
            holder.imageView.setImageResource(drawables[position % 3]);
            holder.name_ppl.setText(names[position % 3]);
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        mAdapterCallback.onMethodCallback(holder.name_ppl.getText().toString());

                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
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
            case R.id.whatsapp:

                submit();
                break;
            case R.id.sms:
                submit1();
                break;
            case R.id.skip:
                Intent intent=new Intent(this, MakeBond.class);
                startActivity(intent);
                break;
        }
    }

    public void submit(){
        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "Download Fambond its awesome.";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);
        }
    }

    public void submit1(){


    }
    public  void onMycall(ArrayList<String> ar,ArrayList<Integer> ars) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        LinearLayout whatsapp=(LinearLayout)dialog.findViewById(R.id.whatsapp);
        TextView w=(TextView) dialog.findViewById(R.id.w);
        //w.setText(i);
        LinearLayout sms=(LinearLayout)dialog.findViewById(R.id.sms);
        whatsapp.setOnClickListener(this);
        sms.setOnClickListener(this);

        dialog.show();


    }
}
