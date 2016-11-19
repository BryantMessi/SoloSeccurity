package com.solo.security.homepage;

import android.content.Context;

import com.solo.security.common.BasePresenter;
import com.solo.security.common.BaseView;

/**
 * Created by Messi on 16-11-5.
 */

public interface HomePageContract {

    interface View extends BaseView<Presenter> {
        void currentVirusProgress(double progress);

        void currentMemoryProgress(int progress);

        void finishMemorySize(String size);

        void finishGarbageSize(long size);

        void showFirstLaunchAnim();

        void showCommonLaunchAnim();

        void showScanAnim();

        void updateScanAnim();

        void showScanResult();

        void updateSafeScanEnable(boolean enable);

        void startSafeScanAnim();

        void updateWhenUnSafe(int count);

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
