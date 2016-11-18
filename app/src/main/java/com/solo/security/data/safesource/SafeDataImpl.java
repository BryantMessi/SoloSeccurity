package com.solo.security.data.safesource;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.solo.security.SecurityApplication;
import com.solo.security.data.Security;
import com.solo.security.utils.AppUtils;
import com.solo.security.utils.DeviceUtils;
import com.trustlook.sdk.cloudscan.CloudScanClient;
import com.trustlook.sdk.cloudscan.ScanResult;
import com.trustlook.sdk.data.AppInfo;
import com.trustlook.sdk.data.PkgInfo;
import com.trustlook.sdk.data.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Messi on 16-11-4.
 */

public enum SafeDataImpl implements SafeData {

    INSTANCE;

    private BaseSafeCallback mCallback;

    @Override
    public void cloudSafeScan(BaseSafeCallback callback) {
        mCallback = Preconditions.checkNotNull(callback, "callback is not set into this method");
        new GenerateAppInfo().execute();
    }

    @Override
    public void fixUnSafeApp(BaseSafeCallback callback) {

    }

    private class GenerateAppInfo extends AsyncTask<Void, Double, Void> {

        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        private CloudScanClient mClient;
        private List<PkgInfo> mPkgInfoList = new ArrayList<>();

        GenerateAppInfo() {//FIXME:需要修改token
            mClient = new CloudScanClient.Builder().setContext(context).setConnectionTimeout(3000)
                    .setSocketTimeout(5000).setDeviceId(DeviceUtils.getDeviceId(context)).setRegion(Region.INTL)
                    .setToken("1111").setVerbose(1)
                    .build();
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            Log.d("messi", "scan progress :" + values[0]);
            mCallback.onScanProgressChanged();
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<PackageInfo> packageInfoList = AppUtils.getLocalAppsPkgInfo(context);
            for (PackageInfo pi : packageInfoList) {
                if (pi != null && pi.applicationInfo != null) {
                    PkgInfo info = mClient.populatePkgInfo(pi.packageName, pi.applicationInfo.publicSourceDir);
                    mPkgInfoList.add(info);
                    Log.d("messi", "generate pkg :" + info.getPkgName());
                }
            }

            ScanResult result = mClient.cacheCheck(mPkgInfoList);

            if (result != null && result.isSuccess()) {
                List<AppInfo> appInfos = result.getList();
                if (appInfos != null && !appInfos.isEmpty()) {
                    List<Security> securities = new ArrayList<>();
                    int unSafeCount = 0;
                    for (int i = 0; i < appInfos.size(); i++) {
                        AppInfo ai = appInfos.get(i);
                        Security security = new Security();
                        String packageName = ai.getPackageName();
                        security.setPackageName(packageName);
                        security.setIcon(AppUtils.getApplicationIcon(context, packageName));
                        security.setLabel((String) AppUtils.getApplicationLabel(context, packageName));
                        Log.d("messi", "scan pkg :" + ai.getPackageName() + " score :" + ai.getScore());
                        if (ai.getScore() >= 8) {//8-10分为恶意应用
                            security.setInfo("Malware");
                            unSafeCount++;
                            mCallback.onScanningUnSafe(unSafeCount);
                        } else {
                            security.setInfo("safe");
                        }
                        securities.add(security);
                        publishProgress((double) (i * 100 / appInfos.size()));
                    }
                    mCallback.onScanFinished(securities);
                }
            } else {
                //TODO:云查杀失败
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
