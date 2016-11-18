package com.solo.security.data.memorysource;

import android.support.annotation.NonNull;

import com.solo.security.data.Security;

import java.util.List;

/**
 * Created by Messi on 16-11-3.
 */

public interface MemoryData {
    interface BaseMemoryCallback {
        void onCurrentMemorySize(String size);

        void onCurrentMemoryProgress(int progress);

        void onFinishMemorySize(String size);

        void onRunningProcessKilled();

        void onRunningProcessInfo(List<Security> runningProcessInfoList);
    }

    interface FastMemoryCallback extends BaseMemoryCallback {
        void onRunningProcessPercent(int percent);
    }

    interface DeepMemoryCallback extends BaseMemoryCallback {

        void onAvailableMemoryLoaded(String available);

        void onTotalMemoryLoaded(String total);
    }

    void getRunningProcessInfo(@NonNull BaseMemoryCallback callback);

    void getRunningProcessPercent(@NonNull FastMemoryCallback callback);

    void getAvailableMemorySize(@NonNull DeepMemoryCallback callback);

    void getTotalMemorySize(@NonNull DeepMemoryCallback callback);

    void killRunningProcess(@NonNull BaseMemoryCallback callback);
}
