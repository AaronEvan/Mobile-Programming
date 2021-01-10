package com.aaronevan.binusezyfoody.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aaronevan.binusezyfoody.binusclass.Resto;

import java.util.ArrayList;

public class RestoDatabaseHandler extends SQLiteOpenHelper {

    public RestoDatabaseHandler(@Nullable Context context) {
        super(context, Database.Database, null, Database.databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void init(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.NamaResto, "Kemanggisan Store");
        cv.put(Database.LongResto, -6.200626786640842);
        cv.put(Database.LatResto, 106.78510782104438);
        db.insert(Database.tableResto, null, cv);
        cv.put(Database.NamaResto, "Alam Sutera Store");
        cv.put(Database.LongResto, -6.2232004023710585);
        cv.put(Database.LatResto, 106.6488380704966);
        db.insert(Database.tableResto, null, cv);
        db.close();
    }

    public ArrayList<Resto> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableResto;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Resto> arr = new ArrayList<>();
        while(cursor.moveToNext()){
            Resto obj = new Resto(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
            arr.add(obj);
        }
        db.close();
        return  arr;
    }

}
