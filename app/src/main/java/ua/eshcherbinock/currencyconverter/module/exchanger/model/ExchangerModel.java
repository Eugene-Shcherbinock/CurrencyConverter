package ua.eshcherbinock.currencyconverter.module.exchanger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.eshcherbinock.currencyconverter.model.api.BankApiService;
import ua.eshcherbinock.currencyconverter.model.entity.currency.Currency;
import ua.eshcherbinock.currencyconverter.model.entity.currency.response.CurrencyResponse;
import ua.eshcherbinock.currencyconverter.module.base.BaseModel;
import ua.eshcherbinock.currencyconverter.module.exchanger.ExchangerModuleContracts;
import ua.eshcherbinock.currencyconverter.provider.api.ApiProviderType;

public final class ExchangerModel extends BaseModel implements ExchangerModuleContracts.Model {

    private BankApiService mBankApiService;

    private List<Currency> mCachedCurrencies;

    public ExchangerModel(ApiProviderType apiProviderType) {
        mCachedCurrencies = new ArrayList<>();
        mBankApiService = apiProviderType.provideApiService(BankApiService.class);
    }

    @Override
    public void fetchCurrencies(final CurrenciesFetchCallback callback) {
        if (!mCachedCurrencies.isEmpty()) {
            callback.onReceived(mCachedCurrencies, null);
            return;
        }

        mBankApiService.getCurrencyExchangeRates().enqueue(new Callback<List<CurrencyResponse>>() {
            @Override
            public void onResponse(Call<List<CurrencyResponse>> call, Response<List<CurrencyResponse>> response) {
                if (!response.isSuccessful()) {
                    callback.onReceived(null, new Error(response.message()));
                    return;
                }

                response.body().forEach(currencyResponse -> {
                    Currency currency = new Currency(currencyResponse);
                    mCachedCurrencies.add(currency);
                });

                callback.onReceived(mCachedCurrencies, null);
            }

            @Override
            public void onFailure(Call<List<CurrencyResponse>> call, Throwable t) {
                callback.onReceived(null, new Error(t));
            }
        });
    }

}
