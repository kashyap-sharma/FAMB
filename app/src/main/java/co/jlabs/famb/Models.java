package co.jlabs.famb;

/**
 * Created by JLabs on 12/21/16.
 */

public class Models {
    private String text;
    private int pic;
    private boolean isSelected = false;

    public Models(String text, int pic) {
        this.text = text;
        this.pic = pic;
    }

    public String getText() {
        return text;
    }
    public int getPic() {
        return pic;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}
