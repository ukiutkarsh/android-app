package com.example.newtabs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "CalendarActivity";

    private Toolbar toolbar2;
    private DrawerLayout drawerLayout2;
    private NavigationView navigationView2;

    private CompactCalendarView mCalendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    private int Study_plans;
    private int assignments;
    private int exams;
    private int lectures;

    private TextView no_of_studyplans;
    private TextView no_of_assignments;
    private TextView no_of_exams;
    private TextView no_of_lectures;
    private TextView TitleDate;
    private TextView Title;

    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        no_of_studyplans = (TextView) findViewById(R.id.No_of_studyplan);
        no_of_assignments = (TextView) findViewById(R.id.No_of_assignment);
        no_of_exams = (TextView) findViewById(R.id.No_of_exam);
        no_of_lectures = (TextView) findViewById(R.id.No_of_lecture);
        TitleDate = (TextView) findViewById(R.id.TitleDate);
        Title = (TextView) findViewById(R.id.MonthTitle);

        toolbar2 = findViewById(R.id.main_toolbar2);
        setSupportActionBar(toolbar2);

        drawerLayout2 = findViewById(R.id.my_drawer_layout2);
        navigationView2 = findViewById(R.id.nav_view2);

        ActionBarDrawerToggle actionBarDrawerToggle2 = new ActionBarDrawerToggle(
                this,
                drawerLayout2,
                toolbar2,
                R.string.nav_open,
                R.string.nav_close
        );

        drawerLayout2.addDrawerListener(actionBarDrawerToggle2);
        actionBarDrawerToggle2.syncState();
        navigationView2.setNavigationItemSelectedListener(this);

        mCalendarView = (CompactCalendarView) findViewById(R.id.calendarView);
        mCalendarView.setUseThreeLetterAbbreviation(true);

        String[] columns = {
                "eventname",
                "description",
                "date",
                "time"
        };

        db = new dbhelper(this);

        Cursor cursor2 = db.getReadableDatabase().query(true, "Eventdetails", columns, null, null, "date", null, null, null);
        if(cursor2 == null){
            Log.d(TAG, "Error");
        } else{
            if (cursor2.moveToFirst()){
                do{
                    String date = cursor2.getString(cursor2.getColumnIndexOrThrow("date"));
                    Log.d(TAG, cursor2.getString(cursor2.getColumnIndexOrThrow("date")));
                    Log.d(TAG, cursor2.getString(cursor2.getColumnIndexOrThrow("description")));
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("dd.MM.yyyy").parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long temp = date1.getTime();
                    Event event_temp = new Event(Color.GREEN, temp, "Event");
                    mCalendarView.addEvent(event_temp);
                }while(cursor2.moveToNext());
            }
        }

        mCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(java.util.Date dateClicked) {
                Context context = getApplicationContext();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                String format = formatter.format(dateClicked);
                Log.d(TAG, format);

                db = new dbhelper(context);
                String target_column = "date" + " = ?";
                String[] desired_target = {format};
                Cursor cursor = db.getReadableDatabase().query("Eventdetails", columns, target_column, desired_target, null, null, null);

                Study_plans = 0;
                exams = 0;
                lectures = 0;
                assignments = 0;

                while (cursor.moveToNext()) {
                    Log.d(TAG, cursor.getString(1));
                    if (cursor.getString(1).equalsIgnoreCase("Study Plan")){
                        Study_plans=(Study_plans+1);
                    }
                    else if (cursor.getString(1).equalsIgnoreCase("Exams")){
                        exams=(exams+1);
                    }
                    else if (cursor.getString(1).equalsIgnoreCase("Lectures")){
                        lectures=(lectures+1);
                    }
                    else if (cursor.getString(1).equalsIgnoreCase("Assignments")){
                        assignments=(assignments+1);
                    }
                }
                cursor.close();

                no_of_studyplans.setText(String.valueOf(Study_plans));
                no_of_assignments.setText(String.valueOf(assignments));
                no_of_exams.setText(String.valueOf(exams));
                no_of_lectures.setText(String.valueOf(lectures));
                TitleDate.setText(format);
            }

            @Override
            public void onMonthScroll(java.util.Date firstDayOfNewMonth) {
                Log.d(TAG, dateFormatMonth.format(firstDayOfNewMonth));
                Title.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }

    public void Home(MenuItem view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

