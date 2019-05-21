package ua.eshcherbinock.currencyconverter.module.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivity <P extends BaseModuleContracts.Presenter> extends Activity
        implements BaseModuleContracts.View<P> {

    // Properties

    protected P mPresenter;

    protected ProgressDialog mProgressHUD;

    // Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        configureModule();

        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutId());

        updateViewDependencies();

        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showLoading(String message) {
        mProgressHUD.setTitle(message);
        mProgressHUD.show();
    }

    @Override
    public void hideLoading() {
        mProgressHUD.dismiss();
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    protected void updateViewDependencies() {
        mProgressHUD = new ProgressDialog(getContext());
        mProgressHUD.setCancelable(false);
        mProgressHUD.setIndeterminate(true);
    }

    abstract protected void configureModule();

    abstract protected int getContentViewLayoutId();

}
