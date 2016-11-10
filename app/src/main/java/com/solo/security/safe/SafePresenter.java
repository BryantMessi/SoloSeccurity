package com.solo.security.safe;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.solo.security.data.safesource.SafeData;
import com.solo.security.data.safesource.SafeDataImpl;

/**
 * Created by Messi on 16-11-4.
 */

public class SafePresenter implements SafeContract.BaseSafePresenter, SafeData.DeepSafeScanCallback {

    private SafeDataImpl mData;
    private SafeContract.DeepSafeView mView;

    public SafePresenter(@NonNull SafeDataImpl data, @NonNull SafeContract.DeepSafeView view) {
        mData = Preconditions.checkNotNull(data);
        mView = Preconditions.checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void startCloudScan() {
        mData.cloudSafeScan(this);
    }

    @Override
    public void fixScanResult() {
        mData.fixUnSafeApp(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onCurrentScan() {
        mView.updateCurrentScanApp();
    }

    @Override
    public void onScannedUnSafe() {
        mView.notifyScannedUnSafe();
    }

    @Override
    public void onScanFinished() {
        mView.scanFinished();
    }

    @Override
    public void onScanProgressChanged() {
        mView.updateScanProgress();
    }

    @Override
    public void onFixedUnSafe() {
        mView.scanResultFixed();
    }
}
