package com.solo.security.homepage.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.solo.security.R;

/**
 * Created by haipingguo on 16-11-18.
 */
public class HomePageOneKeyScanView extends View {
    private Context mContext;
    private Paint mPaint;
    private Bitmap mRadarScanBmp, mScanBuddleBmp, mScanOutWhiteBmp,mResultFinishBmp,mResultSafeBmp;
    int mWidth, mHeight;
    int Radius;
    private boolean isSearching;
    private int mOffsetArgs = 0;// 扫描运动偏移量参数

    public HomePageOneKeyScanView(Context context) {
        this(context,null);
    }

    public HomePageOneKeyScanView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomePageOneKeyScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.common_background));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Radius = mWidth / 2;
        mRadarScanBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_radar);
        mScanBuddleBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_buddle);
        mScanOutWhiteBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_buddle_outer_white_edge);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        if (isSearching) {
            canvas.rotate(mOffsetArgs, 0, 0);// 绘制旋转角度,参数一：角度;参数二：x中心;参数三：y中心.
            canvas.drawBitmap(mRadarScanBmp, -mRadarScanBmp.getWidth() / 2, -mRadarScanBmp.getHeight() / 2, null);
        } else {
            canvas.drawBitmap(mScanBuddleBmp, -mScanBuddleBmp.getWidth() / 2, -mRadarScanBmp.getHeight() / 2, null);
            canvas.drawBitmap(mScanOutWhiteBmp, -mScanOutWhiteBmp.getWidth() / 2, -mScanOutWhiteBmp.getHeight() / 2, null);
            mPaint.setColor(getResources().getColor(R.color.common_background));
            mPaint.setTextSize(100);
            canvas.drawText("一键扫描", -200, 30, mPaint);
        }

    }

    public void setSearch(boolean search, int offsetArgs) {
        isSearching = search;
        mOffsetArgs = offsetArgs;
        invalidate();
    }


    public void scale() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.3f);
        objectAnimator1.setDuration(200);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.3f);
        objectAnimator2.setDuration(200);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(this, "scaleX", 1.3f, 1f);
        objectAnimator3.setDuration(200);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(this, "scaleY", 1.3f, 1f);
        objectAnimator4.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4);
        animatorSet.setStartDelay(20);
        animatorSet.start();
    }

}
