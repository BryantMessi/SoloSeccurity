package com.solo.security.garbage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.solo.security.R;
import com.solo.security.data.garbagesource.GarbageDataImpl;

public class GarbageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage);

        GarbageFragment fragment = (GarbageFragment) getSupportFragmentManager().findFragmentById(R.id.fm_garbage);
        if (fragment == null) {
            fragment = GarbageFragment.newInstance(null, null);
        }

        GarbageDataImpl data = GarbageDataImpl.INSTANCE;

        new GarbagePresenter(data, fragment);
    }
}
