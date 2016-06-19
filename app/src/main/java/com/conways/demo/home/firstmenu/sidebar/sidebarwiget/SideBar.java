package com.conways.demo.home.firstmenu.sidebar.sidebarwiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Conways on 2016/6/18.
 */
public class SideBar extends View {

    private static final int paddingTop = 20;
    private static final int paddingBottom = 20;
    private int mChoose = -1;

    private Paint paintBg;
    private Paint paintText;
    private int mHeight;
    private int mWith;
    private String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private OnSideBarTouchListener onSideBarTouchListener;


    public SideBar(Context context) {
        super(context);
        init();
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paintBg = new Paint();
        paintBg.setColor(0x00ff00ff);
        paintText = new Paint();
        paintText.setTextSize(20f);
        paintText.setColor(0xffed6d35);


    }


    public void setOnSideBarTouchListener(OnSideBarTouchListener onSideBarTouchListener) {
        this.onSideBarTouchListener = onSideBarTouchListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWith = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWith, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawText(canvas);
    }

    private void drawBg(Canvas canvas) {
        canvas.drawRect(0, 0, mWith, mHeight, paintBg);

    }

    private void drawText(Canvas canvas) {
        int unitHeight = mHeight / data.length;
        for (int i = 0; i < data.length; i++) {
            Rect rect = new Rect();
            paintBg.getTextBounds(data[i], 0, data[i].length(), rect);
            int x = (mWith - rect.width()) / 2;
            int y = i * unitHeight + (unitHeight + rect.height()) / 2;
            canvas.drawText(data[i], x, y, paintText);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = mChoose;
        final int newChoose = (int) (y / mHeight * data.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                mChoose = -1;
                if (onSideBarTouchListener != null) {
                    onSideBarTouchListener.onLetterTouching(false);
                }
                break;
            default:
                if (oldChoose != newChoose) {
                    if (newChoose >= 0 && newChoose < data.length) {
                        mChoose = newChoose;
                        if (onSideBarTouchListener != null) {
                            onSideBarTouchListener.onLetterChanged(data[newChoose], mChoose,
                                    mHeight / data.length);
                        }
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    if (onSideBarTouchListener != null) {
                        onSideBarTouchListener.onLetterTouching(false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下调用 onLetterDownListener
                    if (onSideBarTouchListener != null) {
                        onSideBarTouchListener.onLetterTouching(true);
                    }
                }

                break;
        }
        return true;
    }
}
