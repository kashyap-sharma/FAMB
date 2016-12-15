package co.jlabs.famb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import co.jlabs.famb.otp.OtpView;

public class Splash extends AppCompatActivity implements View.OnClickListener {


    private ImageView icon;
    private EditText cont_code;
    private EditText contact;
    private Button signup;
    private TextView terms;
    private TextView text1;
    private TextView text2;
    private RelativeLayout phone_screen;
    private Button submit;
    private TextView sitback;
    private TextView or;
    private OtpView otpview;
    private RelativeLayout otp;
    private TextView at;
    private EditText username;
    private EditText password;
    private EditText retype;
    private Button submit_user;
    private RelativeLayout userscreen;
    private LinearLayout activity_splash;
    public static int splash_time = 3000;
    Context context;
    Animation move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        initView();


    }


    private void initView() {
        move = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        icon = (ImageView) findViewById(R.id.icon);
        cont_code = (EditText) findViewById(R.id.cont_code);
        contact = (EditText) findViewById(R.id.contact);
        signup = (Button) findViewById(R.id.signup);
        terms = (TextView) findViewById(R.id.terms);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        phone_screen = (RelativeLayout) findViewById(R.id.phone_screen);
        submit = (Button) findViewById(R.id.submit);
        sitback = (TextView) findViewById(R.id.sitback);
        or = (TextView) findViewById(R.id.or);
        otpview = (OtpView) findViewById(R.id.otpview);
        otp = (RelativeLayout) findViewById(R.id.otp);
        at = (TextView) findViewById(R.id.at);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        retype = (EditText) findViewById(R.id.retype);
        submit_user = (Button) findViewById(R.id.submit_user);
        userscreen = (RelativeLayout) findViewById(R.id.userscreen);
        activity_splash = (LinearLayout) findViewById(R.id.activity_splash);
        contact.setMaxEms(10);
        contact.addTextChangedListener(passwordWatcher);
        signup.setOnClickListener(this);
        submit.setOnClickListener(this);
        submit_user.setOnClickListener(this);
        TranslateAnimation slide = new TranslateAnimation(0, 0, 100,0 );
        slide.setDuration(500);
        slide.setFillAfter(true);
        icon.startAnimation(slide);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                phone_screen.setVisibility(View.VISIBLE);
            }
        }, splash_time);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                submit();
                break;
            case R.id.submit:
                submit1();
                break;
            case R.id.submit_user:
                submit2();
                break;
        }
    }


    private void submit() {
        // validate


        String contactString = contact.getText().toString().trim();
        if (TextUtils.isEmpty(contactString)) {
            Toast.makeText(this, "Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        phone_screen.setVisibility(View.GONE);
        otp.setVisibility(View.VISIBLE);

        // TODO validate success, do something


    }


    private void submit1() {
        // validate


        Log.e("some",otpview.getOTP());

        String otpM = otpview.getOTP();
        if (TextUtils.getTrimmedLength(otpM)<4) {
            Toast.makeText(this, "OTP invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        otp.setVisibility(View.GONE);
        userscreen.setVisibility(View.VISIBLE);



        // TODO validate success, do something


    }
    private void submit2() {


        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "usernameString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "passwordString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        String retypeString = retype.getText().toString().trim();
        if (TextUtils.isEmpty(retypeString)) {
            Toast.makeText(this, "passwordString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
        Intent intent =new Intent(this, Prof.class);
        startActivity(intent);
    }
    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 10) {
                InputMethodManager inputManager =
                        (InputMethodManager) context.
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    };
}
