package pgu.pert.ui;

import pgu.pert.presenter.ResultPERTPresenter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditEstimation {

    private final EditText field;

    public EditEstimation(final EditText f, final ResultPERTPresenter presenter) {
        field = f;
        field.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                presenter.updateBtnCalculatePert();
            }
        });
    }

    public Float getValueFloat() {
        return isEmpty() ? 0f : Float.parseFloat(field.getText().toString());
    }

    public void reset() {
        field.setText("");
    }

    public boolean isEmpty() {
        return field.length() == 0;
    }

}
