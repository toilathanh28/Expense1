package com.example.coursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class  DBHelpers extends SQLiteOpenHelper {
    public DBHelpers(Context context) {
        super(context, "TripsData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Tripdetails(trip_name1 TEXT primary key, place_name1 TEXT, price1 TEXT, risk_assessment1 TEXT,DoT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Tripdetails");
    }

    public Boolean insertTripData(String trip_name1, String place_name1,String price1,String risk_assessment1,String Dot){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trip_name1", trip_name1);
        contentValues.put("place_name1", place_name1);
        contentValues.put("price1", price1);
        contentValues.put("risk_assessment1", risk_assessment1);
        contentValues.put("Dot", Dot);
        long result = DB.insert("Tripdetails", null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean deleteTripData(String trip_name1){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tripdetails where trip_name1 = ?", new String []{trip_name1});
        if (cursor.getCount() >0){
            long result=DB.delete("Tripdetails","trip_name1=?", new String [] {trip_name1});
            if(result == -1) {
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tripdetails", null);
        return cursor;
    }



    public Boolean addFeeForTrip(String trip_name1, String price1) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("price1", price1);
        Cursor cursor = DB.rawQuery("Select * from Tripdetails where trip_name1 = ?", new String[]{trip_name1});
        if (cursor.getCount() >0){
            long result = DB.update("Tripdetails", contentValues, "trip_name1=?", new String[]{trip_name1});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }
    }

    public Integer resetData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.delete("Tripdetails", null, null);
    }

}
