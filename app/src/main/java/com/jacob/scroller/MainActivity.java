package com.jacob.scroller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jacob.scroller.lesson1.LessonOneActivity;
import com.jacob.scroller.lesson2.LessonTwoActivity;
import com.jacob.scroller.lesson3.MultiScreenActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void lessonOne(View view){
        Intent intent = new Intent(this, LessonOneActivity.class);
        startActivity(intent);
    }

    public void lessonTwo(View view){
        Intent intent = new Intent(this, LessonTwoActivity.class);
        startActivity(intent);
    }


    public void lessonThree(View view){
        Intent intent = new Intent(this, MultiScreenActivity.class);
        startActivity(intent);
    }

}
