package com.solo.security.homepage;

import com.solo.security.BasePresenter;
import com.solo.security.BaseView;

/**
 * Created by Messi on 16-11-5.
 */

public interface HomePageContract {

    interface View extends BaseView<Presenter> {
        void showFirstLaunchAnim();

        void showCommonLaunchAnim();

        void showScanAnim();

        void updateScanAnim();

        void updateScanStatus();

        void showScanResult();

        void updateSafeScanEnable();
    }

    interface Presenter extends BasePresenter {
        void checkLaunchAnim();

        void checkSafeScanEnable();

        void startScan();

        void fix();

        void fixAll();
    }
}
