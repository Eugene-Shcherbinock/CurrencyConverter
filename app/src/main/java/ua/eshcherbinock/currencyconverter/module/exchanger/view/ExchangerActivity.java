package ua.eshcherbinock.currencyconverter.module.exchanger.view;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

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

    private AutoCompleteTextView mTextViewExchangingCurrency;
    private AutoCompleteTextView mTextViewResultCurrency;

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
        setContentView(R.layout.activity_exchanger);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.fetchCurrencies();
    }

    @Override
    public void setupCurrencies(List<Currency> currencies) {

    }

    @Override
    protected void updateViewDependencies() {
        super.updateViewDependencies();

        mEditTextValue = findViewById(R.id.edit_text_exchange_value);
        mEditTextResult = findViewById(R.id.edit_text_exchange_result);

        mTextViewExchangingCurrency = findViewById(R.id.text_view_exchanging_currency);
        mTextViewResultCurrency = findViewById(R.id.text_view_result_currency);

        mTextViewExchangingCurrency.setThreshold(1);
        mTextViewResultCurrency.setThreshold(1);

        new EditTextChangeListener()
                .addEditText(mEditTextResult)
                .addEditText(mEditTextValue)
                .setCallback(new CurrencyFieldChangeListener());
    }

    private class CurrencyFieldChangeListener implements EditTextChangeListener.Callback {

        @Override
        public void onEditTextChanged(EditText editText, String newText) {

        }

    }

}
