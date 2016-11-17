package com.solo.security.homepage;

import android.content.Context;

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

        void showScanResult();

        void updateSafeScanEnable(boolean enable);

        void startSafeScanAnim();

        void updateWhenUnSafe();

        void stopSafeScanAnim();

        void startMemoryAnim();

        void updateCurrentMemorySize(String size);

        void stopMemoryAnim();

        void startGarbageAnim();

        void updateCurrentGarbageSize(String size);

        void stopGarbageAnim();
    }

    interface Presenter extends BasePresenter {
        void checkLaunchAnim();

        void checkSafeScanEnable(Context context);

        void startScan();

        void fixSafe();

        void fixMemory();

        void fixGarbage();

        void fixAll();
    }
}
