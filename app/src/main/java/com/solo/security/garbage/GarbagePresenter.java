package com.solo.security.garbage;

import android.support.annotation.NonNull;
import android.widget.NumberPicker;

import com.google.common.base.Preconditions;
import com.solo.security.data.Security;
import com.solo.security.data.garbagesource.GarbageData;
import com.solo.security.data.garbagesource.GarbageDataImpl;

import java.util.Formatter;
import java.util.List;

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
    public void onMemoryFilesLoaded(List<Security> securities) {
        mView.setMemoryFilesDetail();
    }

    @Override
    public void onInstalledPackagesLoaded(List<Security> securities) {
        mView.setInstalledPackages();
    }

    @Override
    public void onBigFilesLoaded(List<Security> securities) {
        mView.setBigFilesDetail();
    }

    @Override
    public void onCurrentGarbageSize(String size) {
        mView.setCurrentGarbageSize();
    }

    @Override
    public void onAdFilesLoaded(List<Security> securities) {
        mView.setAdFilesDetail();
    }

    @Override
    public void onCacheFilesLoaded(List<Security> securities, long garbageSize) {
        mView.setCacheFilesDetail();
    }

    @Override
    public void onTempFilesLoaded(List<Security> securities, long garbageSize) {
        mView.setTempFilesDetail();
    }

    @Override
    public void onGarbageFilesCleaned() {

    }
}
