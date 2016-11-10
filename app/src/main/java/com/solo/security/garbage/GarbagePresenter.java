package com.solo.security.garbage;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.solo.security.data.garbagesource.GarbageData;
import com.solo.security.data.garbagesource.GarbageDataImpl;

/**
 * Created by Messi on 16-11-4.
 */

public class GarbagePresenter implements GarbageContract.DeepGarbagePresenter, GarbageData.DeepGarbageCallback {

    private GarbageDataImpl mData;
    private GarbageContract.DeepGarbageView mView;

    public GarbagePresenter(@NonNull GarbageDataImpl data, @NonNull GarbageContract.DeepGarbageView view) {
        mData = Preconditions.checkNotNull(data);
        mView = Preconditions.checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void loadResidualFiles() {
        mData.loadResidualFiles(this);
    }

    @Override
    public void loadMemoryFiles() {
        mData.loadMemoryFiles(this);
    }

    @Override
    public void loadInstalledPackages() {
        mData.loadInstalledPackages(this);
    }

    @Override
    public void loadBigFiles() {
        mData.loadBigFiles(this);
    }

    @Override
    public void loadCurrentGarbageSize() {
        mData.loadCurrentGarbageSize(this);
    }

    @Override
    public void loadAdFiles() {
        mData.loadAdFiles(this);
    }

    @Override
    public void loadCacheFiles() {
        mData.loadCacheFiles(this);
    }

    @Override
    public void loadTempFiles() {
        mData.loadTempFiles(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onResidualFilesLoaded() {
        mView.setResidualFilesDetail();
    }

    @Override
    public void onMemoryFilesLoaded() {
        mView.setMemoryFilesDetail();
    }

    @Override
    public void onInstalledPackagesLoaded() {
        mView.setInstalledPackages();
    }

    @Override
    public void onBigFilesLoaded() {
        mView.setBigFilesDetail();
    }

    @Override
    public void onCurrentGarbageSize() {
        mView.setCurrentGarbageSize();
    }

    @Override
    public void onAdFilesLoaded() {
        mView.setAdFilesDetail();
    }

    @Override
    public void onCacheFilesLoaded() {
        mView.setCacheFilesDetail();
    }

    @Override
    public void onTempFilesLoaded() {
        mView.setTempFilesDetail();
    }
}
