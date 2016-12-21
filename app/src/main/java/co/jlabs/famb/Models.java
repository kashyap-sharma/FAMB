package co.jlabs.famb;

/**
 * Created by JLabs on 12/21/16.
 */

public class Models {
    private String text;
    private boolean isSelected = false;

    public Models(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}
