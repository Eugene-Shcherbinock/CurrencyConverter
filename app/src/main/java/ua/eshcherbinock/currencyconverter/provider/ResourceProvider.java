package ua.eshcherbinock.currencyconverter.provider;

import android.content.Context;

public final class ResourceProvider {

    private Context mContext;

    public ResourceProvider(Context context) {
        mContext = context;
    }

    public String getString(int stringResourceId) {
        return mContext.getString(stringResourceId);
    }

}
