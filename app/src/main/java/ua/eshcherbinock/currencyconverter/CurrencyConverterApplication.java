package ua.eshcherbinock.currencyconverter;

import android.app.Application;
import android.content.Context;

public class CurrencyConverterApplication extends Application {

    private static Context sContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

}
