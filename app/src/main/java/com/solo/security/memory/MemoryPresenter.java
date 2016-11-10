package com.solo.security.memory;

import android.support.annotation.NonNull;

import com.solo.security.data.memorysource.MemoryData;
import com.solo.security.data.memorysource.MemoryDataImpl;

/**
 * Created by Messi on 16-11-3.
 */

public class MemoryPresenter implements MemoryContract.DeepMemoryPresenter, MemoryData.DeepMemoryCallback {

    private MemoryDataImpl mData;
    private MemoryContract.DeepMemoryView mMemoryView;

    public MemoryPresenter(@NonNull MemoryDataImpl memoryData, @NonNull MemoryContract.DeepMemoryView memoryView) {
        mData = memoryData;
        mMemoryView = memoryView;
        mMemoryView.setPresenter(this);
    }

    @Override
    public void getRunningProcessInfo() {
        mData.getRunningProcessInfo(this);
    }

    @Override
    public void getAvailableMemorySize() {
        mData.getAvailableMemorySize(this);
    }

    @Override
    public void getTotalMemorySize() {
        mData.getTotalMemorySize(this);
    }

    @Override
    public void getCurrentMemorySize() {
        mData.getCurrentMemorySize(this);
    }

    @Override
    public void getTotalRunningProcessSize() {
        mData.getRunningProcessSize(this);
    }

    @Override
    public void killRunningProcess() {
        mData.killRunningProcess(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onRunningProcessInfo() {
        mMemoryView.setRunningProcessInfo();
    }

    @Override
    public void onAvailableMemoryLoaded() {
        mMemoryView.setAvailableMemorySize();
    }

    @Override
    public void onTotalMemoryLoaded() {
        mMemoryView.setTotalMemorySize();
    }

    @Override
    public void onCurrentMemorySize() {
        mMemoryView.setCurrentMemorySize();
    }

    @Override
    public void onTotalRunningProcessSize(String size) {
        mMemoryView.setTotalRunningProcessSize(size);
    }

    @Override
    public void onRunningProcessKilled() {
        mMemoryView.killedRunningProcess();
    }
}
