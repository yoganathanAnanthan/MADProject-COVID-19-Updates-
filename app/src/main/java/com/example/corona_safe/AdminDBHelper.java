package com.example.corona_safe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Nullable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDBHelper  extends SQLiteOpenHelper {
    public static final String DBNAME = "Admin.db";
    public AdminDBHelper(Context context) {
        super(context, "Admin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table admin(adminName TEXT primary key, adminPassword TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists admin");
    }

    public Boolean insertData(String adminName,String adminPassword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("adminName", adminName);
        contentValues.put("adminPassword", adminPassword);
        long result = MyDB.insert("admin", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkadminname(String adminName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where adminName = ?", new String[]{adminName});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkadminnamepassword(String adminName, String adminPassword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where adminName = ? and adminPassword = ?", new String[] {adminName,adminPassword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}