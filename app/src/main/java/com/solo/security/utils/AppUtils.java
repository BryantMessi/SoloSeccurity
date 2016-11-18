package com.solo.security.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.solo.security.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Messi on 16-10-18.
 */

public class AppUtils {
    private static final int DEFAULT_SCREEN_STATUSBAR = 75;

    public static Drawable getApplicationIcon(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return info.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        }
        return context.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    public static CharSequence getApplicationLabel(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return info.loadLabel(pm);
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
            return packageName;
        }
    }

    public static List<String> getInstalledPackages(Context context) {
        ArrayList<String> appList = new ArrayList<String>();
        if (context != null) {
            List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packages.size(); i++) {
                PackageInfo packageInfo = packages.get(i);
                //Only get the non-system app info
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    appList.add(packageInfo.packageName);
                }
            }
        }
        return appList;
    }

    public static boolean isAppInstalled(Context context, String pkgName) {
        boolean flag;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(pkgName, PackageManager.GET_GIDS);
            flag = info != null;
        } catch (Exception e) {
            // 抛出找不到的异常，说明该程序已经被卸载
            flag = false;
        }
        return flag;
    }

    public static List<PackageInfo> getLocalAppsPkgInfo(Context context) {
        final int MAX_ATTEMPTS = 3;

        for (int i = 0; i < MAX_ATTEMPTS; i++) {

            try {
                return context.getPackageManager().getInstalledPackages(
                        PackageManager.GET_PERMISSIONS | PackageManager.GET_PROVIDERS);
            } catch (RuntimeException re) {

                // Just wait for cooling down
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        }
        return new ArrayList<PackageInfo>();
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = DEFAULT_SCREEN_STATUSBAR;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        } catch (NoSuchFieldException e) {
        } catch (ClassNotFoundException e) {
        }
        return statusHeight;
    }
}
