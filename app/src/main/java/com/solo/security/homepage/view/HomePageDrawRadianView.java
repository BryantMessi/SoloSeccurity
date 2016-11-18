package com.solo.security.homepage.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.solo.security.R;

/**
 * Created by haipingguo on 16-11-18.
 */
public class HomePageDrawRadianView extends View {

    private static final int DEFAULT_MIN_WIDTH = 200; //View默认大小

    private float currentAngle=-90;

    private float startAngle = -90;//开始绘制的角度
    int mWidth, mHeight;
    public HomePageDrawRadianView(Context context) {
        this(context,null);
    }

    public HomePageDrawRadianView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomePageDrawRadianView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /**
         * 计算画布绘制圆弧填入的 top left bottom right 值,
         * 这里注意给的值要在圆弧的一半位置, 绘制的时候参数是从中间开始绘制
         */
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        Paint paintDefalt = new Paint();
        paintDefalt.setColor(getResources().getColor(R.color.common_background));
        paintDefalt.setStrokeCap(Paint.Cap.SQUARE);


        RectF oval1=new RectF(-mWidth/4-5,-mWidth/4-5,mWidth/4+5,mWidth/4+5);

        paintDefalt.setStyle(Paint.Style.STROKE);//设置填充样式
        paintDefalt.setAntiAlias(true);//抗锯齿功能
        paintDefalt.setStrokeWidth(10);//设置画笔宽度

        canvas.drawCircle(0,0, (float) (mWidth/4), paintDefalt);

        Paint paintCurrent = new Paint();
        paintCurrent.setStyle(Paint.Style.STROKE);//设置填充样式
        paintCurrent.setAntiAlias(true);//抗锯齿功能
        paintCurrent.setStrokeWidth(5);//设置画笔宽度
        paintCurrent.setStrokeCap(Paint.Cap.SQUARE);
        paintCurrent.setColor(getResources().getColor(R.color.common_background));
        canvas.drawArc(oval1, startAngle, currentAngle, false, paintCurrent);
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);//设置填充样式
        paintCircle.setAntiAlias(true);//抗锯齿功能
        paintCircle.setColor(Color.WHITE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }
    /**
     *
     * @param origin
     * @param
     * @return
     */
    private int measure(int origin){
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);//得到模式
        int specSize = MeasureSpec.getSize(origin);//得到尺寸

        switch (specMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                result = Math.min(result, specSize);
                if (result == 0) {
                    result = DEFAULT_MIN_WIDTH;
                }
                break;
        }

        return result;
    }
    /**
     * 为进度设置动画
     * @param
     * @param current
     */
    public void setAnimation(float current) {
        currentAngle=current;
        invalidate();
    }
}

