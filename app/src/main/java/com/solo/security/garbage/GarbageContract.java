package com.solo.security.garbage;


import com.solo.security.common.BasePresenter;
import com.solo.security.common.BaseView;

/**
 * Created by Messi on 16-11-4.
 */

public interface GarbageContract {

    interface BaseGarbageView extends BaseView<BaseGarbagePresenter> {
        void setCurrentGarbageSize();
    }

    interface FastGarbageView extends BaseGarbageView {
        void setAdFileSize();

        void setCacheFileSize();

        void setTempFileSize();
    }

    interface DeepGarbageView extends BaseGarbageView {
        void setAdFilesDetail();

        void setCacheFilesDetail();

        void setTempFilesDetail();

        void setResidualFilesDetail();

        void setMemoryFilesDetail();

        void setInstalledPackages();

        void setBigFilesDetail();
    }

    interface BaseGarbagePresenter extends BasePresenter {
        void loadCurrentGarbageSize();

        void loadAdFiles();

        void loadCacheFiles();

        void loadTempFiles();
    }

    interface DeepGarbagePresenter extends BaseGarbagePresenter {
        void loadResidualFiles();

        void loadMemoryFiles();

        void loadInstalledPackages();

        void loadBigFiles();
    }
}
