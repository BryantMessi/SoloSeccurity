package com.solo.security.safe;


import com.solo.security.common.BasePresenter;
import com.solo.security.common.BaseView;
import com.solo.security.data.Security;

import java.util.List;

/**
 * Created by Messi on 16-11-4.
 */

public interface SafeContract {

    interface BaseSafeView extends BaseView<BaseSafePresenter> {
        void notifyScannedUnSafe(int count);

        void updateScanProgress(double progress);

        void scanFinished();

        void scanResultFixed();
    }

    interface DeepSafeView extends BaseSafeView {
        void updateCurrentScanApp(List<Security> securities);
    }

    interface BaseSafePresenter extends BasePresenter {
        void startCloudScan();

        void fixScanResult(List<Security> securities);
    }
}
