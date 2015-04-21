package com.jacob.scroller.lesson2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by jacob-wj on 2015/4/21.
 */
public class MultiScreenLayout extends ViewGroup {

    private int mCurrentScreen = 0;

    private Scroller mScroller;

    private int mWidth = 0;

    private States mState = States.reset;

    private float mLastX;

    private float mTouchSlop;

    private VelocityTracker mVelocity;

    public static final int SNAP_VELOCITY = 600;

    enum States{
        reset,
        scrolling;
    }

    public MultiScreenLayout(Context context) {
        this(context,null);
    }

    public MultiScreenLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiScreenLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mWidth = getScreenWidth();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initLayout();
    }

    private void initLayout() {
        LinearLayout linearLayout1 = new LinearLayout(getContext());
        linearLayout1.setBackgroundColor(Color.RED);
        addView(linearLayout1);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setBackgroundColor(Color.GREEN);
        addView(linearLayout2);

        LinearLayout linearLayout3 = new LinearLayout(getContext());
        linearLayout3.setBackgroundColor(Color.BLUE);
        addView(linearLayout3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child  = getChildAt(i);
            child.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed){
            int childCount = getChildCount();
            int right = mWidth;
            for (int i = 0; i < childCount; i++) {
                View view  = getChildAt(i);
                view.layout(getPaddingLeft()+right*i,getPaddingTop(),(i+1)*right,getMeasuredHeight());
            }
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();

        if (mVelocity == null){
            mVelocity = VelocityTracker.obtain();
        }
        mVelocity.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    mState = States.reset;
                }
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (mLastX -x );
                scrollBy(deltaX,0);
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
                VelocityTracker velocityTracker = mVelocity;
                velocityTracker.computeCurrentVelocity(1000);

                float velox = velocityTracker.getXVelocity();

                if (velox > SNAP_VELOCITY && mCurrentScreen>=0 ){
                    //slide left to right
                    lastScreen();
                }else if(velox < -SNAP_VELOCITY && mCurrentScreen<getChildCount()){
                    nextScreen();
                }else{
                    snapToDestination();
                }
                Log.e("TAG",velox+"");
                break;
        }


        return true;

    }

    private void snapToDestination() {
      mCurrentScreen = (getScrollX() + getWidth() / 2 ) / getWidth() ;

    }

    public void lastScreen() {
        int deltaX = mCurrentScreen*mWidth -getScrollX();
        mScroller.startScroll(mCurrentScreen* mWidth-deltaX,0,-mWidth,0,1000);
        mCurrentScreen--;
        invalidate();
        requestLayout();
    }

    public void nextScreen() {
        int deltaX = mCurrentScreen*mWidth -getScrollX();
        mScroller.startScroll(mCurrentScreen* mWidth-deltaX,0, mWidth,0,1000);
        mCurrentScreen++;
        invalidate();
        requestLayout();
    }


    private int getScreenWidth(){
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return  metrics.widthPixels;
    }
}
