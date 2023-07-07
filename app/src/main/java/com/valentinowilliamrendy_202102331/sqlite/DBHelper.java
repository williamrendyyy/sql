package com.valentinowilliamrendy_202102331.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="project.db";

    public DBHelper(Context context) {super(context, "project.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create table bukus(kode TEXT primary key, judul TEXT, pengarang TEXT, penerbit TEXT, isbn TEXT)");
        sqLiteDatabase.execSQL("create table biodatas(nim TEXT primary key, nama TEXT, jeniskelamin TEXT, alamat TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists bukus");
        sqLiteDatabase.execSQL("drop table if exists biodatas");
    }

    public Boolean inserData(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result ==1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public Boolean insertDataMHS (String nim, String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nim",nim);
        values.put("nama",nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);
        long result = db.insert("biodatas", null,values);
        if (result ==1) return false;
        else
            return true;
    }
    public Cursor tampilDataMHS(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from biodatas", null);
        return cursor;

    }

    public boolean hapusDataMHS(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from biodatas where nim=?", new String[]{nim});
        if (cursor.getCount()>0) {
            long result = db.delete("biodatas", "nim=?", new String[]{nim});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean editDataMHS(String nim, String nama, String jeniskelamin, String alamat, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);

        Cursor cursor = db.rawQuery("Select * from biodatas where nim=?", new String[]{nim});
        if (cursor.getCount()>0) {
            long result = db.update("biodatas", values, "nim=?", new String[]{nim});
            if (result == 1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Boolean ceknim(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from biodatas where nim=?", new String[] {nim});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean insertDataBOOK (String kode, String judul, String pengarang, String penerbit, String isbn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kode",kode);
        values.put("judul",judul);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("isbn", isbn);
        long result = db.insert("bukus", null,values);
        if (result == 0) return false;
        else
            return true;
    }
    public Cursor tampilDataBOOK(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from bukus", null);
        return cursor;

    }

    public boolean hapusDataBOOK(String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from bukus where kode=?", new String[]{kode});
        if (cursor.getCount()>0) {
            long result = db.delete("bukus", "kode=?", new String[]{kode});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean editDataBOOK (String kode, String judul, String pengarang, String penerbit, String isbn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("judul",judul);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("isbn", isbn);
        Cursor cursor = db.rawQuery("Select * from bukus where kode=?", new String[]{kode});
        if (cursor.getCount()>0) {
            long result = db.update("bukus", values, "kode=?", new String[]{kode});
            if (result == 1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Boolean cekDataBOOK(String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from bukus where kode=?", new String[] {kode});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
