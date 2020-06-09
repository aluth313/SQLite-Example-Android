package com.example.sqliteexample.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteexample.model.Mahasiswa;

import java.util.ArrayList;

public class CRUD extends SQLiteOpenHelper {
    Context context;

    public CRUD(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + Constant.DATABASE_TABLE
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constant.KEY_NRP + " TEXT (50) NOT NULL,"
                + Constant.KEY_NAMA_LENGKAP + " TEXT (50))";

        db.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDrop = "DROP TABLE IF EXISTS " + Constant.DATABASE_TABLE;
        String sqlCreateTable = "CREATE TABLE " + Constant.DATABASE_TABLE
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constant.KEY_NRP + " TEXT (50) NOT NULL,"
                + Constant.KEY_NAMA_LENGKAP + " TEXT (50))";

        db.execSQL(sqlDrop);
        db.execSQL(sqlCreateTable);
        onCreate(db);
    }

    public void create(Mahasiswa mahasiswa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.KEY_NRP, mahasiswa.getNrp());
        values.put(Constant.KEY_NAMA_LENGKAP, mahasiswa.getNama());
        db.insert(Constant.DATABASE_TABLE,null,values);
        db.close();
    }

    public void update(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.KEY_NRP, mahasiswa.getNrp());
        values.put(Constant.KEY_NAMA_LENGKAP, mahasiswa.getNama());
        db.update(Constant.DATABASE_TABLE,values,Constant.KEY_NRP + "=?", new String[]{mahasiswa.getNrp()});
        db.close();
    }

    public ArrayList<Mahasiswa> selectAll(){
        ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM " + Constant.DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String nrp = cursor.getString(1);
                String nama = cursor.getString(2);
                Mahasiswa mahasiswa = new Mahasiswa(nrp,nama);
                mahasiswas.add(mahasiswa);
                cursor.moveToNext();
            }
        }
        return mahasiswas;
    }
}
