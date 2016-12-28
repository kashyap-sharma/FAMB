package co.jlabs.famb.activityArea;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import co.jlabs.famb.R;
import co.jlabs.famb.Rounded.CircularImageView;
import co.jlabs.famb.chatBox.ChatMessage;
import co.jlabs.famb.chatBox.ChatMessageAdapter;
import co.jlabs.famb.fonts.ButtonFont;
import co.jlabs.famb.fonts.TextView_White;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;


public class ChatBox extends Activity implements View.OnClickListener {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private TextView_White back;
    private CircularImageView bond_img;
    private TextView bond_name;
    private TextView members;
    private TextView_White settings,textF,galleryF,cameraF,giphyF,voiceF;
    private TextView_White attachment;
    private TextView_White call;
    private RelativeLayout action_bar;
    private ListView listView;
    private LinearLayout tabs;
    private ImageView imgs;
    private EmojiconEditText et_message;
    private ButtonFont send;
    private View contentRoot,voice_sbtn;
    private ButtonFont voice_btn;
    private LinearLayout contentRootVoice;
    private RelativeLayout send_message_layout;
    private RelativeLayout content_chat_box;
    private ChatMessageAdapter mAdapter;
    private EmojIconActions emojIcon;
    private String userChoosenTask;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        context=this;
        Log.e("onRequestPermissionsRes","2");

        initView();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    Toast.makeText(getApplicationContext(), "Some Permissions Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initView() {
        back = (TextView_White) findViewById(R.id.back);
        bond_img = (CircularImageView) findViewById(R.id.bond_img);
        bond_name = (TextView) findViewById(R.id.bond_name);
        members = (TextView) findViewById(R.id.members);
        settings = (TextView_White) findViewById(R.id.settings);
        textF = (TextView_White) findViewById(R.id.textF);
        galleryF = (TextView_White) findViewById(R.id.galleryF);
        cameraF = (TextView_White) findViewById(R.id.cameraF);
        giphyF = (TextView_White) findViewById(R.id.giphyF);
        voiceF = (TextView_White) findViewById(R.id.voiceF);
        attachment = (TextView_White) findViewById(R.id.attachment);
        call = (TextView_White) findViewById(R.id.call);
        action_bar = (RelativeLayout) findViewById(R.id.action_bar);
        listView = (ListView) findViewById(R.id.listView);
        tabs = (LinearLayout) findViewById(R.id.tabs);
        imgs = (ImageView) findViewById(R.id.imgs);
        et_message = (EmojiconEditText) findViewById(R.id.et_message);
        send = (ButtonFont) findViewById(R.id.send);
        contentRoot = (View) findViewById(R.id.chatBox);
        voice_sbtn = (View) findViewById(R.id.voice_sbtn);
       // voice_btn = (ButtonFont) findViewById(R.id.voice_btn);
        contentRootVoice = (LinearLayout) findViewById(R.id.contentRootVoice);
        send_message_layout = (RelativeLayout) findViewById(R.id.send_message_layout);
        content_chat_box = (RelativeLayout) findViewById(R.id.content_chat_box);
        emojIcon = new EmojIconActions(this,contentRoot,et_message,imgs);
        emojIcon.ShowEmojIcon();
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        listView.setAdapter(mAdapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setStackFromBottom(true);
        et_message.addTextChangedListener(edited);
        send.setOnClickListener(this);
        textF.setOnClickListener(this);
        galleryF.setOnClickListener(this);
        cameraF.setOnClickListener(this);
        giphyF.setOnClickListener(this);
        voiceF.setOnClickListener(this);

        //textF,galleryF,cameraF,giphyF,voiceF
       // voice_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("onRequestPermissionsRes","3");

        boolean result= Utility.checkPermission(context);
        switch (v.getId()) {
            case R.id.send:
                submit();
                break;
            case R.id.textF:
                voice_sbtn.setVisibility(View.GONE);
                contentRoot.setVisibility(View.VISIBLE);

                break;
            case R.id.galleryF:
              //  selectImage();
                userChoosenTask ="Choose from Library";
                if(result)
                    galleryIntent();
                break;
            case R.id.cameraF:
                userChoosenTask ="Take Photo";
                if(result)
                    cameraIntent();
                break;
            case R.id.giphyF:

                break;
            case R.id.voiceF:
                voice_sbtn.setVisibility(View.VISIBLE);
                contentRoot.setVisibility(View.GONE);

                break;
        }
    }




    private void submit() {
        // validate
        Log.e("onRequestPermissionsRes","4");

        String message = et_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "message不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        sendMessage(message);
        et_message.setText("");
        // TODO validate success, do something


    }



    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);

        mimicOtherMessage(message);
    }
    private void sendMessage(Bitmap message) {
        ChatMessage chatMessage = new ChatMessage(message, true, true);
        mAdapter.add(chatMessage);
        mimicOtherMessage(message);
    }

    private void mimicOtherMessage(Bitmap message) {
        ChatMessage chatMessage = new ChatMessage(message, false, true);
        mAdapter.add(chatMessage);
    }
    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
    }
    TextWatcher edited=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(count>0){
                send.setText(getString(R.string.send));
            }else  send.setText(getString(R.string.mic));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void galleryIntent()
    {        Log.e("onRequestPermissionsRes","5");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

    }

    private void cameraIntent()
    {        Log.e("onRequestPermissionsRes","6");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage(thumbnail);
        //ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sendMessage(bm);
    }






}


