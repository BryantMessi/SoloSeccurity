package com.solo.security.data.memorysource;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.text.format.Formatter;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.solo.security.data.memorysource.bean.ProcessInfo;
import com.solo.security.data.memorysource.utils.ProcessManager;

import java.util.List;

/**
 * Created by Messi on 16-11-3.
 */

public class MemoryDataImpl implements MemoryData {

    private static MemoryDataImpl sInstance;
    private Context mContext;

    private MemoryDataImpl(Context context) {
        mContext = Preconditions.checkNotNull(context, "Context is null");
    }

    public static MemoryDataImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MemoryDataImpl(context);
        }
        return sInstance;
    }

    @Override
    public void getCurrentMemorySize(@NonNull BaseMemoryCallback callback) {
        callback.onCurrentMemorySize();
    }

    @Override
    public void getRunningProcessInfo(@NonNull DeepMemoryCallback callback) {
        callback.onRunningProcessInfo();
    }

    @Override
    public void getRunningProcessSize(@NonNull BaseMemoryCallback callback) {
        long size = 0;
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.RunningAppProcessInfo> appProcessList = am.getRunningAppProcesses();

            for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
                // 进程ID号
                int pid = appProcessInfo.pid;
                // 获得该进程占用的内存
                int[] myMempid = new int[]{pid};
                // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
                Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(myMempid);
                // 获取进程占内存用信息 kb单位
                int memSize = memoryInfo[0].dalvikPrivateDirty;
                size += memSize;
                Log.i("messi", "pid: " + pid
                        + " memorySize is -->" + memSize + "kb");
            }
        } else {
            List<ProcessInfo> processInfos = ProcessManager.getRunningProcessInfo();
            for (ProcessInfo process : processInfos) {
                int pid = process.getPid();
                int[] myMempid = new int[]{pid};
                // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
                Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(myMempid);
                // 获取进程占内存用信息 kb单位
                int memSize = memoryInfo[0].dalvikPrivateDirty;//单位是kb
                Log.d("messi", "process name :" + process.getProcessName() + " pid :" + pid + " size:" + memSize / 1024);
                size += memSize;
            }
        }
        callback.onTotalRunningProcessSize(Formatter.formatFileSize(mContext, size));
    }

    @Override
    public void getRunningProcessPercent(@NonNull FastMemoryCallback callback) {
        callback.onRunningProcessPercent();
    }

    @Override
    public void getAvailableMemorySize(@NonNull DeepMemoryCallback callback) {
        callback.onAvailableMemoryLoaded();
    }

    @Override
    public void getTotalMemorySize(@NonNull DeepMemoryCallback callback) {
        callback.onTotalMemoryLoaded();
    }

    @Override
    public void killRunningProcess(@NonNull BaseMemoryCallback callback) {
        callback.onRunningProcessKilled();
    }
}
