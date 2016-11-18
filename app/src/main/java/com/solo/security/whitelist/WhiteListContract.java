package com.solo.security.whitelist;

import com.solo.security.BasePresenter;
import com.solo.security.BaseView;

/**
 * Created by Messi on 16-11-4.
 */

public interface WhiteListContract {

    interface View extends BaseView<Presenter> {
        void showMemoryListPage();

        void showEmptyMemoryPage();

        void showSafeListPage();

        void showEmptySafePage();

        void showAddPage();
    }

    interface Presenter extends BasePresenter {
        void loadWhiteList();
    }
}
