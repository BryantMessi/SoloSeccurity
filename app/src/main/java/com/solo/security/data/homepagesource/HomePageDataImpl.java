package com.solo.security.data.homepagesource;

import com.solo.security.data.garbagesource.GarbageData.BaseGarbageCallback;
import com.solo.security.data.memorysource.MemoryData;
import com.solo.security.data.safesource.SafeData;

/**
 * Created by Messi on 16-11-5.
 */

public class HomePageDataImpl implements HomePageData, MemoryData.FastMemoryCallback, BaseGarbageCallback
        , SafeData.BaseSafeCallback {


    @Override
    public void oneKeyScan(HomePageDataCallback callback) {
        //TODO:分别调用三个模块的扫描功能；
    }

    @Override
    public void oneKeyFix(HomePageDataCallback callback) {

    }

    @Override
    public void onRunningProcessPercent() {

    }

    @Override
    public void onCurrentMemorySize() {

    }

    @Override
    public void onTotalRunningProcessSize(String size) {

    }

    @Override
    public void onRunningProcessKilled() {

    }

    @Override
    public void onCurrentGarbageSize() {

    }

    @Override
    public void onAdFilesLoaded() {

    }

    @Override
    public void onCacheFilesLoaded() {

    }

    @Override
    public void onTempFilesLoaded() {

    }

    @Override
    public void onScannedUnSafe() {

    }

    @Override
    public void onScanFinished() {

    }

    @Override
    public void onScanProgressChanged() {

    }

    @Override
    public void onFixedUnSafe() {

    }
}
