package com.project2.bookly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Bookdatabase extends SQLiteOpenHelper {
    public Bookdatabase(@Nullable Context context) {
        super(context, "book.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table book(bookid TEXT PRIMARY KEY,bookname TEXT,year TEXT,dept TEXT,opening TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book");

    }

    public Boolean insertdata(String bookid, String bookname, String year, String dept, String opening) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bookid", bookid);
        values.put("bookname", bookname);
        values.put("year", year);
        values.put("dept", dept);
        values.put("opening", opening);
        long result = db.insert("book", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from book", null);
        return cursor;
    }
    public Cursor getdata(String bookid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from book where bookid=?",new String[] {bookid});
        return cursor;
    }

    public Cursor bookname(String bookname){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from book where bookname=?",new String[] {bookname});
        return cursor;
    }
    public Cursor bookcount(String bookname){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from book where bookname=?",new String[] {bookname});
        return cursor;
    }

    public Cursor bookdata(String dept){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from book where dept=?",new String[] {dept});
        return cursor;
    }


    public boolean deletedata(String bookid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from book where bookid=?", new String[]{bookid});
        if (cursor.getCount() > 0) {
            long result = db.delete("book", "bookid=?", new String[]{bookid});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean updatedata(String bookid, String bookname, String year, String dept, String opening){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("bookname",bookname);
        values.put("year",year);
        values.put("dept",dept);
        values.put("opening", opening);
        Cursor cursor = db.rawQuery("Select * from book where bookid=?",new String[] {bookid});

        if(cursor.getCount()>0)
        {
            long result = db.update("book", values, "bookid=?",new String[] {bookid});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean updatebook(String bookname,String opening){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("opening",opening);
        Cursor cursor=db.rawQuery("Select * from book where bookname=?",new String[]{bookname});
        if(cursor.getCount()>0){
            long result=db.update("book",values,"bookname=?",new String[]{bookname});
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
