package ua.eshcherbinock.currencyconverter.module.exchanger;

import ua.eshcherbinock.currencyconverter.module.exchanger.model.ExchangerModel;
import ua.eshcherbinock.currencyconverter.module.exchanger.presenter.ExchangerPresenter;
import ua.eshcherbinock.currencyconverter.module.exchanger.view.ExchangerActivity;
import ua.eshcherbinock.currencyconverter.provider.api.ApiProvider;

public final class ExchangerModuleConfigurator {

    private ExchangerActivity mActivity;

    public ExchangerModuleConfigurator(ExchangerActivity activity) {
        mActivity = activity;
    }

    public void configure() {
        ExchangerModuleContracts.View view = mActivity;
        ExchangerModuleContracts.Model model = new ExchangerModel(new ApiProvider());
        ExchangerModuleContracts.Presenter presenter = new ExchangerPresenter(view, model);
        view.setPresenter(presenter);
    }

}
