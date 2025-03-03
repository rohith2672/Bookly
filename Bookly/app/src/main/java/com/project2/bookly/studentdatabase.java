package com.project2.bookly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class studentdatabase extends SQLiteOpenHelper {
    public studentdatabase(@Nullable Context context) {
        super(context, "studentdetail", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table studentdetail(sname TEXT,sfname TEXT,sroll TEXT,spassword TEXT,sphone TEXT,sdept TEXT,year TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists studentdetail");

    }

    public Boolean insertdata(String sname,String sfname,String sroll,String spassword,String sphone,String sdept,String year)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("sname", sname);
        values.put("sfname", sfname);
        values.put("sroll", sroll);
        values.put("spassword", spassword);
        values.put("sphone", sphone);
        values.put("sdept", sdept);
        values.put("year", year);
        long result=db.insert("studentdetail",null,values);
        if(result==-1)
        {
            return false;
        }else{
            return true;
        }

    }

    public Boolean userddetail(String user,String pass)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from studentdetail where sroll = ? and spassword = ?",new String[] {user,pass});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from studentdetail",null);
        return cursor;
    }
    public Cursor getdata(String sroll){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from studentdetail where sroll=?",new String[] {sroll});
        return cursor;
    }
    public Cursor getdata(String sdept,String year){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from studentdetail where sdept=? and year=?",new String[] {sdept,year});
        return cursor;
    }




    public boolean deletedata(String roll){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from studentdetail where sroll=?",new String[] {roll});
        if(cursor.getCount()>0)
        {
            long result = db.delete("studentdetail","sroll=?",new String[] {roll});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }


    public boolean updatedata(String sname,String sfname,String sroll,String spassword,String sphone,String sdept,String year){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("sname",sname);
        values.put("sfname",sfname);
        values.put("sroll",sroll);
        values.put("spassword", spassword);
        values.put("sphone",sphone);
        values.put("sdept",sdept);
        values.put("year", year);
        Cursor cursor = db.rawQuery("Select * from studentdetail where sroll=?",new String[] {sroll});

        if(cursor.getCount()>0)
        {
            long result = db.update("studentdetail", values, "sroll=?",new String[] {sroll});
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
