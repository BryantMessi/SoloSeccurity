package com.solo.security.whitelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.solo.security.R;
import com.solo.security.data.whitelistsource.WhiteListDataImpl;

public class WhiteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_list);

        WhiteListFragment fragment = (WhiteListFragment) getSupportFragmentManager().findFragmentById(R.id.fm_white_list);
        if (fragment == null) {
            fragment = WhiteListFragment.newInstance(null, null);
        }

        WhiteListDataImpl data = WhiteListDataImpl.getInstance(this);

        new WhiteListPresenter(data, fragment);
    }
}
