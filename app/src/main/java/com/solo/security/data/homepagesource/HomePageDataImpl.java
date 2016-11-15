package com.solo.security.data.homepagesource;

import com.solo.security.data.Security;
import com.solo.security.data.garbagesource.GarbageData.BaseGarbageCallback;
import com.solo.security.data.memorysource.MemoryData;
import com.solo.security.data.safesource.SafeData;

import java.util.List;

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
    public void onRunningProcessPercent(int percent) {

    }

    @Override
    public void onCurrentMemorySize(String size) {

    }

    @Override
    public void onRunningProcessKilled() {

    }

    @Override
    public void onRunningProcessInfo(List<Security> runningProcessInfoList) {

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

    @Override
    public void onCurrentGarbageSize(String size) {

    }

    @Override
    public void onAdFilesLoaded() {

    }

    @Override
    public void onCacheFilesLoaded(List<Security> securities) {

    }

    @Override
    public void onTempFilesLoaded(List<Security> securities) {

    }
}
