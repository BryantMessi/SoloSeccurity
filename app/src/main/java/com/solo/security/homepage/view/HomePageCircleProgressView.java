package com.solo.security.homepage.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.solo.security.R;

/**
 * Created by haipingguo on 16-11-18.
 */
public class HomePageCircleProgressView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mStartEngle;
    private boolean isCircle;
    private int mWidth, mHeight;
    private Context mContext;

    public HomePageCircleProgressView(Context context) {
        this(context,null);
    }

    public HomePageCircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomePageCircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext=context;
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.common_background));
        mPaint.setStrokeWidth(50);
        //切割线条
        PathEffect effect = new DashPathEffect(new float[]{7, 14}, 1);
        mPaint.setPathEffect(effect);
    }

    public void getArc(Canvas canvas, float o_x, float o_y, float radius,
                       float startangel, float endangel, Paint paint) {
        RectF rect = new RectF(o_x - radius, o_y - radius, o_x + radius, o_y + radius);
        Path path = new Path();
        path.moveTo(o_x, o_y);

        path.lineTo((float) (o_x + radius * Math.cos(startangel * Math.PI / 180))
                , (float) (o_y + radius * Math.sin(startangel * Math.PI / 180)));
        path.lineTo((float) (o_x + radius * Math.cos(endangel * Math.PI / 180))
                , (float) (o_y + radius * Math.sin(endangel * Math.PI / 180)));
        path.addArc(rect, startangel, endangel - startangel);
        canvas.clipPath(path);
        canvas.drawCircle(o_x, o_y, radius, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = 0;
        float y = 0;
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(getResources().getColor(R.color.thirty_five_black));

        getArc(canvas, x, y, 340, 0, 360, mPaint);

        mPaint.setColor(getResources().getColor(R.color.common_background));
        getArc(canvas, x, y, 340, -90, mStartEngle - 90, mPaint);

    }

    public void setStartEngle(float startEngle) {
        mStartEngle = startEngle;
        isCircle = true;
        invalidate();
    }

    public static int pxTodip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }

}
