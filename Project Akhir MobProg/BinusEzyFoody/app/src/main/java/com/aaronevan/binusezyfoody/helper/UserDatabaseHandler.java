package com.aaronevan.binusezyfoody.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDatabaseHandler extends SQLiteOpenHelper {

    public UserDatabaseHandler(@Nullable Context context) {
        super(context, Database.Database, null, Database.databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database.Make_Table_User());
        db.execSQL(Database.Make_Table_Resto());
        db.execSQL(Database.Make_Table_Product());
        db.execSQL(Database.Make_Table_Transaksi());
        db.execSQL(Database.Make_Table_Detail());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void makeTable(){

    }

    public void initMoney() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.Saldo, 100000);
        db.insert(Database.tableUser, null, cv);
        db.close();
    }

    public void updateMoney(Integer Money) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.Saldo, Money);
        Integer id = 1;
        db.update(Database.tableUser, cv, Database.IdUser + " =?", new String[]{id.toString()});
        db.close();
    }

    public Integer getMoney() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableUser;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            db.close();
            return cursor.getInt(1);
        } else {
            db.close();
            return -1;
        }
    }
}
