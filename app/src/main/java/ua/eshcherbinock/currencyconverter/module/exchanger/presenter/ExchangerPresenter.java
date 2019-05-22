package ua.eshcherbinock.currencyconverter.module.exchanger.presenter;

import ua.eshcherbinock.currencyconverter.R;
import ua.eshcherbinock.currencyconverter.module.base.BasePresenter;
import ua.eshcherbinock.currencyconverter.module.exchanger.ExchangerModuleContracts;
import ua.eshcherbinock.currencyconverter.provider.ResourceProvider;

public class ExchangerPresenter extends BasePresenter <ExchangerModuleContracts.View, ExchangerModuleContracts.Model>
        implements ExchangerModuleContracts.Presenter<ExchangerModuleContracts.View> {

    private ResourceProvider mResourceProvider;

    public ExchangerPresenter(ExchangerModuleContracts.View view, ExchangerModuleContracts.Model model) {
        super(view, model);
        mResourceProvider = new ResourceProvider(view.getContext());
    }

    @Override
    public void fetchCurrencies() {
        mView.get().showLoading(mResourceProvider.getString(R.string.fetching));
        mModel.fetchCurrencies((currencies, error) -> {
            mView.get().hideLoading();

            if (error != null) {
                mView.get().showError(error.getLocalizedMessage());
                return;
            }

            mView.get().setupCurrencies(currencies);
        });
    }

    @Override
    public void exchange(float value, int fromCurrencyIndex, int toCurrencyIndex) {
        float result = mModel.exchange(value, fromCurrencyIndex, toCurrencyIndex);
        mView.get().showExchangeResult(result);
    }

}
