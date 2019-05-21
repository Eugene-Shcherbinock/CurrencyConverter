package ua.eshcherbinock.currencyconverter.module.exchanger.helper;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public final class EditTextChangeListener {

    @Nullable
    private Callback mCallback;

    public EditTextChangeListener addEditText(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mCallback != null) {
                    mCallback.onEditTextChanged(editText, s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        return this;
    }

    public EditTextChangeListener setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    public interface Callback {

        void onEditTextChanged(EditText editText, String newText);

    }

}
