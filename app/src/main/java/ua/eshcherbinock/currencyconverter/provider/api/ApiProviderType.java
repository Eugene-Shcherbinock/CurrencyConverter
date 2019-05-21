package ua.eshcherbinock.currencyconverter.provider.api;

public interface ApiProviderType {

    <S> S provideApiService(Class<S> serviceClass);

}
