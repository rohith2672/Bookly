package com.project2.bookly;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class studentcompletedfrag extends Fragment {
    String tname;
    ListView completedlist;
    ArrayList<String> list1, list2, list3, list4, list5,list6;
    Customlistview adapter;
    Transactiondatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.studentcompletedlayout,container,false);
       completedlist=v.findViewById(R.id.completedlist);

        String status="CLOSED";
        tname=getActivity().getIntent().getExtras().getString("name");

        db = new Transactiondatabase(getContext());

        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list3 = new ArrayList<String>();
        list4 = new ArrayList<String>();
        list5 = new ArrayList<String>();
        list6 = new ArrayList<String>();

        Cursor getcompleteddata = db.getdata(tname, status);
        if (getcompleteddata.getCount() <= 0) {
            Toast.makeText(getContext(), "No Borrow History", Toast.LENGTH_SHORT).show();
        } else {
            list1.clear();
            list2.clear();
            list3.clear();
            list4.clear();
            list5.clear();
            list6.clear();
            while (getcompleteddata.moveToNext()) {
                list1.add(getcompleteddata.getString(2));
                list2.add(getcompleteddata.getString(3));
                list3.add(getcompleteddata.getString(4));
                list4.add(getcompleteddata.getString(5));
                list5.add(getcompleteddata.getString(6));
                list6.add(getcompleteddata.getString(7));
            }adapter=new Customlistview(getContext(),list1,list2,list3,list4,list5,list6);
            completedlist.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }



        return v;
    }
}
