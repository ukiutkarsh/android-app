package com.example.newtabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class insertdata extends AppCompatActivity {
    EditText eventname, description, date, time;
    Button insert;
    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdata);

        eventname = findViewById(R.id.eventname);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        insert = findViewById(R.id.insert);
        db = new dbhelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventnametxt = eventname.getText().toString();
                String descriptiontxt = description.getText().toString();
                String datetxt = date.getText().toString();
                String timetxt = time.getText().toString();

                Boolean checkinsertdata = db.insertdata(eventnametxt, descriptiontxt, datetxt, timetxt);

                if(checkinsertdata==true)
                    Toast.makeText(insertdata.this, "New Event Created", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(insertdata.this, "New Event could not be Created", Toast.LENGTH_SHORT).show();
            }
        });
    }
}