package com.solo.security.data.safesource;

import android.content.Context;

import com.google.common.base.Preconditions;

/**
 * Created by Messi on 16-11-4.
 */

public class SafeDataImpl implements SafeData {

    private static SafeDataImpl sInstance;
    private Context mContext;

    private SafeDataImpl(Context context) {
        mContext = Preconditions.checkNotNull(context, "Context is null");
    }

    public static SafeDataImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SafeDataImpl(context);
        }
        return sInstance;
    }

    @Override
    public void cloudSafeScan(BaseSafeCallback callback) {

    }

    @Override
    public void fixUnSafeApp(BaseSafeCallback callback) {

    }
}
