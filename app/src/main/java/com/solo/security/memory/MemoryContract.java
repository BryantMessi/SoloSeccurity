package com.solo.security.memory;

import com.solo.security.common.BasePresenter;
import com.solo.security.common.BaseView;
import com.solo.security.data.Security;

import java.util.List;

/**
 * Created by Messi on 16-11-3.
 */

public interface MemoryContract {

    interface BaseMemoryView extends BaseView<BaseMemoryPresenter> {
        void setCurrentMemorySize(String size);

        void setRunningProcessInfo(List<Security> securities);

        void killedRunningProcess();
    }

    interface FastMemoryView extends BaseMemoryView {
        void setRunningProcessPercent();
    }

    interface DeepMemoryView extends BaseMemoryView {

        void setAvailableMemorySize(String size);

        void setTotalMemorySize(String size);
    }

    interface BaseMemoryPresenter extends BasePresenter {

        void getRunningProcessInfo();

        void killRunningProcess();
    }

    interface FastMemoryPresenter extends BaseMemoryPresenter {
        void getRunningMemoryPercent();
    }

    interface DeepMemoryPresenter extends BaseMemoryPresenter {

        void getAvailableMemorySize();

        void getTotalMemorySize();
    }
}
