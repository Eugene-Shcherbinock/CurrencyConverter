package ua.eshcherbinock.currencyconverter.module.exchanger;

import android.support.annotation.Nullable;

import java.util.List;

import ua.eshcherbinock.currencyconverter.model.entity.currency.Currency;
import ua.eshcherbinock.currencyconverter.module.base.BaseModuleContracts;

public interface ExchangerModuleContracts {

    interface View <P extends Presenter> extends BaseModuleContracts.View <P> {

        void setupCurrencies(List<Currency> currencies);

    }

    interface Presenter <V extends View> extends BaseModuleContracts.Presenter <V> {

        void fetchCurrencies();

    }

    interface Model extends BaseModuleContracts.Model {

        void fetchCurrencies(CurrenciesFetchCallback callback);

        interface CurrenciesFetchCallback {

            void onReceived(@Nullable List<Currency> currencies, @Nullable Error error);

        }

    }

}
