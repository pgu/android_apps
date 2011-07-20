package pgu.standup;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

public class TextViewState {

    private final TextView textView;
    private final int colorDefault;
    private final float textSizeDefault;
    private boolean isActive;

    public TextViewState(final int id, final Activity a) {
        textView = (TextView) a.findViewById(id);
        colorDefault = textView.getCurrentTextColor();
        textSizeDefault = textView.getTextSize();
    }

    public void reset() {
        isActive = false;
        textView.setTextColor(colorDefault);
        textView.setTextSize(textSizeDefault);
    }

    public boolean isActive() {
        return isActive;
    }

    public TextView getTextView() {
        return textView;
    }

    public void highlight() {
        isActive = true;
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(textSizeDefault + 10);
    }

}
