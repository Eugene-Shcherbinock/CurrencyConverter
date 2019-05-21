package ua.eshcherbinock.currencyconverter.module.base;

import android.content.Context;

public interface BaseModuleContracts {

    interface View <P extends Presenter> {

        Context getContext();

        void showLoading(String message);
        void hideLoading();

        void showError(String message);

        void setPresenter(P presenter);

    }

    interface Presenter <V extends View> {

        void onCreate();
        void onDestroy();

    }

    interface Model {}

}
