package com.solo.security.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.solo.security.R;
import com.solo.security.data.memorysource.MemoryDataImpl;

public class MemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        MemoryDataImpl data = MemoryDataImpl.getInstance(this);

        MemoryFragment fragment = (MemoryFragment) getSupportFragmentManager().findFragmentById(R.id.fm_memory);
        if (fragment == null) {
            fragment = MemoryFragment.newInstance(null, null);
        }

        new MemoryPresenter(data, fragment);
    }
}
