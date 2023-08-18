package com.example.newtabs;

import android.database.Cursor;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class showdata extends AppCompatActivity {
    private static final String TAG = "Cannot invoke method length() on null object";

    private ArrayList<String> meventnames = new ArrayList<>();
    dbhelper db;

    public void getdata(){
        String[] columns = {
                "eventname",
                "description",
                "date",
                "time"
        };
        db = new dbhelper(this);
        String target_column = "description";
        String[] desired_target = {"Study Plan"};
        Cursor cursor = db.getReadableDatabase().query("Eventdetails", columns, target_column, desired_target, null, null, null);
        while(cursor.moveToNext()){
            String current_item = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            meventnames.add(current_item);
        }
        cursor.close();
    }

}
