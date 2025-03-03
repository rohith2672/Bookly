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

public class studentongoingfrag extends Fragment {
    String tname;
    ListView ongoinglist;
    ArrayList<String> list1, list2, list3, list4, list5,list6;
    Customlistview adapter;
    Transactiondatabase db;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.studentongoinglayout, container, false);
        ongoinglist=v.findViewById(R.id.ongoinglist);

        String status = "OPEN";
        tname = getActivity().getIntent().getExtras().getString("name");
        db = new Transactiondatabase(getContext());

        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list3 = new ArrayList<String>();
        list4 = new ArrayList<String>();
        list5 = new ArrayList<String>();
        list6 = new ArrayList<String>();

        Cursor getongoingdata = db.getdata(tname, status);
        if (getongoingdata.getCount() <= 0) {
            Toast.makeText(getContext(), "No Ongoing Transactions Available", Toast.LENGTH_SHORT).show();
        } else {
            list1.clear();
            list2.clear();
            list3.clear();
            list4.clear();
            list5.clear();
            list6.clear();
            while (getongoingdata.moveToNext()) {
                list1.add(getongoingdata.getString(2));
                list2.add(getongoingdata.getString(3));
                list3.add(getongoingdata.getString(4));
                list4.add(getongoingdata.getString(5));
                list5.add(getongoingdata.getString(6));
                list6.add(getongoingdata.getString(7));
            }adapter=new Customlistview(getContext(),list1,list2,list3,list4,list5,list6);
            ongoinglist.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        return v;
    }
}
