package com.conways.demo.home.firstmenu.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by user on 2016/6/30.
 */
public class ChartLine extends View {

    private int topPadding = px2Dp(60);
    private int leftPadding = px2Dp(200);
    private int bottomPadding = px2Dp(200);
    private int rightPadding = px2Dp(60);

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
    private Paint pointPaint;


    private List<List<Data>> datas;
    private int offSet=0;
    private int moveoffset=0;


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
        xPaint.setStrokeWidth(px2Dp(3));
        xPaint.setColor(0xffed6d35);

        yPaint = new Paint();
        yPaint.setColor(0xffed6d35);
        yPaint.setStrokeWidth(px2Dp(3));

        xTextPaint = new Paint();
        xTextPaint.setColor(0xffed6d35);
        xTextPaint.setTextSize(px2Dp(80));
        xTextPaint.setAntiAlias(true);

        yTextPaint = new Paint();
        yTextPaint.setColor(0xffed6d35);
        yTextPaint.setTextSize(px2Dp(80));
        yTextPaint.setAntiAlias(true);

        gridPaint = new Paint();
        gridPaint.setStrokeWidth(px2Dp(1));
        gridPaint.setColor(0x80ed6d35);

        pointPaint=new Paint();
        pointPaint.setColor(0xffed6d35);
        pointPaint.setAntiAlias(true);
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
        drawXY(canvas);
        drawValue(canvas);
        drawPoint(canvas);
    }

    private void drawBg(Canvas canvas) {
        canvas.drawRect(0, 0, mWith, topPadding, paddingBgPaint);
        canvas.drawRect(0, topPadding, leftPadding, mHeight, paddingBgPaint);
        canvas.drawRect(0, mHeight - bottomPadding, mWith, mHeight, paddingBgPaint);
        canvas.drawRect(leftPadding, topPadding, mWith, mHeight - bottomPadding, bgPaint);
    }

    private void drawXY(Canvas canvas) {
        canvas.drawLine(leftPadding, topPadding, leftPadding, mHeight - bottomPadding, xPaint);
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
            int x = leftPadding - rect.width() - px2Dp(20);
            int y = mHeight - i * verticalItem - bottomPadding + rect.height() / 2;
            canvas.drawText(i * yValueTemp + "", x, y, xTextPaint);
        }

    }

    private void drawPoint(Canvas canvas){
        canvas.drawCircle(mWith/2,mHeight/2,px2Dp(60),pointPaint);

        if (datas==null){
            return;
        }

        for (int i = 0; i <datas.size() ; i++) {
            for (int j = 0; j <datas.get(i).size(); j++) {


            }
        }


    }

    private int px2Dp(float px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
