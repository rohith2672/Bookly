package com.project2.bookly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class admindatabase extends SQLiteOpenHelper {
    public admindatabase(@Nullable Context context) {
        super(context, "admin", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table admin(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists admin");
    }
    public Boolean insertdata(String username,String password)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result=db.insert("admin",null,values);
        if(result==-1)
        {
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from admin",null);
        return cursor;
    }

    public Boolean userddetail(String user,String pass)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from admin where username = ? and password = ?",new String[] {user,pass});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
