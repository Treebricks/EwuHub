package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.treebricks.ewuhub.R;

public class ApplicationHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sort_course)
        {
            //Toast.makeText(this, "Sort Course Pressed", Toast.LENGTH_SHORT).show();
            Intent sortCourse = new Intent(this, SortCourseHome.class);
            startActivity(sortCourse);
        }
        else if (id == R.id.nav_result)
        {
            Toast.makeText(this, "Result Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_academic_calender)
        {
            Toast.makeText(this, "Academic Calender Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_notice_board)
        {
            Toast.makeText(this, "Notice Board Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_settings)
        {
            Toast.makeText(this, "Settings Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_about)
        {
            Toast.makeText(this, "About Pressed", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}