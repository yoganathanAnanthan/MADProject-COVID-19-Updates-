package com.example.corona_safe.database;


import static com.example.corona_safe.database.UsersMaster.Users.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context){super(context,DATABASE_NAME,null,1);}
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE "+ TABLE_NAME + " ("+
                        UsersMaster.Users.COLUMN_NAME_PROVINCE+ " TEXT PRIMARY KEY," +
                        UsersMaster.Users.COLUMN_NAME_CONFIRMED+ " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_DEATHS+ " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_RECOVERED+ " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_DATE+ " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public Long addInfo(String province, String confirmed, String deaths, String recovered){

        SQLiteDatabase db = getWritableDatabase();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timeStamp = dateFormat.format(date);

        ContentValues values=new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_PROVINCE,province);
        values.put(UsersMaster.Users.COLUMN_NAME_CONFIRMED,confirmed);
        values.put(UsersMaster.Users.COLUMN_NAME_DEATHS,deaths);
        values.put(UsersMaster.Users.COLUMN_NAME_RECOVERED,recovered);
        values.put(UsersMaster.Users.COLUMN_NAME_DATE,timeStamp);

        return db.insert(TABLE_NAME,null,values);
        //long newRowId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);
    }

    public List readAll(){
        SQLiteDatabase db = getReadableDatabase();

        String [] projection = {
                UsersMaster.Users.COLUMN_NAME_PROVINCE,
                UsersMaster.Users.COLUMN_NAME_CONFIRMED,
                UsersMaster.Users.COLUMN_NAME_DEATHS,
                UsersMaster.Users.COLUMN_NAME_RECOVERED,
                UsersMaster.Users.COLUMN_NAME_DATE
        };
        String sortOrder = UsersMaster.Users.COLUMN_NAME_PROVINCE + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List info = new ArrayList<>();

        while (cursor.moveToNext()){
            String province = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PROVINCE));
            String confirmed = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_CONFIRMED));
            String deaths = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_DEATHS));
            String recovered = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_RECOVERED));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_DATE));

            info.add(province+":"+confirmed+":"+deaths+":"+recovered+":"+date);
        }
        cursor.close();
        return info;
    }

    public void deleteInfo(String province){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.COLUMN_NAME_PROVINCE + " LIKE ?";
        String[] stringArgs = {province};

        db.delete(TABLE_NAME,selection,stringArgs);
    }

    public void updateInfo(View view, String province,String confirmed,String deaths,String recovered){
        SQLiteDatabase db = getWritableDatabase();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timeStamp = dateFormat.format(date);

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_CONFIRMED,confirmed);
        values.put(UsersMaster.Users.COLUMN_NAME_DEATHS,deaths);
        values.put(UsersMaster.Users.COLUMN_NAME_RECOVERED,recovered);
        values.put(UsersMaster.Users.COLUMN_NAME_DATE,timeStamp);
        String selection = UsersMaster.Users.COLUMN_NAME_PROVINCE + " LIKE ?";
        String[] selectionArgs = {province};

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        Snackbar snackbar = Snackbar.make(view, count+"rows were affected!",Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();
    }

    public Cursor readProvince(String province1){
        SQLiteDatabase db = getReadableDatabase();

        //String sortOrder = UsersMaster.Users.COLUMN_NAME_PROVINCE + " DESC";

        Cursor cursor = db.rawQuery("SELECT * FROM users1 WHERE province = '"+province1+"'", null);

            return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
