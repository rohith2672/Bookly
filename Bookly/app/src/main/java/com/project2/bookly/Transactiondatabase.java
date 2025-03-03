package com.project2.bookly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Transactiondatabase extends SQLiteOpenHelper {
    public Transactiondatabase(@Nullable Context context) {
        super(context,"transactions",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table transactions(recordno TEXT,sroll TEXT,bookname TEXT,borrowdate TEXT,returndate TEXT,status TEXT,realrdate TEXT,fine TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists transactions");
    }

    public Boolean insertdata(String recordno,String sroll,String bookname, String borrowdate, String returndate,String status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recordno", recordno);
        values.put("sroll", sroll);
        values.put("bookname", bookname);
        values.put("borrowdate", borrowdate);
        values.put("returndate", returndate);
        values.put("status", status);
        long result = db.insert("transactions", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from transactions", null);
        return cursor;
    }

    public Cursor getdata(String sroll){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from transactions where sroll=?",new String[] {sroll});
        return cursor;
    }

    public Cursor getdata(String sroll,String status){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from transactions where sroll=? and status=?",new String[] {sroll,status});
        return cursor;
    }

    public Cursor getreturndata(String sroll,String bookname){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from transactions where sroll=? and bookname=?",new String[] {sroll,bookname});
        return cursor;
    }

    public Boolean bookchecking(String sroll,String bookname,String status){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from transactions where sroll=? and bookname=? and status=?",new String[] {sroll,bookname,status});
        if(cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }




    public boolean updatebookstatus(String bookname,String sroll,String status,String realrdate){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("status",status);
        values.put("realrdate",realrdate);
        Cursor cursor=db.rawQuery("Select * from transactions where bookname=? and sroll=?",new String[]{bookname,sroll});
        if(cursor.getCount()>0){
            long result=db.update("transactions",values,"bookname=? and sroll=?",new String[]{bookname,sroll});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean updatefine(String bookname,String sroll,String fine){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("fine",fine);
        Cursor cursor=db.rawQuery("Select * from transactions where bookname=? and sroll=?",new String[]{bookname,sroll});
        if(cursor.getCount()>0){
            long result=db.update("transactions",values,"bookname=? and sroll=?",new String[]{bookname,sroll});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }


}

