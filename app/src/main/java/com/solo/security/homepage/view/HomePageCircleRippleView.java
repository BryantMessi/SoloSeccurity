package com.solo.security.homepage.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.solo.security.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haipingguo on 16-11-18.
 */
public class HomePageCircleRippleView extends RelativeLayout {
    private int mRippleColor;
    private int mRippleRadius = 400;
    private int mAnimDuration = 1000;
    private int mAnimDelay;
    private int mRippleViewNums = 1;

    private LayoutParams mRippleViewParams;

    private Paint mPaint = new Paint();
    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private List<Animator> mAnimatorList = new ArrayList<>();

    public HomePageCircleRippleView(Context context) {
        this(context,null);
    }

    public HomePageCircleRippleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomePageCircleRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initAnimDelay();
        initPaint();
        initParams();
        initAnimSet();
        for (int i = 0; i < mRippleViewNums; i++) {
            RippleView rippleView_1 = new RippleView(context);
            addView(rippleView_1, mRippleViewParams);
            addAnimRippleView(rippleView_1, 0);
        }
        mAnimatorSet.playTogether(mAnimatorList);
        mAnimatorSet.start();

    }


    private void initAnimDelay() {
        mAnimDelay = mAnimDuration / mRippleViewNums;
    }

    private void addAnimRippleView(RippleView rippleView, int i) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleView, "scaleX", 1.0f, 1.5f);
        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleXAnimator.setStartDelay(i * mAnimDelay);
        scaleXAnimator.setDuration(mAnimDuration);
        mAnimatorList.add(scaleXAnimator);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleView, "scaleY", 1.0f, 1.5f);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleYAnimator.setStartDelay(i * mAnimDelay);
        scaleYAnimator.setDuration(mAnimDuration);
        mAnimatorList.add(scaleYAnimator);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleView, "alpha", 1.0f, 0f);
        alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
        alphaAnimator.setStartDelay(i * mAnimDelay);
        alphaAnimator.setDuration(mAnimDuration);
        mAnimatorList.add(alphaAnimator);
    }

    private void initAnimSet() {
        mAnimatorSet.setDuration(mAnimDuration);
        mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
    }


    private void initParams() {
        mRippleViewParams = new LayoutParams(mRippleRadius * 2, mRippleRadius * 2);
        mRippleViewParams.addRule(CENTER_IN_PARENT, TRUE);
    }

    private void initPaint() {
        mRippleColor = getResources().getColor(R.color.common_background);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mRippleColor);
    }
    public void scale() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.08f);
        objectAnimator1.setDuration(200);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.08f);
        objectAnimator2.setDuration(200);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(this, "scaleX", 1.08f, 1f);
        objectAnimator3.setDuration(200);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(this, "scaleY", 1.08f, 1f);
        objectAnimator4.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4);
        animatorSet.setStartDelay(20);
        animatorSet.start();
    }
    private class RippleView extends View {

        private Bitmap mRadarExternalWaveBmp;
        private int mWidth, mHeight;
        public RippleView(Context context) {
            this(context,null);
        }

        public RippleView(Context context, AttributeSet attrs) {
            this(context,attrs,0);
        }

        public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mWidth = w;
            mHeight = h;
        }
        private void init() {
            mRadarExternalWaveBmp= BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_buddle_external_wave);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.translate(mWidth / 2, mHeight / 2);
            canvas.drawBitmap(mRadarExternalWaveBmp, -mRadarExternalWaveBmp.getWidth() / 2, -mRadarExternalWaveBmp.getHeight() / 2, null);
        }
    }
}

