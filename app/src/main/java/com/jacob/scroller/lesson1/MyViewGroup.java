package com.jacob.scroller.lesson1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;

/**
 * Created by jacob-wj on 2015/4/21.
 */
public class MyViewGroup extends LinearLayout {
    private OverScroller mScroller;
    private boolean mFlag = false;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            Log.e("TAG",mScroller.getCurrX()+"");
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }

    public void beginScroll(){
        if (!mFlag){
            mScroller.startScroll(0,0,0,0,1000);
            mFlag = true;
        }else{
            mScroller.startScroll(0,0,-500,0,1000);
            mFlag = false;
        }
        invalidate();
    }
}
