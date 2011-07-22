package pgu.pert.ui;

import pgu.pert.presenter.ResetPresenter;
import android.view.View;
import android.widget.Button;

public class ButtonReset {

    private final Button resetBtn;
    private final ResetPresenter presenter;

    public ButtonReset(final Button btn, final ResetPresenter a) {
        presenter = a;
        resetBtn = btn;
        setClickAction();
    }

    private void setClickAction() {
        resetBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View arg0) {
                resetScreen();
            }

            private void resetScreen() {
                presenter.resetOptimistic();
                presenter.resetNominal();
                presenter.resetPessimistic();
                presenter.resetResult();
                presenter.resetFocusOnFirstField();
            }
        });
    }

}
