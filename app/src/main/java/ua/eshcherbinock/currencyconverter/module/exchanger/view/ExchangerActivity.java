package ua.eshcherbinock.currencyconverter.module.exchanger.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import ua.eshcherbinock.currencyconverter.R;
import ua.eshcherbinock.currencyconverter.model.entity.currency.Currency;
import ua.eshcherbinock.currencyconverter.module.base.BaseActivity;
import ua.eshcherbinock.currencyconverter.module.exchanger.ExchangerModuleConfigurator;
import ua.eshcherbinock.currencyconverter.module.exchanger.ExchangerModuleContracts;
import ua.eshcherbinock.currencyconverter.module.exchanger.helper.EditTextChangeListener;
import ua.eshcherbinock.currencyconverter.module.exchanger.view.adapter.CurrenciesAdapter;

public class ExchangerActivity extends BaseActivity <ExchangerModuleContracts.Presenter>
        implements ExchangerModuleContracts.View <ExchangerModuleContracts.Presenter> {

    private EditText mEditTextValue;
    private EditText mEditTextResult;

    private CurrenciesAdapter mCurrenciesAdapter;

    private Spinner mSpinnerExchangingCurrency;
    private Spinner mSpinnerResultCurrency;

    @Override
    protected void configureModule() {
        ExchangerModuleConfigurator configurator = new ExchangerModuleConfigurator(this);
        configurator.configure();
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_exchanger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.fetchCurrencies();
    }

    @Override
    public void setupCurrencies(List<Currency> currencies) {
        mCurrenciesAdapter.setupCurrencies(currencies);
        mCurrenciesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showExchangeResult(float result) {
        mEditTextResult.setText(Float.toString(result));
    }

    @Override
    protected void updateViewDependencies() {
        super.updateViewDependencies();

        mEditTextValue = findViewById(R.id.edit_text_exchange_value);
        mEditTextResult = findViewById(R.id.edit_text_exchange_result);

        mSpinnerExchangingCurrency = findViewById(R.id.spinner_exchanging_currency);
        mSpinnerResultCurrency = findViewById(R.id.spinner_result_currency);

        mCurrenciesAdapter = new CurrenciesAdapter(getContext());
        mSpinnerExchangingCurrency.setAdapter(mCurrenciesAdapter);
        mSpinnerResultCurrency.setAdapter(mCurrenciesAdapter);

        mEditTextValue.setText("1.0");

        new EditTextChangeListener()
                .addEditText(mEditTextValue)
                .setCallback(new CurrencyFieldChangeListener());

        AdapterView.OnItemSelectedListener onCurrencySelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exchange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        mSpinnerResultCurrency.setOnItemSelectedListener(onCurrencySelectedListener);
        mSpinnerExchangingCurrency.setOnItemSelectedListener(onCurrencySelectedListener);
    }

    private void exchange() {
        int fromCurrencyIndex = mSpinnerExchangingCurrency.getSelectedItemPosition();
        int toCurrencyIndex = mSpinnerResultCurrency.getSelectedItemPosition();

        float value = Float.valueOf(mEditTextValue.getText().toString());
        mPresenter.exchange(value, fromCurrencyIndex, toCurrencyIndex);
    }

    private class CurrencyFieldChangeListener implements EditTextChangeListener.Callback {

        @Override
        public void onEditTextChanged(EditText editText, String newText) {
            if (newText.isEmpty()) {
                return;
            }
            exchange();
        }

    }

}
