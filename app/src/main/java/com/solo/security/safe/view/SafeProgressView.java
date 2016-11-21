package com.solo.security.safe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.solo.security.R;

/**
 * Created by win01 on 2016/11/19.
 */

public class SafeProgressView extends View {

    private Paint mProgressPaint;
    //画笔粗细
    private float mStrokeWidth;
    private final RectF mBounds = new RectF();
    private final int START_ANGLE = 130;

    //回弹的进度，目前用不到
    private int mBounceProgress = 0;
    private Bitmap mSafeScaleBmp, mSafeOutCircleBmp, mSafePointBmp;
    private int mOffsetArgs = 0;
    int mWidth, mHeight;

    public SafeProgressView(Context context) {
        this(context, null);
    }

    public SafeProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SafeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mStrokeWidth = getResources().getDimension(R.dimen.boost_progress_stroke_width);
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth(mStrokeWidth);
        mProgressPaint.setColor(Color.WHITE);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds.left = mStrokeWidth / 2f + .5f;
        mBounds.right = w - mStrokeWidth / 2f - .5f;
        mBounds.top = mStrokeWidth / 2f + .5f;
        mBounds.bottom = h - mStrokeWidth / 2f - .5f;

        mSafeScaleBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_radar);
        mSafeOutCircleBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_buddle);
        mSafePointBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.homepage_scan_buddle_outer_white_edge);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawArc(mBounds, START_ANGLE, (float) (mBounceProgress * 2.7), false, mProgressPaint);
        canvas.rotate(mOffsetArgs, 0, 0);
        canvas.drawBitmap(mSafeScaleBmp, -mSafeScaleBmp.getWidth() / 2, -mSafeScaleBmp.getHeight() / 2, null);
        canvas.drawBitmap(mSafeOutCircleBmp, -mSafeOutCircleBmp.getWidth() / 2, -mSafeOutCircleBmp.getHeight() / 2, null);
        canvas.drawBitmap(mSafePointBmp, -mSafePointBmp.getWidth() / 2, -mSafePointBmp.getHeight() / 2, null);
    }

    public void setBounceProgress(int progress) {
        this.mBounceProgress = progress;
        this.mOffsetArgs = (int) (3.6 * progress);
        invalidate();
    }
}

