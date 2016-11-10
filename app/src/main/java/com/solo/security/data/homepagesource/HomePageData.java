package com.solo.security.data.homepagesource;

/**
 * Created by Messi on 16-11-5.
 */

public interface HomePageData {

    interface HomePageDataCallback {
        void onScanResult();

        void onFixFinished();
    }

    void oneKeyScan(HomePageDataCallback callback);

    void oneKeyFix(HomePageDataCallback callback);
}
