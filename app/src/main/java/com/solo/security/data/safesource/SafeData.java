package com.solo.security.data.safesource;

import com.solo.security.data.Security;

import java.util.List;

/**
 * Created by Messi on 16-11-4.
 */

public interface SafeData {

    interface BaseSafeCallback {
        void onScanningUnSafe(int count);

        void onScanFinished(List<Security> securities);

        void onScanProgressChanged(double progress);

        void onFixedUnSafe();
    }

    interface DeepSafeScanCallback extends BaseSafeCallback {
        void onCurrentScan();
    }

    void cloudSafeScan(BaseSafeCallback callback);

    void fixUnSafeApp(BaseSafeCallback callback);
}
