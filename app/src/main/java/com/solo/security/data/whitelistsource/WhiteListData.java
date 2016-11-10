package com.solo.security.data.whitelistsource;

/**
 * Created by Messi on 16-11-4.
 */

public interface WhiteListData {

    interface WhiteListDataCallback {
        void onDataLoaded();
    }

    void loadWhiteList(WhiteListDataCallback callback);
}
