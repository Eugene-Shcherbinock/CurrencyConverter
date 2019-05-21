package ua.eshcherbinock.currencyconverter.model.entity.currency.response;

import com.google.gson.annotations.SerializedName;

public class CurrencyResponse {

    @SerializedName("r030")
    private int mCurrencyId;

    @SerializedName("txt")
    private String mCurrencyName;

    @SerializedName("cc")
    private String mCurrencyCode;

    @SerializedName("rate")
    private float mCurrencyRate;

    public int getCurrencyId() {
        return mCurrencyId;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public float getCurrencyRate() {
        return mCurrencyRate;
    }

}
