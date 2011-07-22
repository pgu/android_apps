package pgu.pert.activity;

import java.text.DecimalFormat;

import pgu.pert.R;
import pgu.pert.presenter.CalculatorPERTPresenter;
import pgu.pert.presenter.ResetPresenter;
import pgu.pert.presenter.ResultPERTPresenter;
import pgu.pert.ui.ButtonCalculatePert;
import pgu.pert.ui.ButtonReset;
import pgu.pert.ui.EditEstimation;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PERTActivity extends Activity implements ResetPresenter, CalculatorPERTPresenter, ResultPERTPresenter {

    private EditEstimation optimistic;
    private EditEstimation nominal;
    private EditEstimation pessimistic;
    private TextView result;
    private ButtonCalculatePert btnCalculatePert;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.title);

        optimistic = new EditEstimation((EditText) get(R.id.optimistic), this);
        nominal = new EditEstimation((EditText) get(R.id.nominal), this);
        pessimistic = new EditEstimation((EditText) get(R.id.pessimistic), this);
        result = (TextView) findViewById(R.id.result);

        btnCalculatePert = new ButtonCalculatePert((Button) get(R.id.calculate), this);
        new ButtonReset((Button) get(R.id.reset), this);

    }

    private View get(final int id) {
        return findViewById(id);
    }

    @Override
    public void updateBtnCalculatePert() {
        if (!optimistic.isEmpty() && !pessimistic.isEmpty() && !nominal.isEmpty()) {
            btnCalculatePert.enable();
        } else {
            btnCalculatePert.disable();
        }
    }

    @Override
    public void setResult(final Float r) {
        result.setText(decimalFormat.format(r));
    }

    @Override
    public void resetResult() {
        result.setText("0");
    }

    @Override
    public void resetOptimistic() {
        optimistic.reset();
    }

    @Override
    public void resetNominal() {
        nominal.reset();
    }

    @Override
    public void resetPessimistic() {
        pessimistic.reset();
    }

    @Override
    public Float getOptimisticValue() {
        return optimistic.getValueFloat();
    }

    @Override
    public Float getNominalValue() {
        return nominal.getValueFloat();
    }

    @Override
    public Float getPessimisticValue() {
        return pessimistic.getValueFloat();
    }

    @Override
    public void resetFocusOnFirstField() {
        optimistic.focus();
    }

}