package com.solo.security.data.memorysource;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.format.Formatter;

import com.google.common.base.Preconditions;
import com.solo.security.data.Security;
import com.solo.security.data.memorysource.bean.ProcessInfo;
import com.solo.security.data.memorysource.utils.ProcessManager;
import com.solo.security.utils.AppUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Messi on 16-11-3.
 */

public class MemoryDataImpl implements MemoryData {

    private static final String TAG = MemoryDataImpl.class.getSimpleName();

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
    @WorkerThread
    public void getRunningProcessInfo(@NonNull BaseMemoryCallback callback) {

        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        ArrayList<Security> memoryInfos = new ArrayList<>();
        long currentScannedSize = 0;
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = mContext.getPackageManager();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.RunningAppProcessInfo> appProcessList = am.getRunningAppProcesses();
            int size = appProcessList.size();
            for (int i = 0; i < size; i++) {
                ActivityManager.RunningAppProcessInfo appProcessInfo = appProcessList.get(i);

                // 进程ID号
                int pid = appProcessInfo.pid;
                // 进程名，默认是包名或者由属性android：process=""指定
                String processName = appProcessInfo.processName;
                // 获得该进程占用的内存
                int[] pids = new int[]{pid};
                // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
                Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(pids);
                // 获取进程占内存用信息 kb单位
                int memSize = memoryInfo[0].dalvikPrivateDirty;

                String[] packageList = appProcessInfo.pkgList;
                Security security = new Security();
                security.setIcon(AppUtils.getApplicationIcon(mContext, packageList[i]));
                security.setLabel((String) AppUtils.getApplicationLabel(mContext, packageList[i]));
                security.setSize(Formatter.formatFileSize(mContext, memSize));
                security.setPackageName(processName);
                memoryInfos.add(security);

                currentScannedSize += memSize;
                callback.onCurrentMemorySize(Formatter.formatFileSize(mContext, currentScannedSize));
            }
        } else {
            List<ProcessInfo> processInfos = ProcessManager.getRunningProcessInfo();
            for (ProcessInfo process : processInfos) {
                int pid = process.getPid();
                String packageName = process.getProcessName();

                Security memory = new Security();
                memory.setLabel((String) AppUtils.getApplicationLabel(mContext, packageName));
                memory.setIcon(AppUtils.getApplicationIcon(mContext, packageName));

                int[] pids = new int[]{pid};
                // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
                Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(pids);
                // 获取进程占内存用信息 kb单位
                int memSize = memoryInfo[0].dalvikPrivateDirty;//单位是kb

                memory.setSize(Formatter.formatFileSize(mContext, memSize));
                memoryInfos.add(memory);

                currentScannedSize += memSize;
                callback.onCurrentMemorySize(Formatter.formatFileSize(mContext, currentScannedSize));
            }
        }
        callback.onRunningProcessInfo(memoryInfos);
    }


    @Override
    public void getRunningProcessPercent(@NonNull FastMemoryCallback callback) {
        long total = ProcessManager.getTotalMemorySize();
        long ava = ProcessManager.getAvailableMemorySize(mContext);
        double temp = (double) (total - ava);
        double percent = (temp / total) * 100;
        DecimalFormat df = new DecimalFormat("###");
        callback.onRunningProcessPercent(Integer.parseInt(df.format(percent)));
    }

    @Override
    public void getAvailableMemorySize(@NonNull DeepMemoryCallback callback) {
        long ava = ProcessManager.getAvailableMemorySize(mContext);
        callback.onAvailableMemoryLoaded(Formatter.formatFileSize(mContext, ava));
    }

    @Override
    public void getTotalMemorySize(@NonNull DeepMemoryCallback callback) {
        long total = ProcessManager.getTotalMemorySize();
        callback.onTotalMemoryLoaded(Formatter.formatFileSize(mContext, total));
    }

    @Override
    public void killRunningProcess(@NonNull BaseMemoryCallback callback) {
        callback.onRunningProcessKilled();
    }
}
