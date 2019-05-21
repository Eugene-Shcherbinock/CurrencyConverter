package ua.eshcherbinock.currencyconverter.module.exchanger.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.eshcherbinock.currencyconverter.R;
import ua.eshcherbinock.currencyconverter.model.entity.currency.Currency;

public final class CurrenciesAdapter extends ArrayAdapter<Currency> {

    private List<Currency> mCurrencies;
    private List<Currency> mFilteredCurrencies;

    private Filter mCurrenciesFilter;

    public CurrenciesAdapter(Context context, List<Currency> currencies) {
        super(context, R.layout.currency_list_item, currencies);

        mCurrencies = currencies;
        mFilteredCurrencies = new ArrayList<>(currencies);
    }

    @Override
    public Filter getFilter() {
        return new CurrenciesFilter();
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

    private class CurrenciesFilter extends Filter {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Currency currency = (Currency) resultValue;
            return currency.getCode() + " " + currency.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("FILTER", "Constraint: " + constraint);
            if (constraint != null) {
                mFilteredCurrencies.clear();
                for (Currency currency : mCurrencies) {
                    if (currency.getCode().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        mFilteredCurrencies.add(currency);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredCurrencies;
                filterResults.count = mFilteredCurrencies.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Currency> c = (ArrayList<Currency>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Currency cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }  else {
                clear();
                notifyDataSetChanged();
            }
        }

    }

}
