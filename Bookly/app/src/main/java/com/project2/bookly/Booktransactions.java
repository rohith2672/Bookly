package com.project2.bookly;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Booktransactions extends Fragment {
    Spinner year, dept;
    ArrayAdapter<String> yearadapter, deptadapter, listadapter;
    ArrayList<String> alist;
    ArrayList<String> list1, list2, list3, list4, list5,list6;
    List<String> yearlist, deptlist;
    ListView tranlist;
    Button gettrans;
    studentdatabase db;
    Transactiondatabase db2;
    Customlistview adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transactions, container, false);
        year = v.findViewById(R.id.yearspin);
        dept = v.findViewById(R.id.deptspin);
        tranlist = v.findViewById(R.id.transactionlist);
        gettrans = v.findViewById(R.id.gettransactions);

        db = new studentdatabase(getContext());
        db2 = new Transactiondatabase(getContext());

        listadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, alist);


        deptlist = Arrays.asList(getResources().getStringArray(R.array.deptlist));
        deptadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, deptlist);
        dept.setAdapter(deptadapter);

        yearlist = Arrays.asList(getResources().getStringArray(R.array.yearlist));
        yearadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, yearlist);
        year.setAdapter(yearadapter);

        alist = new ArrayList<String>();
        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list3 = new ArrayList<String>();
        list4 = new ArrayList<String>();
        list5 = new ArrayList<String>();
        list6 = new ArrayList<String>();

        gettrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alist.clear();
                listadapter.notifyDataSetChanged();
                String deptdata = dept.getSelectedItem().toString();
                String yeardata = year.getSelectedItem().toString();

                Cursor getstulist = db.getdata(deptdata, yeardata);
                if (getstulist.getCount() <= 0) {
                    Toast.makeText(getContext(), "database is empty:(", Toast.LENGTH_SHORT).show();
                } else {
                    alist.clear();
                    while (getstulist.moveToNext()) {
                        alist.add(getstulist.getString(2));
                    }
                    listadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, alist);
                    tranlist.setAdapter(listadapter);
                    listadapter.notifyDataSetChanged();
                }
            }
        });

        tranlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String bookname = "";
                AlertDialog.Builder listdialog = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);

                final View view = inflater.inflate(R.layout.displaytransactiondialog, null);
                listdialog.setView(view);
                final AlertDialog dialog = listdialog.create();
                dialog.show();
                ListView mylist = dialog.findViewById(R.id.detailedtransaction);

                String itemselected = tranlist.getItemAtPosition(position).toString();
                Cursor checkborrowhistory = db2.getdata(itemselected);
                if (checkborrowhistory.getCount() <= 0) {
                    Toast.makeText(getContext(), "No Borrow History", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    list4.clear();
                    list5.clear();
                    list6.clear();
                    while (checkborrowhistory.moveToNext()) {
                        list1.add(checkborrowhistory.getString(2));
                        list2.add(checkborrowhistory.getString(3));
                        list3.add(checkborrowhistory.getString(4));
                        list4.add(checkborrowhistory.getString(5));
                        list5.add(checkborrowhistory.getString(6));
                        list6.add(checkborrowhistory.getString(7));
                    }adapter=new Customlistview(getContext(),list1,list2,list3,list4,list5,list6);
                    mylist.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                    }
        });


        return v;
    }
}
