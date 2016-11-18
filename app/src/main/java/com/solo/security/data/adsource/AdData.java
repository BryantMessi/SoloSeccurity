package com.solo.security.data.adsource;

/**
 * Created by Messi on 16-11-18.
 */

public interface AdData {

    interface Callback {
        void onAdSuccess();

        void onAdError();

        void onAdClick();
    }

    void loadAd();
}
