package com.solo.security.data.homepagesource;

import com.solo.security.data.Security;

import java.util.List;
import java.util.Map;

/**
 * Created by Messi on 16-11-5.
 */

public interface HomePageData {

    interface HomePageDataCallback {

        void onUnSafeChecked(int count);

        void onCurrentProgress(double progress);

        void onCurrentMemorySize(String size);

        void onFinishMemorySize(String size);

        void onCurrentMemoryProgress(int progress);

        void onCurrentGarbageSize(String size);

        void onMemoryFixed();

        void onGarbageFixed();

        void onSafeFixed();

        void onMemoryResult(List<Security> securities);

        void onMemoryPercent(int percent);

        void onGarbageResult(Map<String, List<Security>> securities,long garbageSize);

        void onSafeResult();
    }

    void oneKeyScan();

    void oneKeyFix();

    void fixSafe();

    void fixMemory();

    void fixGarbage();
}
