package com.solo.security.whitelist;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.solo.security.data.Security;
import com.solo.security.data.whitelistsource.WhiteListData;
import com.solo.security.data.whitelistsource.WhiteListDataImpl;

import java.util.List;

/**
 * Created by Messi on 16-11-4.
 */

public class WhiteListPresenter implements WhiteListContract.Presenter, WhiteListData.WhiteListDataCallback {

    private WhiteListDataImpl mData;
    private WhiteListContract.View mView;

    public WhiteListPresenter(@NonNull WhiteListDataImpl data, @NonNull WhiteListContract.View view) {
        mData = Preconditions.checkNotNull(data);
        mView = Preconditions.checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void loadWhiteList() {
        mData.loadMemoryWhiteList(this);
        mData.loadSafeWhiteList(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onMemoryListLoaded(List<Security> securities) {
        mView.showMemoryListPage();
    }

    @Override
    public void onSafeListLoaded(List<Security> securities) {
        mView.showSafeListPage();
    }

    @Override
    public void onEmptyMemoryList() {
        mView.showEmptyMemoryPage();
    }

    @Override
    public void onEmptySafeList() {
        mView.showEmptySafePage();
    }
}
