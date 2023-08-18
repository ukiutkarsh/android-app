package com.example.newtabs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_1 extends Fragment{

    private ArrayList<String> meventnames = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();
    private ArrayList<String> mtime = new ArrayList<>();
    private Boolean cond2 = true;

    dbhelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        if(cond2) {
            String[] columns = {
                    "eventname",
                    "description",
                    "date",
                    "time"
            };
            db = new dbhelper(view.getContext());
            String target_column = "description" + " = ?";
            String[] desired_target = {"Study Plan"};
            Cursor cursor = db.getReadableDatabase().query("Eventdetails", columns, target_column, desired_target, null, null, null);
            while (cursor.moveToNext()) {
                String current_eventname = cursor.getString(0);
                String current_date = cursor.getString(2);
                String current_time = cursor.getString(3);
                meventnames.add(current_eventname);
                mdate.add(current_date);
                mtime.add(current_time);
            }
            cursor.close();
            cond2 = false;
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_studyplan);
        recyclerview_adapter_studyplan adapter_studyplan = new recyclerview_adapter_studyplan(meventnames, mdate, mtime, view.getContext());
        recyclerView.setAdapter(adapter_studyplan);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }
}

