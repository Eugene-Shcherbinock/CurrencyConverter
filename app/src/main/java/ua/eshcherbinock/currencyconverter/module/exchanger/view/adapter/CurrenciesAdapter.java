package ua.eshcherbinock.currencyconverter.module.exchanger.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.eshcherbinock.currencyconverter.R;
import ua.eshcherbinock.currencyconverter.model.entity.currency.Currency;

public final class CurrenciesAdapter extends BaseAdapter {

    private Context mContext;
    private List<Currency> mCurrencies;

    public CurrenciesAdapter(Context context) {
        mContext = context;
        mCurrencies = new ArrayList<>();
    }

    public void setupCurrencies(List<Currency> currencies) {
        mCurrencies = new ArrayList<>(currencies);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCurrencies.size();
    }

    @Override
    public Currency getItem(int position) {
        return mCurrencies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_list_item, parent, false);
        }

        Currency currency = getItem(position);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.bindWith(currency);

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextViewCode;
        private TextView mTextViewName;

        ViewHolder(View view) {
            mTextViewCode = view.findViewById(R.id.text_view_currency_code);
            mTextViewName = view.findViewById(R.id.text_view_currency_name);
        }

        void bindWith(Currency currency) {
            mTextViewCode.setText(currency.getCode());
            mTextViewName.setText(currency.getName());
        }
    }

}
