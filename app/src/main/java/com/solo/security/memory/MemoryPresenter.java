package com.solo.security.memory;

import android.support.annotation.NonNull;

import com.solo.security.data.Security;
import com.solo.security.data.memorysource.MemoryData;
import com.solo.security.data.memorysource.MemoryDataImpl;

import java.util.List;

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
    public void killRunningProcess() {
        mData.killRunningProcess(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onRunningProcessInfo(List<Security> securities) {
        mMemoryView.setRunningProcessInfo(securities);
    }

    @Override
    public void onAvailableMemoryLoaded(String size) {
        mMemoryView.setAvailableMemorySize(size);
    }

    @Override
    public void onTotalMemoryLoaded(String size) {
        mMemoryView.setTotalMemorySize(size);
    }

    @Override
    public void onCurrentMemorySize(String size) {
        mMemoryView.setCurrentMemorySize(size);
    }


    @Override
    public void onRunningProcessKilled() {
        mMemoryView.killedRunningProcess();
    }
}
