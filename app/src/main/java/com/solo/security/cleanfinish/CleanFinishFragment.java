package com.solo.security.cleanfinish;


import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.solo.security.R;
import com.solo.security.common.BaseFragment;
import com.solo.security.utils.AnimatorUtils;

import butterknife.BindView;

public class CleanFinishFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.common_clean_finish_shield_img)
    ImageView mCleanFinishShieldImg;

    @BindView(R.id.common_clean_finish_ok_img)
    ImageView mCleanFinishOkImg;

    @BindView(R.id.clean_finish_viruses_cardvew)
    CardView mCleanFinishVirusesVew;

    @BindView(R.id.clean_finish_garbage_cardvew)
    CardView mCleanFinishGarbageVew;

    @BindView(R.id.clean_finish_memory_cardvew)
    CardView mCleanFinishMemoryVew;

    private CleanFinishPresenter mPresenter;

    public CleanFinishFragment() {
        // Required empty public constructor
    }

    public static CleanFinishFragment newInstance(String param1, String param2) {
        CleanFinishFragment fragment = new CleanFinishFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewID() {
        return R.layout.fragment_clean_finish;
    }

    @Override
    protected void initViewsAndData(View view) {
        mCleanFinishVirusesVew.setOnClickListener(this);
        mCleanFinishGarbageVew.setOnClickListener(this);
        mCleanFinishMemoryVew.setOnClickListener(this);

       /* mCleanFinishShieldImg.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorUtils.startRotateAnimator(getContext(),mCleanFinishShieldImg,R.animator.homepage_scan_anim_rotate_finish_shield);
            }
        },300);
        mCleanFinishOkImg.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCleanFinishOkImg.setVisibility(View.VISIBLE);
                mCleanFinishShieldImg.setVisibility(View.GONE);
                AnimatorUtils.startRotateAnimator(getContext(),mCleanFinishOkImg,R.animator.homepage_scan_anim_rotate_finish_ok);
            }
        },800);*/

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clean_finish_viruses_cardvew:
                break;
            case R.id.clean_finish_garbage_cardvew:
                break;
            case R.id.clean_finish_memory_cardvew:
                break;
        }
    }
}
