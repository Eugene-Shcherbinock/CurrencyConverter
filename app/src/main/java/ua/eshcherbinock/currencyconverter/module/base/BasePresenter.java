package ua.eshcherbinock.currencyconverter.module.base;

import java.lang.ref.WeakReference;

public class BasePresenter <V extends BaseModuleContracts.View, M extends BaseModuleContracts.Model>
        implements BaseModuleContracts.Presenter <V> {

    protected WeakReference<V> mView;
    protected M mModel;

    protected BasePresenter(V view, M model) {
        mView = new WeakReference<>(view);
        mModel = model;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        mView.clear();
        mView = null;
    }

}
