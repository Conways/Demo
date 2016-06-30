package com.conways.demo.home.firstmenu.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by user on 2016/6/30.
 */
public class ChartLine extends View {

    private int topPadding = dp2px(5);
    private int leftPadding = dp2px(20);
    private int bottomPadding = dp2px(20);
    private int rightPadding = dp2px(5);

    private int mWith;
    private int mHeight;

    private float max = 100;
    private float min = 0;

    private int verticalCount = 5;
    private int horizontalCont = 7;

    private Paint bgPaint;
    private Paint paddingBgPaint;
    private Paint xPaint;
    private Paint yPaint;
    private Paint xTextPaint;
    private Paint yTextPaint;
    private Paint gridPaint;
    private Paint pointBgPaint;
    private Paint pointPaint;
    private Paint linePaint;


    private List<List<Data>> datas;
    private int offSet = 0;
    private int moveoffset = 0;


    public ChartLine(Context context) {
        super(context);
        initPaint();
    }

    public ChartLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ChartLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        bgPaint = new Paint();
        bgPaint.setColor(0xffffffff);

        paddingBgPaint = new Paint();
        paddingBgPaint.setColor(0xffffffff);

        xPaint = new Paint();
        xPaint.setStrokeWidth(dp2px(1));
        xPaint.setColor(0xffed6d35);

        yPaint = new Paint();
        yPaint.setColor(0xffed6d35);
        yPaint.setStrokeWidth(dp2px(1));

        xTextPaint = new Paint();
        xTextPaint.setColor(0xffed6d35);
        xTextPaint.setTextSize(dp2px(10));
        xTextPaint.setAntiAlias(true);

        yTextPaint = new Paint();
        yTextPaint.setColor(0xffed6d35);
        yTextPaint.setTextSize(dp2px(10));
        yTextPaint.setAntiAlias(true);

        gridPaint = new Paint();
        gridPaint.setStrokeWidth(dp2px(0.5f));
        gridPaint.setColor(0x80ed6d35);

        pointBgPaint = new Paint();
        pointBgPaint.setColor(0xffed6d35);
        pointBgPaint.setAntiAlias(true);


        pointPaint = new Paint();
        pointPaint.setColor(0xffffffff);
        pointPaint.setAntiAlias(true);

        linePaint=new Paint();
        linePaint.setColor(0xffed6d35);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(dp2px(0.5f));
    }

    public void setDatas(List<List<Data>> datas) {
        if (datas == null) {
            return;
        }
        this.datas = datas;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        mWith = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawY(canvas);
        drawPoint(canvas);
        drawHorizontalBg(canvas);
        drawX(canvas);
        drawValue(canvas);
    }

    private void drawBg(Canvas canvas) {
        canvas.drawRect(0, 0, mWith, topPadding, paddingBgPaint);
        canvas.drawRect(0, mHeight - bottomPadding, mWith, mHeight, paddingBgPaint);
        canvas.drawRect(leftPadding, topPadding, mWith-rightPadding, mHeight - bottomPadding, bgPaint);
    }
    private void drawHorizontalBg(Canvas canvas) {
        canvas.drawRect(0, topPadding, leftPadding, mHeight, paddingBgPaint);
        canvas.drawRect(mWith-rightPadding, 0, mWith, mHeight, paddingBgPaint);
    }


    private void drawX(Canvas canvas) {
        canvas.drawLine(leftPadding, topPadding, leftPadding, mHeight - bottomPadding, xPaint);
    }
    private void drawY(Canvas canvas) {
        canvas.drawLine(leftPadding, mHeight - bottomPadding, mWith - rightPadding, mHeight - bottomPadding,
                yPaint);
    }

    private void drawValue(Canvas canvas) {
        int verticalItem = (mHeight - topPadding - bottomPadding) / verticalCount;
        int horizontalItem = (mWith - leftPadding - rightPadding) / horizontalCont;

        //绘制背景格子
        for (int i = 1; i < verticalCount; i++) {
            int yTemp = mHeight - bottomPadding - i * verticalItem;
            canvas.drawLine(leftPadding, yTemp, mWith - rightPadding, yTemp, gridPaint);
        }
        for (int i = 1; i < horizontalCont; i++) {
            int xTemp = leftPadding + i * horizontalItem;
            canvas.drawLine(xTemp, topPadding, xTemp, mHeight - bottomPadding, gridPaint);
        }
        //绘制数值

        int yValueTemp = (int) (max - min) / verticalCount;
        for (int i = 0; i < verticalItem; i++) {
            String value = i * yValueTemp + "";
            Rect rect = new Rect();
            xTextPaint.getTextBounds(value, 0, value.length(), rect);
            int x = leftPadding - rect.width() - dp2px(3);
            int y = mHeight - i * verticalItem - bottomPadding + rect.height() / 2;
            canvas.drawText(i * yValueTemp + "", x, y, xTextPaint);
        }

    }

    private void drawPoint(Canvas canvas) {

        if (datas == null) {
            return;
        }

        int horizontalItem = (mWith - leftPadding - rightPadding) / horizontalCont;

        for (int i = 0; i < datas.size(); i++) {
            for (int j = 0; j < datas.get(i).size(); j++) {
                Data data = datas.get(i).get(j);
                float x = mWith  + offSet + moveoffset - j * horizontalItem;
                float y = topPadding + (mHeight - topPadding - bottomPadding) * (max - data.getValue()) /
                        (max - min);

                if (j < datas.get(i).size() - 1) {
                    float x1 = mWith  + offSet + moveoffset - (j + 1) * horizontalItem;
                    float y1 = topPadding + (mHeight - topPadding - bottomPadding) * (max - datas.get
                            (i).get(j + 1).getValue()) / (max - min);
                    canvas.drawLine(x, y, x1, y1, linePaint);
                }


                canvas.drawCircle(x, y, dp2px(4f), pointBgPaint);
                canvas.drawCircle(x, y, dp2px(3f), pointPaint);

            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
