package ua.eshcherbinock.currencyconverter.model.entity.currency;

import android.support.annotation.Nullable;

import ua.eshcherbinock.currencyconverter.model.entity.currency.response.CurrencyResponse;

public final class Currency {

    private String mName;
    private String mCode;

    private float mRate;

    public Currency(@Nullable CurrencyResponse response) {
        if (response == null) {
            return;
        }

        mName = response.getCurrencyName();
        mCode = response.getCurrencyCode();
        mRate = response.getCurrencyRate();
    }

    public String getName() {
        return mName;
    }

    public String getCode() {
        return mCode;
    }

    public float getRate() {
        return mRate;
    }

}
