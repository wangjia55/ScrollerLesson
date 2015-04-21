package com.jacob.scroller.lesson1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.jacob.scroller.R;

/**
 * Created by jacob-wj on 2015/4/21.
 */
public class LessonOneActivity extends FragmentActivity {

    private MyViewGroup mMyViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);
        mMyViewGroup = (MyViewGroup) findViewById(R.id.myviewgroup);

    }

    public void startScroll(View view){
        mMyViewGroup.beginScroll();
    }
}
