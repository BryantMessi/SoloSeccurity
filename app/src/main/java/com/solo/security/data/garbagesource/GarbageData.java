package com.solo.security.data.garbagesource;

/**
 * Created by Messi on 16-11-3.
 */

public interface GarbageData {
    interface BaseGarbageCallback {
        void onCurrentGarbageSize();

        void onAdFilesLoaded();

        void onCacheFilesLoaded();

        void onTempFilesLoaded();
    }

    interface DeepGarbageCallback extends BaseGarbageCallback {
        void onResidualFilesLoaded();

        void onMemoryFilesLoaded();

        void onInstalledPackagesLoaded();

        void onBigFilesLoaded();
    }

    void loadCurrentGarbageSize(BaseGarbageCallback callback);

    void loadAdFiles(BaseGarbageCallback callback);

    void loadCacheFiles(BaseGarbageCallback callback);

    void loadTempFiles(BaseGarbageCallback callback);

    void loadResidualFiles(DeepGarbageCallback callback);

    void loadMemoryFiles(DeepGarbageCallback callback);

    void loadInstalledPackages(DeepGarbageCallback callback);

    void loadBigFiles(DeepGarbageCallback callback);
}
