package com.solo.security.data.garbagesource;

import com.solo.security.data.Security;

import java.util.List;

/**
 * Created by Messi on 16-11-3.
 */

public interface GarbageData {
    interface BaseGarbageCallback {
        void onCurrentGarbageSize(String size);

        void onAdFilesLoaded();

        void onCacheFilesLoaded(List<Security> securities);

        void onTempFilesLoaded(List<Security> securities);
    }

    interface DeepGarbageCallback extends BaseGarbageCallback {
        void onResidualFilesLoaded();

        void onMemoryFilesLoaded(List<Security> securities);

        void onInstalledPackagesLoaded(List<Security> securities);

        void onBigFilesLoaded(List<Security> securities);
    }

    void loadAdFiles(BaseGarbageCallback callback);

    void loadCacheFiles(BaseGarbageCallback callback);

    void loadTempFiles(BaseGarbageCallback callback);

    void loadResidualFiles(DeepGarbageCallback callback);

    void loadMemoryFiles(DeepGarbageCallback callback);

    void loadInstalledPackages(DeepGarbageCallback callback);

    void loadBigFiles(DeepGarbageCallback callback);
}
