package com.solo.security.data.garbagesource;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Environment;
import android.os.RemoteException;
import android.support.annotation.WorkerThread;
import android.text.format.Formatter;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.solo.security.SecurityApplication;
import com.solo.security.contansts.SystemConstants;
import com.solo.security.data.Security;
import com.solo.security.data.memorysource.MemoryData;
import com.solo.security.data.memorysource.MemoryDataImpl;
import com.solo.security.utils.AppUtils;
import com.solo.security.utils.FileUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Messi on 16-11-4.
 */

public enum GarbageDataImpl implements GarbageData {

    INSTANCE;

    @Override
    @WorkerThread
    public void loadAdFiles(BaseGarbageCallback callback) {
        callback.onAdFilesLoaded(null);
    }

    @Override
    @WorkerThread
    public void loadCacheFiles(BaseGarbageCallback callback) {
        try {
            final Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
            List<String> installedPackages = AppUtils.getInstalledPackages(context);
            if (!installedPackages.isEmpty()) {
                final List<Security> securities = new ArrayList<>();
                PackageManager pm = context.getPackageManager();
                Method getPackageSizeInfo = pm.getClass().getMethod(
                        "getPackageSizeInfo", String.class, IPackageStatsObserver.class);

                for (final String pkg : installedPackages) {
                    getPackageSizeInfo.invoke(pm, pkg, new IPackageStatsObserver.Stub() {
                        @Override
                        public void onGetStatsCompleted(final PackageStats pStats, boolean succeeded)
                                throws RemoteException {
                            Security bean = new Security();
                            bean.setIcon(AppUtils.getApplicationIcon(context, pkg));
                            bean.setLabel((String) AppUtils.getApplicationLabel(context, pkg));
                            String size = Formatter.formatFileSize(context, pStats.cacheSize);
                            bean.setSize(size);
                            securities.add(bean);
                            Log.d("messi", "cache file name :" + AppUtils.getApplicationLabel(context, pkg) + " size :" + Formatter.formatFileSize(context, pStats.cacheSize));

                        }
                    });
                }
                callback.onCacheFilesLoaded(securities);
            }
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    @WorkerThread
    public void loadTempFiles(BaseGarbageCallback callback) {
        List<Security> securities = new ArrayList<>();
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_ANR)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_ANR, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_LOCAL_TMP)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_LOCAL_TMP, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_SYSTEM_APPUSAGESTATES)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_SYSTEM_APPUSAGESTATES, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_SYSTEM_DROPBOX)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_SYSTEM_DROPBOX, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_SYSTEM_USAGESTATS)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_SYSTEM_USAGESTATS, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_TMP)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_TMP, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DATA_TOMBSTONES)) {
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DATA_TOMBSTONES, callback));
        }

        if (FileUtils.isFileAvailable(SystemConstants.FILE_DEV_LOG_MAIN)) {//设备日志文件
            securities.addAll(getGarbageFiles(SystemConstants.FILE_DEV_LOG_MAIN, callback));
        }

        if (FileUtils.isSDCardMounted()) {
            List<File> files = new ArrayList<>();
            files = FileUtils.getFiles(Environment.getExternalStorageDirectory(), files);
            if (!files.isEmpty()) {
                for (File file : files) {
                    if (file.length() == 0) {//空白文件
                        Security bean = new Security();
                        bean.setLabel(file.getName());
                        bean.setSize(Formatter.formatFileSize(context, file.length()));
                        securities.add(bean);
                    }
                }
            }
        }
        for (Security bean : securities) {
            Log.d("messi", "temp file name :" + bean.getLabel() + " size :" + bean.getSize());
        }
        callback.onTempFilesLoaded(securities);
    }

    @Override
    @WorkerThread
    public void loadResidualFiles(DeepGarbageCallback callback) {
        callback.onResidualFilesLoaded();
    }

    @Override
    @WorkerThread
    public void loadMemoryFiles(final DeepGarbageCallback callback) {
        MemoryDataImpl memoryData = MemoryDataImpl.INSTANCE;
        memoryData.getRunningProcessInfo(new MemoryData.DeepMemoryCallback() {
            @Override
            public void onAvailableMemoryLoaded(String available) {

            }

            @Override
            public void onTotalMemoryLoaded(String total) {

            }

            @Override
            public void onCurrentMemorySize(String size) {
                callback.onCurrentGarbageSize(size);
            }

            @Override
            public void onRunningProcessKilled() {

            }

            @Override
            public void onRunningProcessInfo(List<Security> runningProcessInfoList) {
                callback.onMemoryFilesLoaded(runningProcessInfoList);
            }
        });
    }

    @Override
    @WorkerThread
    public void loadInstalledPackages(DeepGarbageCallback callback) {
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        List<Security> securities = new ArrayList<>();
        if (FileUtils.isSDCardMounted()) {
            List<File> files = new ArrayList<>();
            files = FileUtils.getFiles(Environment.getExternalStorageDirectory(), files);
            if (!files.isEmpty()) {
                for (File file : files) {
                    String name = file.getName();
                    if (name.endsWith(".apk")) {
                        //判断是否是无用安装包
                        if (!file.canExecute()) {
                            Security security = new Security();
                            security.setLabel(name);
                            securities.add(security);
                            String size = Formatter.formatFileSize(context, file.length());
                            security.setSize(size);
                            callback.onCurrentGarbageSize(size);
                        } else if (AppUtils.isAppInstalled(context, name.substring(0, name.lastIndexOf(".")))) {
                            Security security = new Security();
                            security.setLabel(name);
                            securities.add(security);
                            String size = Formatter.formatFileSize(context, file.length());
                            security.setSize(size);
                            callback.onCurrentGarbageSize(size);
                        }
                    }
                }
            }
        }

        for (Security bean : securities) {
            Log.d("messi", "installed file name :" + bean.getLabel() + " size :" + bean.getSize());
        }
        callback.onInstalledPackagesLoaded(securities);
    }

    @Override
    @WorkerThread
    public void loadBigFiles(DeepGarbageCallback callback) {
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        List<Security> securities = new ArrayList<>();
        if (FileUtils.isSDCardMounted()) {
            List<File> files = new ArrayList<>();
            files = FileUtils.getFiles(Environment.getExternalStorageDirectory(), files);
            if (!files.isEmpty()) {
                for (File file : files) {
                    if (file.length() > SystemConstants.BIG_FILE_THRESHOLD) {
                        //大文件
                        Security security = new Security();
                        security.setLabel(file.getName());
                        String size = Formatter.formatFileSize(context, file.length());
                        security.setSize(size);
                        securities.add(security);
                        callback.onCurrentGarbageSize(size);
                    }
                }
            }
        }

        for (Security bean : securities) {
            Log.d("messi", "big file name :" + bean.getLabel() + " size :" + bean.getSize());
        }

        callback.onBigFilesLoaded(securities);
    }

    @Override
    public void cleanGarbageFiles(BaseGarbageCallback callback) {
        callback.onGarbageFilesCleaned();
    }

    private List<Security> getGarbageFiles(File file, BaseGarbageCallback callback) {
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        List<Security> securities = new ArrayList<>();
        List<File> files = new ArrayList<>();
        files = FileUtils.getFiles(file, files);
        if (!files.isEmpty()) {
            for (File f : files) {
                Security bean = new Security();
                bean.setLabel(f.getName());
                String size = Formatter.formatFileSize(context, f.length());
                bean.setSize(size);
                securities.add(bean);
                callback.onCurrentGarbageSize(size);
            }
        }
        return securities;
    }
}
