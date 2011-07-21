package pgu.pert.ui;

import pgu.pert.presenter.CalculatorPERTPresenter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ButtonCalculatePert {

    private final Button calculateBtn;
    private final CalculatorPERTPresenter presenter;

    public ButtonCalculatePert(final Button btn, final CalculatorPERTPresenter a) {
        calculateBtn = btn;
        presenter = a;

        disable();
        setClickAction();
    }

    private void setClickAction() {
        calculateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                final Float result = calculatePert();
                displayResult(result);
            }

            private void displayResult(final Float result) {
                presenter.setResult(result);
            }

            private Float calculatePert() {
                final Float opt = presenter.getOptimisticValue();
                final Float nom = presenter.getNominalValue();
                final Float pes = presenter.getPessimisticValue();

                if (hasValidInputForCalculation(opt, nom, pes)) {
                    final Float res = (opt + nom * 4 + pes) / 6;

                    Log.i(ButtonCalculatePert.class.getName(), "opt=" + opt);
                    Log.i(ButtonCalculatePert.class.getName(), "nom=" + nom);
                    Log.i(ButtonCalculatePert.class.getName(), "pes=" + pes);
                    Log.i(ButtonCalculatePert.class.getName(), "res=" + res);

                    return res;
                } else {
                    return 0f;
                }

            }

            private boolean hasValidInputForCalculation(final Float opt, final Float nom, final Float pes) {
                return 0 != opt && 0 != nom && 0 != pes;
            }
        });

    }

    public void disable() {
        calculateBtn.setEnabled(false);
    }

    public void enable() {
        calculateBtn.setEnabled(true);
    }

}