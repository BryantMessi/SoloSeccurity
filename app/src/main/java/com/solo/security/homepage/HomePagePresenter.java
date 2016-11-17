package com.solo.security.homepage;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.solo.security.data.Security;
import com.solo.security.data.homepagesource.HomePageData;
import com.solo.security.data.homepagesource.HomePageDataImpl;
import com.solo.security.utils.DeviceUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Messi on 16-11-5.
 */

public class HomePagePresenter implements HomePageContract.Presenter, HomePageData.HomePageDataCallback {

    private HomePageDataImpl mData;
    private HomePageContract.View mView;

    public HomePagePresenter(@NonNull HomePageDataImpl data, @NonNull HomePageContract.View view) {
        mData = Preconditions.checkNotNull(data);
        mView = Preconditions.checkNotNull(view);
        mView.setPresenter(this);
        mData.setCallback(this);
    }

    @Override
    public void checkLaunchAnim() {
        //TODO:检测是否是第一次启动，决定展示界面
    }

    @Override
    public void checkSafeScanEnable(Context context) {
        mView.updateSafeScanEnable(DeviceUtils.isNetworkAvailable(context));
    }

    @Override
    public void startScan() {
        //FIXME：开启一键扫描
        mData.oneKeyScan();
    }

    @Override
    public void fixSafe() {
        mData.fixSafe();
    }

    @Override
    public void fixMemory() {
        mData.fixMemory();
    }

    @Override
    public void fixGarbage() {
        mData.fixGarbage();
    }

    @Override
    public void fixAll() {
        //FIXME:全部修复
        mData.oneKeyFix();
    }

    @Override
    public void start() {

    }

    @Override
    public void onUnSafeChecked() {
        mView.updateWhenUnSafe();
    }

    @Override
    public void onCurrentMemorySize(String size) {
        mView.updateCurrentMemorySize(size);
    }

    @Override
    public void onCurrentGarbageSize(String size) {
        mView.updateCurrentGarbageSize(size);
    }

    @Override
    public void onMemoryFixed() {

    }

    @Override
    public void onGarbageFixed() {

    }

    @Override
    public void onSafeFixed() {

    }

    @Override
    public void onMemoryResult(List<Security> securities) {

    }

    @Override
    public void onMemoryPercent(int percent) {

    }

    @Override
    public void onGarbageResult(Map<String, List<Security>> securities) {

    }

    @Override
    public void onSafeResult() {

    }
}
