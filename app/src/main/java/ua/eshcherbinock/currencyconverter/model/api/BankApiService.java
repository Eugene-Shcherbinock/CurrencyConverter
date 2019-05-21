package ua.eshcherbinock.currencyconverter.model.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ua.eshcherbinock.currencyconverter.common.Configuration;
import ua.eshcherbinock.currencyconverter.model.entity.currency.response.CurrencyResponse;

public interface BankApiService {

    @GET(Configuration.Api.EXCHANGE)
    Call<List<CurrencyResponse>> getCurrencyExchangeRates();

}
