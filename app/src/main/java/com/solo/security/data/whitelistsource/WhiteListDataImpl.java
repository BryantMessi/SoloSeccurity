package com.solo.security.data.whitelistsource;

import android.content.Context;

import com.google.common.base.Preconditions;

/**
 * Created by Messi on 16-11-4.
 */

public class WhiteListDataImpl implements WhiteListData {

    private static WhiteListDataImpl sInstance;
    private Context mContext;

    private WhiteListDataImpl(Context context) {
        mContext = Preconditions.checkNotNull(context, "Context is null");
    }

    public static WhiteListDataImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WhiteListDataImpl(context);
        }
        return sInstance;
    }

    @Override
    public void loadWhiteList(WhiteListDataCallback callback) {

    }
}
