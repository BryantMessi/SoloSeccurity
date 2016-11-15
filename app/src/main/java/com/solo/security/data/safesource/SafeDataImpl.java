package com.solo.security.data.safesource;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.google.common.base.Preconditions;
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

public class SafeDataImpl implements SafeData {

    private static SafeDataImpl sInstance;
    private Context mContext;
    private BaseSafeCallback mCallback;

    private SafeDataImpl(Context context) {
        mContext = Preconditions.checkNotNull(context, "Context is null");
    }

    public static SafeDataImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SafeDataImpl(context);
        }
        return sInstance;
    }

    @Override
    public void cloudSafeScan(BaseSafeCallback callback) {
        mCallback = Preconditions.checkNotNull(callback, "callback is not set into this method");
        new GenerateAppInfo().execute();
    }

    @Override
    public void fixUnSafeApp(BaseSafeCallback callback) {

    }

    private class GenerateAppInfo extends AsyncTask<Void, String, List<PkgInfo>> {

        private CloudScanClient mClient;
        private List<PkgInfo> mPkgInfoList = new ArrayList<>();

        GenerateAppInfo() {//FIXME:需要修改token
            mClient = new CloudScanClient.Builder().setContext(mContext).setConnectionTimeout(3000)
                    .setSocketTimeout(5000).setDeviceId(DeviceUtils.getDeviceId(mContext)).setRegion(Region.INTL)
                    .setToken("1111").setVerbose(1)
                    .build();
        }

        @Override
        protected List<PkgInfo> doInBackground(Void... params) {
            List<PackageInfo> packageInfoList = AppUtils.getLocalAppsPkgInfo(mContext);
            for (PackageInfo pi : packageInfoList) {
                if (pi != null && pi.applicationInfo != null) {
                    PkgInfo info = mClient.populatePkgInfo(pi.packageName, pi.applicationInfo.publicSourceDir);
                    mPkgInfoList.add(info);
                    Log.d("messi", "generate pkg :" + info.getPkgName());

                }
            }
            return mPkgInfoList;
        }

        @Override
        protected void onPostExecute(List<PkgInfo> pkgInfos) {
            //开始云查杀
            if (pkgInfos != null) {
                new CloudScanApps(mClient).execute(pkgInfos);
            }
        }
    }


    private class CloudScanApps extends AsyncTask<List<PkgInfo>, String, ScanResult> {
        private CloudScanClient mClient;

        CloudScanApps(CloudScanClient client) {
            mClient = client;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("messi", "CloudScanApps onProgressUpdate " + values[0]);
            int progress = Integer.parseInt(values[0]);
            if (progress == 100) {
                mCallback.onScanFinished();
            } else {
                mCallback.onScanProgressChanged();
            }
            super.onProgressUpdate(values);
        }

        @Override
        protected ScanResult doInBackground(List<PkgInfo>... params) {
            if (isCancelled()) {
                return null;
            }

            return mClient.cloudScan(params[0]);
        }

        @Override
        protected void onPostExecute(ScanResult scanResult) {
            if (scanResult != null && scanResult.isSuccess()) {
                List<AppInfo> appInfos = scanResult.getList();
                if (appInfos != null) {
                    for (int i = 0; i < appInfos.size(); i++) {
                        AppInfo ai = appInfos.get(i);
                        Log.d("messi", "scan pkg :" + ai.getPackageName() + " score :" + ai.getScore());
                        if (ai.getScore() >= 8) {//8-10分为恶意应用
                            String packageName = ai.getPackageName();
                            //TODO:回调
                            mCallback.onScannedUnSafe();
                        }
                    }
                }
            }
        }
    }
}
