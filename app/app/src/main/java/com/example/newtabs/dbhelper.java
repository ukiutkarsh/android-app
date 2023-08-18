package com.example.newtabs;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

import androidx.annotation.Nullable;
import android.database.Cursor;

public class dbhelper extends SQLiteOpenHelper {
    public dbhelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Eventdetails(eventname TEXT primary key, description TEXT, date TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Eventdetails");
    }

    public Boolean insertdata(String eventname, String description, String date, String time)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventname", eventname);
        contentValues.put("description", description);
        contentValues.put("date", date);
        contentValues.put("time", time);
        long result = DB.insert("Eventdetails", null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Eventdetails where eventname = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Eventdetails", "eventname=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

}

