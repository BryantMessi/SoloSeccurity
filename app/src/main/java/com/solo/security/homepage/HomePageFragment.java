package com.solo.security.homepage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.solo.security.R;

public class HomePageFragment extends Fragment implements View.OnClickListener, HomePageContract.View {

    private HomePagePresenter mPresenter;

    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_one_key).setOnClickListener(this);
        view.findViewById(R.id.btn_safe).setOnClickListener(this);
        view.findViewById(R.id.btn_memory).setOnClickListener(this);
        view.findViewById(R.id.btn_garbage).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_one_key:
                mPresenter.startScan();
                break;
            case R.id.btn_safe:
                break;
            case R.id.btn_memory:
                break;
            case R.id.btn_garbage:
                break;
        }
    }

    @Override
    public void showFirstLaunchAnim() {

    }

    @Override
    public void showCommonLaunchAnim() {

    }

    @Override
    public void showScanAnim() {

    }

    @Override
    public void updateScanAnim() {

    }

    @Override
    public void showScanResult() {

    }

    @Override
    public void updateSafeScanEnable(boolean enable) {

    }

    @Override
    public void startSafeScanAnim() {

    }

    @Override
    public void updateWhenUnSafe() {

    }

    @Override
    public void stopSafeScanAnim() {

    }

    @Override
    public void startMemoryAnim() {

    }

    @Override
    public void updateCurrentMemorySize(String size) {

    }


    @Override
    public void stopMemoryAnim() {

    }

    @Override
    public void startGarbageAnim() {

    }

    @Override
    public void updateCurrentGarbageSize(String size) {

    }

    @Override
    public void stopGarbageAnim() {

    }

    @Override
    public void setPresenter(@NonNull HomePageContract.Presenter presenter) {
        mPresenter = (HomePagePresenter) Preconditions.checkNotNull(presenter);
    }
}
