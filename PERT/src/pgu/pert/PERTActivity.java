package pgu.pert;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PERTActivity extends Activity {

    private static final String TAG = "PERTActivity";

    private EditText optimistic;
    private EditText nominal;
    private EditText pessimistic;
    private TextView result;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.title);

        optimistic = (EditText) findViewById(R.id.optimistic);
        nominal = (EditText) findViewById(R.id.nominal);
        pessimistic = (EditText) findViewById(R.id.pessimistic);
        result = (TextView) findViewById(R.id.result);

        final Button calculateBtn = (Button) findViewById(R.id.calculate);
        calculateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                final Float opt = Float.parseFloat(optimistic.getText().toString());
                final Float nom = Float.parseFloat(nominal.getText().toString());
                final Float pes = Float.parseFloat(pessimistic.getText().toString());

                final Float res = (opt + nom * 4 + pes) / 6;

                Log.i(TAG, "opt=" + opt);
                Log.i(TAG, "nom=" + nom);
                Log.i(TAG, "pes=" + pes);
                Log.i(TAG, "res=" + res);

                result.setText("" + res);
            }
        });

        final Button resetBtn = (Button) findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View arg0) {
                optimistic.setText("");
                nominal.setText("");
                pessimistic.setText("");
                result.setText("");
            }
        });

    }
}