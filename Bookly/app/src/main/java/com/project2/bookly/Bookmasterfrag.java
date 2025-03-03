package com.project2.bookly;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

public class Bookmasterfrag extends Fragment {
    Spinner year, dept;
    ArrayAdapter<String> yearadapter, deptadapter;
    List<String> yearlist, deptlist;
    Bookdatabase db;
    EditText id, name, openingcount,idfinder;
    Button create, modify, delete, get,get2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bookmaster, container, false);
        year = v.findViewById(R.id.spinyear);
        dept = v.findViewById(R.id.spindept);
        id = v.findViewById(R.id.bookid);
        name = v.findViewById(R.id.bookname);
        openingcount = v.findViewById(R.id.bookopeningcount);
        create = v.findViewById(R.id.Bookcreate);
        delete = v.findViewById(R.id.Bookdelete);
        modify = v.findViewById(R.id.Bookmodify);
        get = v.findViewById(R.id.get);
        get2 = v.findViewById(R.id.get2);
        idfinder = v.findViewById(R.id.idfinder);


        deptlist = Arrays.asList(getResources().getStringArray(R.array.deptlist));
        deptadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, deptlist);
        dept.setAdapter(deptadapter);

        yearlist = Arrays.asList(getResources().getStringArray(R.array.yearlist));
        yearadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, yearlist);
        year.setAdapter(yearadapter);

        db = new Bookdatabase(getContext());

       get2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String bid = idfinder.getText().toString();
               Cursor bookid = db.bookname(bid);
               if (bookid.getCount()<= 0) {
                   Toast.makeText(getContext(), "invalid bookname", Toast.LENGTH_SHORT).show();
                   idfinder.setText("");
               } else {

                   while (bookid.moveToNext()) {
                       String biid=bookid.getString(0);
                        id.setText(biid);
                   }
               }
           }
       });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bid = id.getText().toString();
                if (bid.equals("")) {
                    Toast.makeText(getContext(), "book id shouldn't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor bookid = db.getdata(bid);
                    if (bookid.getCount() <= 0) {
                        Toast.makeText(getContext(), "Invalid BookID", Toast.LENGTH_SHORT).show();
                    } else {
                        while (bookid.moveToNext()) {
                            String bname = bookid.getString(1);
                            String byear = bookid.getString(2);
                            String bdept = bookid.getString(3);
                            String bcount = bookid.getString(4);

                            name.setText(bname);
                            int spinyear = yearadapter.getPosition(byear);
                            year.setSelection(spinyear);
                            int spindept = deptadapter.getPosition(bdept);
                            openingcount.setText(bcount);
                        }
                    }
                }
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bid = id.getText().toString();
                String bname = name.getText().toString();
                String byear = year.getSelectedItem().toString();
                String bdept = dept.getSelectedItem().toString();
                String bcount = openingcount.getText().toString();

                if (bname.equals("") || byear.equals("") || bdept.equals("")) {
                    Toast.makeText(getContext(), "the above fields shouldn't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkmodify = db.updatedata(bid, bname, byear, bdept, bcount);
                    if (checkmodify == true) {
                        Toast.makeText(getContext(), "book updated", Toast.LENGTH_SHORT).show();
                        id.setText("");
                        name.setText("");
                        year.setSelection(0);
                        dept.setSelection(0);
                        openingcount.setText("");
                    } else {
                        Toast.makeText(getContext(), "not updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bid = id.getText().toString();

                Boolean deletebook = db.deletedata(bid);
                if (deletebook == true) {
                    Toast.makeText(getContext(), "Book deleted", Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    year.setSelection(0);
                    dept.setSelection(0);
                    openingcount.setText("");
                } else {
                    Toast.makeText(getContext(), "not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bid = id.getText().toString();
                String bname = name.getText().toString();
                String byear = year.getSelectedItem().toString();
                String bdept = dept.getSelectedItem().toString();
                String bocount = openingcount.getText().toString();


                if (bocount.equals("")||bid.equals("")||bname.equals("")) {
                    Toast.makeText(getContext(), "Invalid details", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insertioncheck = db.insertdata(bid, bname, byear, bdept, bocount);
                    if (insertioncheck == true) {
                        Toast.makeText(getContext(), "Book created", Toast.LENGTH_SHORT).show();
                        id.setText("");
                        name.setText("");
                        year.setSelection(0);
                        dept.setSelection(0);
                        openingcount.setText("");
                    } else {
                        Toast.makeText(getContext(), "not created", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return v;
    }
}
