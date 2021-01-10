package com.aaronevan.binusezyfoody.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aaronevan.binusezyfoody.binusclass.HistoryDetail;
import com.aaronevan.binusezyfoody.binusclass.HistoryHeader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailDatabaseHandler extends SQLiteOpenHelper {

    public DetailDatabaseHandler(@Nullable Context context) {
        super(context, Database.Database, null, Database.databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addDetailTransaction(Integer TransaksiId, Integer ProductId, Integer Qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.IdTransaction, TransaksiId);
        cv.put(Database.IdProduct, ProductId);
        cv.put(Database.Quantity, Qty);
        db.insert(Database.tableDetail, null, cv);
        db.close();
    }

    public ArrayList<HistoryDetail> getAllData(Integer idTrans) {
        ArrayList<HistoryDetail> hh = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableDetail + " WHERE " + Database.IdTransaction + "=" + idTrans.toString();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HistoryDetail obj = new HistoryDetail(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
            hh.add(obj);
        }
        db.close();
        return hh;
    }
}
