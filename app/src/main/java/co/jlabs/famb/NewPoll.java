package co.jlabs.famb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewPoll extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView n_poll;
    private RelativeLayout header;
    private EditText poll_subject;
    private EditText add_note;
    private TextView enter;
    private EditText op1;
    private RelativeLayout option1;
    private EditText op2;
    private RelativeLayout option2;
    private TextView bar;
    private Button add_more;
    private LinearLayout activity_new_poll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poll);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        n_poll = (TextView) findViewById(R.id.n_poll);
        header = (RelativeLayout) findViewById(R.id.header);
        poll_subject = (EditText) findViewById(R.id.poll_subject);
        add_note = (EditText) findViewById(R.id.add_note);
        enter = (TextView) findViewById(R.id.enter);
        op1 = (EditText) findViewById(R.id.op1);
        option1 = (RelativeLayout) findViewById(R.id.option1);
        op2 = (EditText) findViewById(R.id.op2);
        option2 = (RelativeLayout) findViewById(R.id.option2);
        bar = (TextView) findViewById(R.id.bar);
        add_more = (Button) findViewById(R.id.add_more);
        activity_new_poll = (LinearLayout) findViewById(R.id.activity_new_poll);

        add_more.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_more:

                break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    private void submit() {
        // validate
        String subject = poll_subject.getText().toString().trim();
        if (TextUtils.isEmpty(subject)) {
            Toast.makeText(this, "Enter poll subject", Toast.LENGTH_SHORT).show();
            return;
        }

        String note = add_note.getText().toString().trim();
        if (TextUtils.isEmpty(note)) {
            Toast.makeText(this, "Add note", Toast.LENGTH_SHORT).show();
            return;
        }

        String op1String = op1.getText().toString().trim();
        if (TextUtils.isEmpty(op1String)) {
            Toast.makeText(this, "Option 1", Toast.LENGTH_SHORT).show();
            return;
        }

        String op2String = op2.getText().toString().trim();
        if (TextUtils.isEmpty(op2String)) {
            Toast.makeText(this, "Option 2", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
