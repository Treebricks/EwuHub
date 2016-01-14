package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.treebricks.ewuhub.R;

public class SortCourseHome extends AppCompatActivity
{


    public static final String NUMBEROFCOURSES = "NUMBEROFCOURSES";
    private int totalNumberOfCourse = 0;
    private String totalNumberOfCourseString = null;
    EditText totalCourseEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_course_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                //Get total number of course from edit text
                totalCourseEditText = (EditText) findViewById(R.id.total_course_edit_text);
                totalNumberOfCourseString = totalCourseEditText.getText().toString();

                if(!totalNumberOfCourseString.equals(""))
                {
                    totalNumberOfCourse = Integer.parseInt(totalNumberOfCourseString);
                }

                if(totalNumberOfCourse == 3 || totalNumberOfCourse == 4)
                {
                    Intent courseInputIntent = new Intent(SortCourseHome.this, CourseInput.class);

                    // Send data to other activity using bundle
                    Bundle infoBundle = new Bundle();
                    infoBundle.putInt(NUMBEROFCOURSES, totalNumberOfCourse);
                    courseInputIntent.putExtras(infoBundle);

                    startActivity(courseInputIntent);
                }
                else
                {
                    Toast.makeText(SortCourseHome.this, "You Inputed "+ String.valueOf(totalNumberOfCourse) +". Please, Input 3 or 4 to go next!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putInt(NUMBEROFCOURSES, totalNumberOfCourse);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalNumberOfCourse = savedInstanceState.getInt(NUMBEROFCOURSES);
    }

}