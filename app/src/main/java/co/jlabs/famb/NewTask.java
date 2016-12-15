package co.jlabs.famb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewTask extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView n_poll;
    private RelativeLayout header;
    private EditText task_name;
    private EditText assign;
    private EditText add_note;
    private TextView enter;
    private TextView op1;
    private RelativeLayout option1;
    private TextView op2;
    private RelativeLayout option2;
    private TextView op3;
    private RelativeLayout option3;
    private TextView bar;
    private TextView dt;
    private TextView date_enter;
    private RelativeLayout date;
    private TextView time_edit;
    private RelativeLayout time;
    private ImageView done;
    private LinearLayout activity_new_poll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        n_poll = (TextView) findViewById(R.id.n_poll);
        header = (RelativeLayout) findViewById(R.id.header);
        task_name = (EditText) findViewById(R.id.task_name);
        assign = (EditText) findViewById(R.id.assign);
        add_note = (EditText) findViewById(R.id.add_note);
        enter = (TextView) findViewById(R.id.enter);
        op1 = (TextView) findViewById(R.id.op1);
        option1 = (RelativeLayout) findViewById(R.id.option1);
        op2 = (TextView) findViewById(R.id.op2);
        option2 = (RelativeLayout) findViewById(R.id.option2);
        op3 = (TextView) findViewById(R.id.op3);
        option3 = (RelativeLayout) findViewById(R.id.option3);
        bar = (TextView) findViewById(R.id.bar);
        dt = (TextView) findViewById(R.id.dt);
        date_enter = (TextView) findViewById(R.id.date_enter);
        date = (RelativeLayout) findViewById(R.id.date);
        time_edit = (TextView) findViewById(R.id.time_edit);
        time = (RelativeLayout) findViewById(R.id.time);
        done = (ImageView) findViewById(R.id.done);
        activity_new_poll = (LinearLayout) findViewById(R.id.activity_new_poll);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    private void submit() {
        // validate
        String name = task_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Your task here", Toast.LENGTH_SHORT).show();
            return;
        }

        String assignString = assign.getText().toString().trim();
        if (TextUtils.isEmpty(assignString)) {
            Toast.makeText(this, "Assign to someone", Toast.LENGTH_SHORT).show();
            return;
        }

        String note = add_note.getText().toString().trim();
        if (TextUtils.isEmpty(note)) {
            Toast.makeText(this, "Add note", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
