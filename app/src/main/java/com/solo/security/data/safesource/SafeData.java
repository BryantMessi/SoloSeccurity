package com.solo.security.data.safesource;

/**
 * Created by Messi on 16-11-4.
 */

public interface SafeData {

    interface BaseSafeCallback {
        void onScannedUnSafe();

        void onScanFinished();

        void onScanProgressChanged();

        void onFixedUnSafe();
    }

    interface DeepSafeScanCallback extends BaseSafeCallback {
        void onCurrentScan();
    }

    void cloudSafeScan(BaseSafeCallback callback);

    void fixUnSafeApp(BaseSafeCallback callback);
}
