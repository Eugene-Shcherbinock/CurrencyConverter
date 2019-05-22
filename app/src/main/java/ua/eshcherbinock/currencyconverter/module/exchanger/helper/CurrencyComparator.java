package ua.eshcherbinock.currencyconverter.module.exchanger.helper;

import java.util.Comparator;

import ua.eshcherbinock.currencyconverter.model.entity.currency.Currency;

public final class CurrencyComparator implements Comparator<Currency> {

    @Override
    public int compare(Currency o1, Currency o2) {
        return o2.getCode().compareTo(o1.getCode());
    }

}
