package com.solo.security.safe;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.common.base.Preconditions;
import com.solo.security.R;
import com.solo.security.common.BaseFragment;
import com.solo.security.common.view.CommonCircleProgressView;
import com.solo.security.safe.view.SafeProgressView;
import com.solo.security.utils.PermissionsChecker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SafeFragment extends BaseFragment implements SafeContract.DeepSafeView, View.OnClickListener {
    private int[] mSafeImageRes = {R.mipmap.icon1, R.mipmap.icon2,R.mipmap.icon3,R.mipmap.icon4,R.mipmap.icon5};

    @BindView(R.id.safe_progress_vew)
    SafeProgressView mSafeProgressView;

    private List<ImageView> mSafeImgList = new ArrayList<>();

    private SafePresenter mPresenter;

    public SafeFragment() {
        // Required empty public constructor
    }

    public static SafeFragment newInstance(String param1, String param2) {
        SafeFragment fragment = new SafeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isHavePermissions();
    }

    @Override
    protected int getContentViewID() {
        return R.layout.fragment_safe;
    }

    @Override
    protected void initViewsAndData(View view) {
        for (int imagRe : mSafeImageRes) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.startCloudScan();
    }

    @Override
    public void updateCurrentScanApp() {

    }

    @Override
    public void notifyScannedUnSafe(int count) {

    }

    @Override
    public void updateScanProgress(double progress) {
        Log.i("ghpppp","progress=="+progress);
        if(99==(int)progress){
            progress=100;
        }
        mSafeProgressView.setBounceProgress((int) progress);
    }

    @Override
    public void scanFinished() {

    }

    @Override
    public void scanResultFixed() {

    }

    @Override
    public void setPresenter(@NonNull SafeContract.BaseSafePresenter presenter) {
        mPresenter = (SafePresenter) Preconditions.checkNotNull(presenter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            //

        }
    }

    public boolean isHavePermissions() {
        try {
            PermissionsChecker mPermissionChecker = new PermissionsChecker(getContext());
            if (mPermissionChecker.lacksPermissions(Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("messi", "permission exception : " + e.getMessage());
            return true;
        }
    }
}
