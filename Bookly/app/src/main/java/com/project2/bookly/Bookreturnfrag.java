package com.project2.bookly;

import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Bookreturnfrag extends Fragment {
    Button get,save;
    EditText sid;
    ListView booklist;
    ArrayList<String> alist;
    ArrayAdapter<String> listadapter;
    Transactiondatabase db;
    studentdatabase db2;
    Bookdatabase db3;
    String date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bookreturn,container,false);
        get=v.findViewById(R.id.getdetail);
        save=v.findViewById(R.id.savereturn);
        sid=v.findViewById(R.id.studentroll2);
        booklist=v.findViewById(R.id.list_items);

        db=new Transactiondatabase(getContext());
        db2=new studentdatabase(getContext());
        db3=new Bookdatabase(getContext());
        alist=new ArrayList<String>();

        listadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, alist);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = sid.getText().toString();
                Cursor getstudentdetail = db2.getdata(name);
                if (name.equals("")) {
                    Toast.makeText(getContext(), "Enter student id and add books", Toast.LENGTH_SHORT).show();
                } else if (getstudentdetail.getCount() <= 0) {
                    Toast.makeText(getContext(), "Enter valid student id", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        SparseBooleanArray checkeditem = booklist.getCheckedItemPositions();
                        int totalselected = booklist.getAdapter().getCount();
                        for (int i = 0; i < totalselected; i++) {
                            if (checkeditem.get(i) == true) {
                                String checkedbook = booklist.getItemAtPosition(i).toString();
                                date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                bookdetails(checkedbook, name, date);
                            } else {
                                Toast.makeText(getContext(), "some books are not returned", Toast.LENGTH_SHORT).show();
                            }
                        }
                        alist.clear();
                        listadapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Book(s) returned", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=sid.getText().toString();
                alist.clear();
                listadapter.notifyDataSetChanged();
                Cursor getstudentdetail = db2.getdata(name);
                if (name.equals("")) {
                    Toast.makeText(getContext(), "Enter student id", Toast.LENGTH_SHORT).show();
                } else if (getstudentdetail.getCount() <= 0) {
                    Toast.makeText(getContext(), "Enter valid student id", Toast.LENGTH_SHORT).show();
                } else {
                    String status="OPEN";
                    Cursor getdata = db.getdata(name,status);
                    if (getdata.getCount() <= 0) {
                        Toast.makeText(getContext(), "database is empty :(", Toast.LENGTH_SHORT).show();
                    } else {
                        while (getdata.moveToNext()) {
                            alist.add(getdata.getString(2));
                        }
                        listadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, alist);
                        booklist.setAdapter(listadapter);
                        listadapter.notifyDataSetChanged();
                    }
                }

            }
        });


        return v;
    }
    public void bookdetails(String name,String sname,String date){
        Cursor getdata=db3.bookname(name);
        while (getdata.moveToNext()){
            String bname=getdata.getString(1);
            String bcount=getdata.getString(4);
            bookcalculation(bname,bcount);
            bookstatus(bname,sname,date);
            comparedate(bname,sname,date);
        }
    }

    public void comparedate(String bname,String sname,String date){
        String estretdate=null;
        String fine="";
        Cursor bookdata=db.getreturndata(sname,bname);
        while(bookdata.moveToNext()) {
            estretdate = bookdata.getString(6);
        } if (date.compareTo(estretdate)<0){
                fine="15";
            }else{
                fine="0";
                Toast.makeText(getContext(), fine, Toast.LENGTH_SHORT).show();
        } Boolean updatefine=db.updatefine(bname,sname,fine);


    }

    public void bookstatus(String bname,String sroll,String date){
          String status="CLOSED";
        Boolean updatestatus=db.updatebookstatus(bname,sroll,status,date);
        if(updatestatus==true) {
            Toast.makeText(getContext(), "Book returned", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getContext(), "Book not updated", Toast.LENGTH_SHORT).show();
        }

    }

    public void bookcalculation(String name,String count){
        int acount=Integer.parseInt(count);
        acount=acount+1;
        String finalcount=String.valueOf(acount);
        Boolean checkupdated=db3.updatebook(name,finalcount);



        }

}
