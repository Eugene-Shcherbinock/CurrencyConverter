package ua.eshcherbinock.currencyconverter.provider.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.eshcherbinock.currencyconverter.common.Configuration;

public class ApiProvider implements ApiProviderType {

    private Retrofit mRetrofit;

    public ApiProvider() {
        initializeRetrofitInstance();
    }

    public Retrofit getRetrofitInstance() {
        return mRetrofit;
    }

    @Override
    public <S> S provideApiService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    private void initializeRetrofitInstance() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Configuration.Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
