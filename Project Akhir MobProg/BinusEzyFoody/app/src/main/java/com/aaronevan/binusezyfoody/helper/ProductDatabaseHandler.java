package com.aaronevan.binusezyfoody.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.binusclass.Resto;

import java.util.ArrayList;

public class ProductDatabaseHandler extends SQLiteOpenHelper {

    public ProductDatabaseHandler(@Nullable Context context) {
        super(context, Database.Database, null, Database.databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void initProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.IdResto, 1);
        cv.put(Database.NamaProduct, "Mineral Water");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.water);
        cv.put(Database.HargaProduct, 4000);
        cv.put(Database.StockProduct, 50);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 1);
        cv.put(Database.NamaProduct, "Jus Apel");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.apel);
        cv.put(Database.HargaProduct, 12000);
        cv.put(Database.StockProduct, 20);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 1);
        cv.put(Database.NamaProduct, "Jus Mangga");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.mangga);
        cv.put(Database.HargaProduct, 14000);
        cv.put(Database.StockProduct, 4);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 1);
        cv.put(Database.NamaProduct, "Jus Alpukat");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.alpukat);
        cv.put(Database.HargaProduct, 15000);
        cv.put(Database.StockProduct, 5);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 2);
        cv.put(Database.NamaProduct, "Mineral Water");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.water);
        cv.put(Database.HargaProduct, 4000);
        cv.put(Database.StockProduct, 60);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 2);
        cv.put(Database.NamaProduct, "Jus Apel");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.apel);
        cv.put(Database.HargaProduct, 12000);
        cv.put(Database.StockProduct, 35);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 2);
        cv.put(Database.NamaProduct, "Jus Mangga");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.mangga);
        cv.put(Database.HargaProduct, 14000);
        cv.put(Database.StockProduct, 90);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 2);
        cv.put(Database.NamaProduct, "Jus Alpukat");
        cv.put(Database.JenisProduct, "Drink");
        cv.put(Database.ImageProduct, R.drawable.alpukat);
        cv.put(Database.HargaProduct, 15000);
        cv.put(Database.StockProduct, 10);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 1);
        cv.put(Database.NamaProduct, "Soup");
        cv.put(Database.JenisProduct, "Food");
        cv.put(Database.ImageProduct, R.drawable.soup);
        cv.put(Database.HargaProduct, 23000);
        cv.put(Database.StockProduct, 40);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 2);
        cv.put(Database.NamaProduct, "Soup");
        cv.put(Database.JenisProduct, "Food");
        cv.put(Database.ImageProduct, R.drawable.soup);
        cv.put(Database.HargaProduct, 23000);
        cv.put(Database.StockProduct, 50);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 1);
        cv.put(Database.NamaProduct, "Nugget");
        cv.put(Database.JenisProduct, "Snack");
        cv.put(Database.ImageProduct, R.drawable.nugget);
        cv.put(Database.HargaProduct, 3000);
        cv.put(Database.StockProduct, 20);
        db.insert(Database.tableProduct, null, cv);

        cv.put(Database.IdResto, 2);
        cv.put(Database.NamaProduct, "Nugget");
        cv.put(Database.JenisProduct, "Snack");
        cv.put(Database.ImageProduct, R.drawable.nugget);
        cv.put(Database.HargaProduct, 3000);
        cv.put(Database.StockProduct, 30);
        db.insert(Database.tableProduct, null, cv);
    }

    public ArrayList<Product> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableProduct;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Product> arr = new ArrayList<>();
        while(cursor.moveToNext()){
            Product obj = new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
            arr.add(obj);
        }
        db.close();
        return arr;
    }

    public ArrayList<Product> getData(Integer Store, String Jenis){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableProduct +" WHERE " + Database.IdResto + "=" + Store.toString() + " AND " + Database.JenisProduct + "=\"" + Jenis + "\"";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Product> arr = new ArrayList<>();
        while(cursor.moveToNext()){
            Product obj = new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
            arr.add(obj);
        }
        db.close();
        return arr;
    }

    public void reduceStock(Integer ProductId, Integer Stock){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.StockProduct, Stock);
        db.update(Database.tableProduct, cv, Database.IdProduct + " =?", new String[]{ProductId.toString()});
        db.close();
    }

    public Product getOneData(Integer ProductID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Database.tableProduct + " WHERE " + Database.IdProduct + "=" + ProductID.toString();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            db.close();
//            Integer idProduct, Integer idResto, String namaProduct, String jenisProduct, Integer imageProduct, Integer hargaProduct, Integer stockProduct
            Product temp = new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4), cursor.getInt(5),cursor.getInt(6));
            return temp;
        } else {
            db.close();
            return null;
        }
    }

}
