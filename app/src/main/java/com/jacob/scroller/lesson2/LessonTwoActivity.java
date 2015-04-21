package com.jacob.scroller.lesson2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.jacob.scroller.R;
import com.jacob.scroller.lesson1.MyViewGroup;

/**
 * Created by jacob-wj on 2015/4/21.
 */
public class LessonTwoActivity extends FragmentActivity {

    private MultiScreenLayout multiLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);

        multiLayout = (MultiScreenLayout) findViewById(R.id.multiLayout);
    }

    public void last(View view){
        multiLayout.lastScreen();
    }
    public void next(View view){
        multiLayout.nextScreen();
    }
}
