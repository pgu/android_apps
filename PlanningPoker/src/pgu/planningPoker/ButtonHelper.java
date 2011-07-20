package pgu.planningPoker;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public final class ButtonHelper {

    private ButtonHelper() {
        throw new UnsupportedOperationException();
    }

    public static void clickCard(final int id, final String value, final Activity a) {
        a.findViewById(id).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                ((TextView) a.findViewById(R.id.selected)).setText("" + value);
            }
        });
    }

}
