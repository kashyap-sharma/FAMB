package co.jlabs.famb.chatBox;

import android.graphics.Bitmap;

/**
 * Created by JLabs on 06/09/15.
 */
public class ChatMessage {
    private boolean isImage, isMine;
    private String content;
    private Bitmap contents;

    public ChatMessage(String message, boolean mine, boolean image) {
        content = message;
        isMine = mine;
        isImage = image;
    }
    public ChatMessage(Bitmap message, boolean mine, boolean image) {
        contents = message;
        isMine = mine;
        isImage = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Bitmap getContents() {
        return contents;
    }

    public void setContents(Bitmap contents) {
        this.contents = contents;
    }



    public boolean isMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }
}
