package com.solo.security.data.whitelistsource;

import com.solo.security.data.Security;

import java.util.List;

/**
 * Created by Messi on 16-11-4.
 */

public interface WhiteListData {

    interface WhiteListDataCallback {
        void onMemoryListLoaded(List<Security> securities);

        void onSafeListLoaded(List<Security> securities);

        void onEmptyMemoryList();

        void onEmptySafeList();
    }

    void loadMemoryWhiteList(WhiteListDataCallback callback);

    void loadSafeWhiteList(WhiteListDataCallback callback);
}
