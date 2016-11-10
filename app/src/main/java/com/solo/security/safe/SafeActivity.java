package com.solo.security.safe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.solo.security.R;
import com.solo.security.data.safesource.SafeDataImpl;

public class SafeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);

        SafeFragment fragment = (SafeFragment) getSupportFragmentManager().findFragmentById(R.id.fm_safe);

        if (fragment == null) {
            fragment = SafeFragment.newInstance(null, null);
        }

        SafeDataImpl data = SafeDataImpl.getInstance(this);

        new SafePresenter(data, fragment);
    }
}
