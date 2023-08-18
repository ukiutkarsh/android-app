package com.example.newtabs;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class deletedata extends AppCompatActivity {

    private static final String LOG_TAG =
            deletedata.class.getSimpleName();

    EditText studyplan_name;
    Button delete;
    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Deletion Initiated");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);

        studyplan_name = findViewById(R.id.deletename);
        delete = findViewById(R.id.deletebtn);
        db = new dbhelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = studyplan_name.getText().toString();
                Boolean checkudeletedata = db.deletedata(nameTXT);
                if (checkudeletedata == true)
                    Toast.makeText(deletedata.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(deletedata.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
