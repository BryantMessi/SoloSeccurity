package com.solo.security.homepage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.solo.security.R;
import com.solo.security.data.homepagesource.HomePageDataImpl;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        HomePageFragment fragment = (HomePageFragment) getSupportFragmentManager().findFragmentById(R.id.fm_home);

        if (fragment == null) {
            fragment = HomePageFragment.newInstance(null, null);
        }

        HomePageDataImpl data = HomePageDataImpl.INSTANCE;
        data.init();
        new HomePagePresenter(data, fragment);
    }
}
