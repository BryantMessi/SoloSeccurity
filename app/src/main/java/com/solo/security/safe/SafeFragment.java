package com.solo.security.safe;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.solo.security.R;

public class SafeFragment extends Fragment implements SafeContract.DeepSafeView {


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_safe, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void updateCurrentScanApp() {

    }

    @Override
    public void notifyScannedUnSafe() {

    }

    @Override
    public void updateScanProgress() {

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
}
