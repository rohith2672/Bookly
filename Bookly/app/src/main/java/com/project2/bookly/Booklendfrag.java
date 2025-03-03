package com.project2.bookly;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Booklendfrag extends Fragment {
    FloatingActionButton fab;
    ListView booklist;
    EditText studentrollno;
    Spinner totalbooklist;
    Button save;
    Bookdatabase db;
    Transactiondatabase db2;
    studentdatabase db3;
    String date;
    ArrayList<String> spinlist, listslist;
    ArrayAdapter<String> spinadapter, listadapter;
    int recordno=0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.booklend, container, false);
        fab = v.findViewById(R.id.addbook);
        booklist = v.findViewById(R.id.booklist);
        studentrollno = v.findViewById(R.id.studentroll);
        totalbooklist = v.findViewById(R.id.Booklist);
        save = v.findViewById(R.id.savelendbutton);
        TextView datetext = v.findViewById(R.id.textView2);

        db = new Bookdatabase(getContext());
        db2=new Transactiondatabase(getContext());
        db3=new studentdatabase(getContext());

        spinlist = new ArrayList<String>();

        Cursor booklistdata = db.getdata();
        while (booklistdata.moveToNext()) {
            spinlist.add(booklistdata.getString(1));
        }

        spinadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinlist);
        totalbooklist.setAdapter(spinadapter);
        spinadapter.notifyDataSetChanged();

        listadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, listslist);

        listslist = new ArrayList<String>();

        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        datetext.setText("Date : " + date);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = studentrollno.getText().toString();
                String status="OPEN";
                Cursor getstudentdetail = db3.getdata(sname);
                if (sname.equals("")) {
                    Toast.makeText(getContext(), "Enter student id and add books", Toast.LENGTH_SHORT).show();
                } else if (getstudentdetail.getCount() <= 0) {
                    Toast.makeText(getContext(), "Enter valid student id", Toast.LENGTH_SHORT).show();
                } else {
                    recordno = recordno + 1;
                    String record = String.valueOf(recordno);
                    //adding 30 days to the present date
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.DAY_OF_MONTH, 30);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                    Date resultdate = new Date(calendar.getTimeInMillis());
                    String returndate = sdf.format(resultdate);

                    int bookcount = booklist.getAdapter().getCount();
                    for (int i = 0; i < bookcount; i++) {
                        String item = booklist.getItemAtPosition(i).toString();
                        bookdetails(item);

                        Boolean checkinsertion = db2.insertdata(record, sname, item, date, returndate,status);
                        if (checkinsertion == true) {
                            Toast.makeText(getContext(), "Transaction updated", Toast.LENGTH_SHORT).show();
                            studentrollno.setText("");
                        } else {
                            Toast.makeText(getContext(), "not updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                    listslist.clear();
                    listadapter.notifyDataSetChanged();
                }
            }

        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = studentrollno.getText().toString();
                String status="OPEN";
                if (sname.equals("")) {
                    Toast.makeText(getContext(), "Invalid Roll no", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor stu = db3.getdata(sname);
                    if (stu.getCount() > 0) {
                        String currentbook=totalbooklist.getSelectedItem().toString();
                        Boolean check=db2.bookchecking(sname,currentbook,status);
                        if(check==true){
                                if (!listslist.contains(currentbook))
                                    listslist.add(currentbook);
                        } else {
                            Toast.makeText(getContext(), "Cannot add the same subject", Toast.LENGTH_SHORT).show();
                        }
                        listadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, listslist);
                        booklist.setAdapter(listadapter);
                    }else{
                        Toast.makeText(getContext(), "invalid Roll no", Toast.LENGTH_SHORT).show();
                        listslist.clear();
                        listadapter.notifyDataSetChanged();
                    }
                }
            }
        });

        return v;
    }

    public void bookdetails(String name){
        Cursor getdata=db.bookname(name);
            while (getdata.moveToNext()){
                String bname=getdata.getString(1);
                String bcount=getdata.getString(4);
                bookcalculation(bname,bcount);
            }
    }

    public void bookcalculation(String name,String count){
        int acount=Integer.parseInt(count);
        acount=acount-1;
       String finalcount=String.valueOf(acount);
        Boolean checkupdated=db.updatebook(name,finalcount);
    }

}
