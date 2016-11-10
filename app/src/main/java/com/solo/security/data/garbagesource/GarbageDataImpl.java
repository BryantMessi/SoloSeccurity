package com.solo.security.data.garbagesource;

import android.content.Context;

import com.google.common.base.Preconditions;

/**
 * Created by Messi on 16-11-4.
 */

public class GarbageDataImpl implements GarbageData {

    private static GarbageDataImpl sInstance;
    private Context mContext;

    private GarbageDataImpl(Context context) {
        mContext = Preconditions.checkNotNull(context, "Context is null");
    }

    public static GarbageDataImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GarbageDataImpl(context);
        }
        return sInstance;
    }

    @Override
    public void loadCurrentGarbageSize(BaseGarbageCallback callback) {
        callback.onCurrentGarbageSize();
    }

    @Override
    public void loadAdFiles(BaseGarbageCallback callback) {
        callback.onAdFilesLoaded();
    }

    @Override
    public void loadCacheFiles(BaseGarbageCallback callback) {
        callback.onCacheFilesLoaded();
    }

    @Override
    public void loadTempFiles(BaseGarbageCallback callback) {
        callback.onTempFilesLoaded();
    }

    @Override
    public void loadResidualFiles(DeepGarbageCallback callback) {
        callback.onResidualFilesLoaded();
    }

    @Override
    public void loadMemoryFiles(DeepGarbageCallback callback) {
        callback.onMemoryFilesLoaded();
    }

    @Override
    public void loadInstalledPackages(DeepGarbageCallback callback) {
        callback.onInstalledPackagesLoaded();
    }

    @Override
    public void loadBigFiles(DeepGarbageCallback callback) {
        callback.onBigFilesLoaded();
    }
}
