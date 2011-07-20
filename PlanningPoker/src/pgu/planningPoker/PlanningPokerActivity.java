package pgu.planningPoker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PlanningPokerActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.title);

        ButtonHelper.clickCard(R.id.card01, "1", this);
        ButtonHelper.clickCard(R.id.card02, "2", this);
        ButtonHelper.clickCard(R.id.card03, "3", this);
        ButtonHelper.clickCard(R.id.card05, "5", this);
        ButtonHelper.clickCard(R.id.card08, "8", this);
        ButtonHelper.clickCard(R.id.card13, "13", this);
        ButtonHelper.clickCard(R.id.card21, "21", this);

        findViewById(R.id.reset).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                ((TextView) findViewById(R.id.selected)).setText("");
            }
        });

    }
}