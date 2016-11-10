package com.solo.security.data.memorysource;

import android.support.annotation.NonNull;

/**
 * Created by Messi on 16-11-3.
 */

public interface MemoryData {
    interface BaseMemoryCallback {
        void onCurrentMemorySize();

        void onTotalRunningProcessSize(String size);

        void onRunningProcessKilled();
    }

    interface FastMemoryCallback extends BaseMemoryCallback {
        void onRunningProcessPercent();
    }

    interface DeepMemoryCallback extends BaseMemoryCallback {
        void onRunningProcessInfo();

        void onAvailableMemoryLoaded();

        void onTotalMemoryLoaded();
    }

    void getCurrentMemorySize(@NonNull BaseMemoryCallback callback);

    void getRunningProcessInfo(@NonNull DeepMemoryCallback callback);

    void getRunningProcessSize(@NonNull BaseMemoryCallback callback);

    void getRunningProcessPercent(@NonNull FastMemoryCallback callback);

    void getAvailableMemorySize(@NonNull DeepMemoryCallback callback);

    void getTotalMemorySize(@NonNull DeepMemoryCallback callback);

    void killRunningProcess(@NonNull BaseMemoryCallback callback);
}
