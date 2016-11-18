package com.solo.security.data.homepagesource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.solo.security.SecurityApplication;
import com.solo.security.data.Security;
import com.solo.security.data.garbagesource.GarbageData;
import com.solo.security.data.garbagesource.GarbageDataImpl;
import com.solo.security.data.memorysource.MemoryData;
import com.solo.security.data.memorysource.MemoryDataImpl;
import com.solo.security.data.safesource.SafeData;
import com.solo.security.data.safesource.SafeDataImpl;
import com.solo.security.utils.DeviceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Messi on 16-11-5.
 */

public enum HomePageDataImpl implements HomePageData, MemoryData.FastMemoryCallback, SafeData.BaseSafeCallback
        , GarbageData.BaseGarbageCallback {

    INSTANCE;

    private HomePageDataCallback mCallback;
    private MemoryDataImpl mMemory;
    private SafeDataImpl mSafe;
    private GarbageDataImpl mGarbage;

    private Map<String, List<Security>> mGarbageFiles;

    public void init() {
        mMemory = MemoryDataImpl.INSTANCE;
        mSafe = SafeDataImpl.INSTANCE;
        mGarbage = GarbageDataImpl.INSTANCE;
        mGarbageFiles = new HashMap<>();
    }

    public void setCallback(@NonNull HomePageDataCallback callback) {
        mCallback = Preconditions.checkNotNull(callback, "not have a callback");
    }

    @Override
    public void oneKeyScan() {
        //TODO:分别调用三个模块的扫描功能；
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        if (DeviceUtils.isNetworkAvailable(context)) {
            mSafe.cloudSafeScan(this);
        } else {
            mMemory.getRunningProcessInfo(this);
            mMemory.getRunningProcessPercent(this);
        }
    }

    @Override
    public void oneKeyFix() {
        fixSafe();
        fixMemory();
        fixGarbage();
    }

    @Override
    public void fixSafe() {
        mSafe.fixUnSafeApp(this);
    }

    @Override
    public void fixMemory() {
        mMemory.killRunningProcess(this);
    }

    @Override
    public void fixGarbage() {
        mGarbage.cleanGarbageFiles(this);
    }

    @Override
    public void onRunningProcessPercent(int percent) {
        mCallback.onMemoryPercent(percent);
    }

    @Override
    public void onCurrentMemorySize(String size) {
        mCallback.onCurrentMemorySize(size);
        mGarbage.loadCacheFiles(this);
        mGarbage.loadTempFiles(this);
        mGarbage.loadAdFiles(this);
    }

    @Override
    public void onRunningProcessKilled() {
        mCallback.onMemoryFixed();
    }

    @Override
    public void onRunningProcessInfo(List<Security> runningProcessInfoList) {
        mCallback.onMemoryResult(runningProcessInfoList);
    }

    @Override
    public void onScanningUnSafe(int count) {
        mCallback.onUnSafeChecked();
    }

    @Override
    public void onScanFinished(List<Security> securities) {
        mCallback.onSafeResult();
        mMemory.getRunningProcessPercent(this);
        mMemory.getRunningProcessInfo(this);
    }

    @Override
    public void onScanProgressChanged() {

    }

    @Override
    public void onFixedUnSafe() {
        mCallback.onSafeFixed();
    }

    @Override
    public void onCurrentGarbageSize(String size) {
        mCallback.onCurrentGarbageSize(size);
    }

    @Override
    public void onAdFilesLoaded(List<Security> securities) {
        mGarbageFiles.put("Ad", securities);
        if (mGarbageFiles.size() == 3) {
            mCallback.onGarbageResult(mGarbageFiles);
        }
    }

    @Override
    public void onCacheFilesLoaded(List<Security> securities) {
        mGarbageFiles.put("Cache", securities);
        if (mGarbageFiles.size() == 3) {
            mCallback.onGarbageResult(mGarbageFiles);
        }
    }

    @Override
    public void onTempFilesLoaded(List<Security> securities) {
        mGarbageFiles.put("Temp", securities);
        if (mGarbageFiles.size() == 3) {
            mCallback.onGarbageResult(mGarbageFiles);
        }
    }

    @Override
    public void onGarbageFilesCleaned() {
        mCallback.onGarbageFixed();
    }
}
