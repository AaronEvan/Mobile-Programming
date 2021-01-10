package com.aaronevan.binusezyfoody.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aaronevan.binusezyfoody.binusclass.HistoryHeader;
import com.aaronevan.binusezyfoody.binusclass.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransaksiDatabaseHandler extends SQLiteOpenHelper {

    public TransaksiDatabaseHandler(@Nullable Context context) {
        super(context, Database.Database, null, Database.databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Integer addTransaction(String Alamat, Integer Total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.Alamat, Alamat);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        cv.put(Database.Tanggal, formatter.format(date));
        cv.put(Database.Total, Total);
        cv.put(Database.IdUser, 1);
        db.insert(Database.tableTransaksi, null, cv);
        db.close();
        return getLastID();
    }

    public Integer getLastID(){
        String query = "SELECT MAX("+ Database.IdTransaction+") FROM " + Database.tableTransaksi;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur.moveToFirst()) {
            db.close();
            return cur.getInt(0);
        } else {
            db.close();
            return -1;
        }
    }

    public ArrayList<HistoryHeader> getAllData(){
        ArrayList<HistoryHeader> hh = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableTransaksi +" WHERE " + Database.IdUser + "=1";
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            HistoryHeader obj = new HistoryHeader(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            hh.add(obj);
        }
        db.close();
        return hh;
    }

}
