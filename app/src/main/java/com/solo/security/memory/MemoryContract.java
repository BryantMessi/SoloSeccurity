package com.solo.security.memory;

import com.solo.security.BasePresenter;
import com.solo.security.BaseView;

/**
 * Created by Messi on 16-11-3.
 */

public interface MemoryContract {

    interface BaseMemoryView extends BaseView<BaseMemoryPresenter> {
        void setCurrentMemorySize();

        void setTotalRunningProcessSize(String size);

        void killedRunningProcess();
    }

    interface FastMemoryView extends BaseMemoryView {
        void setRunningProcessPercent();
    }

    interface DeepMemoryView extends BaseMemoryView {
        void setRunningProcessInfo();

        void setAvailableMemorySize();

        void setTotalMemorySize();
    }

    interface BaseMemoryPresenter extends BasePresenter {
        void getCurrentMemorySize();

        void getTotalRunningProcessSize();

        void killRunningProcess();
    }

    interface FastMemoryPresenter extends BaseMemoryPresenter {
        void getRunningMemoryPercent();
    }

    interface DeepMemoryPresenter extends BaseMemoryPresenter {
        void getRunningProcessInfo();

        void getAvailableMemorySize();

        void getTotalMemorySize();
    }
}
