package com.solo.security.homepage;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.solo.security.R;
import com.solo.security.cleanfinish.CleanFinishActivity;
import com.solo.security.common.BaseFragment;
import com.solo.security.common.view.CommonCircleRippleView;
import com.solo.security.common.view.CommonWaveHelper;
import com.solo.security.common.view.CommonWaveView;
import com.solo.security.homepage.view.HomePageCircleProgressView;
import com.solo.security.homepage.view.HomePageDrawRadianView;
import com.solo.security.homepage.view.HomePageOneKeyScanView;
import com.solo.security.utils.AnimatorUtils;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment implements View.OnClickListener, HomePageContract.View {

    private HomePagePresenter mPresenter;
    private CommonWaveHelper mCommonWaveHelper;
    private static final int VIRUS_PROGRESS = 120;

    @BindView(R.id.homepage_one_key_scan_rlyt)
    RelativeLayout mHomePageOneKeyScanRlyt;

    @BindView(R.id.homepage_safe_txt)
    TextView mHomePageSafeTxt;

    @BindView(R.id.homepage_progress_txt)
    TextView mHomePageProgressTxt;

    @BindView(R.id.homepage_virus_killing_txt)
    TextView mHomePageVirusKillTxt;

    @BindView(R.id.homepage_mobile_accelerator_txt)
    TextView mHomePageMobileAcceleratorTxt;

    @BindView(R.id.homepage_garbage_disposal_txt)
    TextView mHomePageGarbageDisposalTxt;

    @BindView(R.id.common_waveview)
    CommonWaveView mCommonWaveView;

    @BindView(R.id.homepage_circle_progress_vew)
    HomePageCircleProgressView mHomePageCircleProgressView;

    @BindView(R.id.homepage_draw_radian_vew)
    HomePageDrawRadianView mHomePageDrawRadianView;

    @BindView(R.id.homepage_onekey_scan_vew)
    HomePageOneKeyScanView mHomePageOneKeyScanView;

    @BindView(R.id.homepage_circle_ripple_vew)
    CommonCircleRippleView mCommonCircleRippleView;

    @BindView(R.id.homepage_virus_killing_img)
    ImageView mHomePageVirusKillingImg;

    @BindView(R.id.homepage_mobile_accelerator_img)
    ImageView mHomePageMobileAcceleratorImg;

    @BindView(R.id.homepage_garbage_disposal_img)
    ImageView mHomePageGarbageDisposalImg;


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomePageOneKeyScanRlyt.setOnClickListener(this);
        mCommonWaveHelper = new CommonWaveHelper(mCommonWaveView);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCommonWaveHelper.cancel();
    }

    @Override
    protected int getContentViewID() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initViewsAndData(View view) {

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
            case R.id.homepage_one_key_scan_rlyt:
                mHomePageCircleProgressView.setVisibility(View.VISIBLE);
                mHomePageDrawRadianView.setVisibility(View.VISIBLE);
                mCommonCircleRippleView.setVisibility(View.GONE);
                mCommonWaveHelper.start();
                mHomePageSafeTxt.setText("正在扫描病毒");
                mHomePageVirusKillingImg.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.homepage_scan_image_alpha));
                mPresenter.startScan();
                break;
        }
    }

    @Override
    public void currentVirusProgress(double progress) {
        int scale = (int) (progress * 120 / 100);
        if ((int) progress == 99) {
            mHomePageVirusKillingImg.clearAnimation();
            AnimatorUtils.startRotateAnimator(getContext(),mHomePageVirusKillingImg,R.animator.homepage_scan_anim_rotate_image_finish);
        } else {
            mHomePageOneKeyScanView.setSearch(true, scale);
            mHomePageCircleProgressView.setStartEngle(scale);
            mHomePageDrawRadianView.setAnimation(scale);
        }
        mHomePageProgressTxt.setText((int) (progress / 3) + "%");
    }

    @Override
    public void currentMemoryProgress(final int progress) {
        mHomePageMobileAcceleratorImg.post(new Runnable() {
            @Override
            public void run() {
                int scale = (progress * 120 / 100)+ VIRUS_PROGRESS;
                if (progress == 100) {
                    mHomePageMobileAcceleratorImg.clearAnimation();
                    AnimatorUtils.startRotateAnimator(getContext(),mHomePageMobileAcceleratorImg,R.animator.homepage_scan_anim_rotate_image_finish);
                }
                mHomePageOneKeyScanView.setSearch(true,scale);
                mHomePageCircleProgressView.setStartEngle(scale);
                mHomePageDrawRadianView.setAnimation(scale);
                mHomePageProgressTxt.setText((progress/3+33) + "%");
            }
        });

    }

    @Override
    public void finishMemorySize(final String size) {
        mHomePageMobileAcceleratorTxt.post(new Runnable() {
            @Override
            public void run() {
                mHomePageMobileAcceleratorImg.setVisibility(View.GONE);
                mHomePageMobileAcceleratorTxt.setVisibility(View.VISIBLE);
                mHomePageMobileAcceleratorTxt.setText(size);
                mHomePageGarbageDisposalImg.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.homepage_scan_image_alpha));
            }
        });
    }

    @Override
    public void finishGarbageSize(final long size) {
        mHomePageGarbageDisposalTxt.post(new Runnable() {
            @Override
            public void run() {
                mHomePageGarbageDisposalImg.clearAnimation();
                AnimatorUtils.startRotateAnimator(getContext(),mHomePageGarbageDisposalImg,R.animator.homepage_scan_anim_rotate_image_finish);
                mHomePageGarbageDisposalImg.setVisibility(View.GONE);
                mHomePageGarbageDisposalTxt.setVisibility(View.VISIBLE);
                mHomePageGarbageDisposalTxt.setText(Formatter.formatFileSize(getContext(), size));
                Intent intent=new Intent(getContext(), CleanFinishActivity.class);
                startActivity(intent);
            }
        });
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
    public void updateWhenUnSafe(final int count) {
        mHomePageVirusKillTxt.post(new Runnable() {
            @Override
            public void run() {
                mHomePageVirusKillingImg.setVisibility(View.GONE);
                mHomePageVirusKillTxt.setVisibility(View.VISIBLE);
                mHomePageVirusKillTxt.setText(count + "");
                mHomePageMobileAcceleratorImg.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.homepage_scan_image_alpha));
            }
        });
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
