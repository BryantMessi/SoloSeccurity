package com.solo.security.homepage;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

import com.solo.security.R;
import com.solo.security.common.BaseActivity;
import com.solo.security.data.homepagesource.HomePageDataImpl;
import com.solo.security.utils.AppUtils;

import butterknife.BindView;

public class HomePageActivity extends BaseActivity {
    private int mStatusHeight;

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.status_bar_vew)
    View mStatusView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initFragment() {
        HomePageFragment fragment = (HomePageFragment) getSupportFragmentManager().findFragmentById(R.id.fm_home);

        if (fragment == null) {
            fragment = HomePageFragment.newInstance(null, null);
        }

        HomePageDataImpl data = HomePageDataImpl.INSTANCE;
        data.init();
        new HomePagePresenter(data, fragment);
    }

    @Override
    protected void initToolBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(Color.TRANSPARENT);
            mToolbar.setTitle(getResources().getString(R.string.app_name));
            setSupportActionBar(mToolbar);
        }
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusHeight = AppUtils.getStatusHeight(this);
        } else {
            mStatusHeight = 0;
        }
        // 设置状态栏高度
        mStatusView.getLayoutParams().height = mStatusHeight;
        mStatusView.setLayoutParams(mStatusView.getLayoutParams());
    }

    @Override
    protected void initViewsAndData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
