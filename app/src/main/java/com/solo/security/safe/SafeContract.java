package com.solo.security.safe;

import com.solo.security.BasePresenter;
import com.solo.security.BaseView;

/**
 * Created by Messi on 16-11-4.
 */

public interface SafeContract {

    interface BaseSafeView extends BaseView<BaseSafePresenter> {
        void notifyScannedUnSafe();

        void updateScanProgress();

        void scanFinished();

        void scanResultFixed();
    }

    interface DeepSafeView extends BaseSafeView {
        void updateCurrentScanApp();
    }

    interface BaseSafePresenter extends BasePresenter {
        void startCloudScan();

        void fixScanResult();
    }
}
