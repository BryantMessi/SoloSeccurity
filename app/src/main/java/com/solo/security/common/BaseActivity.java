package com.solo.security.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by haipingguo on 16-11-18.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewID() != 0) {
            setContentView(getContentViewID());
        }
        ButterKnife.bind(this);
        initToolBar();
        initFragment();

        initViewsAndData();
    }
    protected abstract int getContentViewID();
    protected abstract void initFragment();
    protected abstract void initToolBar();
    protected abstract void initViewsAndData();
}
