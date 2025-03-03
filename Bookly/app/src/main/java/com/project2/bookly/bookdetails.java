package com.project2.bookly;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class bookdetails extends Fragment {
    ListView one,two,three,four,five;
    Bookdatabase db;
    ArrayList<String> cselist,eeelist,ecelist,mechlist,civillist;
    ArrayList<String> cselist2,eeelist2,ecelist2,mechlist2,civillist2;
    Custombookdetails adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bookdetails,container,false);
        one=v.findViewById(R.id.listview1);
        two=v.findViewById(R.id.listview2);
        three=v.findViewById(R.id.listview3);
        four=v.findViewById(R.id.listview4);
        five=v.findViewById(R.id.listview5);

        db=new Bookdatabase(getContext());

        cselist=new ArrayList<String>();
        cselist2=new ArrayList<String>();
        eeelist=new ArrayList<String>();
        eeelist2=new ArrayList<String>();
        ecelist=new ArrayList<String>();
        ecelist2=new ArrayList<String>();
        mechlist=new ArrayList<String>();
        mechlist2=new ArrayList<String>();
        civillist=new ArrayList<String>();
        civillist2=new ArrayList<String>();

        String cse="CSE";
        String eee="EEE";
        String ece="ECE";
        String civil="CIVIL";
        String mech="MECH";


        Cursor csedata=db.bookdata(cse);
        while(csedata.moveToNext()){
            cselist.add(csedata.getString(1));
            cselist2.add(csedata.getString(4));
        }adapter=new Custombookdetails(getContext(),cselist,cselist2);
        one.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Cursor ecedata=db.bookdata(ece);
        while(ecedata.moveToNext()){
            ecelist.add(ecedata.getString(1));
            ecelist2.add(ecedata.getString(4));
        }adapter=new Custombookdetails(getContext(),ecelist,ecelist2);
        two.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Cursor civildata=db.bookdata(civil);
        while(civildata.moveToNext()){
            civillist.add(civildata.getString(1));
            civillist2.add(civildata.getString(4));
        }adapter=new Custombookdetails(getContext(),civillist,civillist2);
        three.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Cursor eeedata=db.bookdata(eee);
        while(eeedata.moveToNext()){
            eeelist.add(eeedata.getString(1));
            eeelist2.add(eeedata.getString(4));
        }adapter=new Custombookdetails(getContext(),eeelist,eeelist2);
        four.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Cursor mechdata=db.bookdata(mech);
        while(mechdata.moveToNext()){
            mechlist.add(mechdata.getString(1));
            mechlist2.add(mechdata.getString(4));
        }adapter=new Custombookdetails(getContext(),mechlist,mechlist2);
        five.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }
}
