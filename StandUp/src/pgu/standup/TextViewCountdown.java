package pgu.standup;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

public class TextViewCountdown {

    private final TextView textView;
    private final int colorDefault;

    public TextViewCountdown(final int id, final Activity a) {
        textView = (TextView) a.findViewById(id);
        colorDefault = textView.getCurrentTextColor();
    }

    public void reset() {
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(colorDefault);
        textView.setText(R.string.countdown);
    }

    public TextView getTextView() {
        return textView;
    }

    public void update(final int seconds) {
        textView.setText(seconds + "s");

        int color;
        if (10 < seconds) {
            color = Color.GREEN;
        } else if (5 < seconds) {
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
        textView.setTextColor(color);

        if (5 == seconds) {
            textView.setBackgroundColor(Color.YELLOW);
        }
    }

}
