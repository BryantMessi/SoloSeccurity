package com.solo.security.safe;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.solo.security.data.Security;
import com.solo.security.data.safesource.SafeData;
import com.solo.security.data.safesource.SafeDataImpl;

import java.util.List;

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
    public void fixScanResult(List<Security> securities) {
        mData.fixUnSafeApp(securities, this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onCurrentScan(List<Security> securities) {
        mView.updateCurrentScanApp(securities);
    }

    @Override
    public void onScanningUnSafe(int count) {
        mView.notifyScannedUnSafe(count);
    }

    @Override
    public void onScanFinished(List<Security> securities) {
        mView.scanFinished();
    }

    @Override
    public void onScanProgressChanged(double progress) {
        mView.updateScanProgress(progress);
    }

    @Override
    public void onFixedUnSafe() {
        mView.scanResultFixed();
    }

}
