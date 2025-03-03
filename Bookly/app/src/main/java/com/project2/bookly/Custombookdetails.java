package com.project2.bookly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custombookdetails extends ArrayAdapter<String> {
   private final Context context;
   private final ArrayList<String> list1;
    private final ArrayList<String> list2;


    public Custombookdetails(Context context, ArrayList<String> list1, ArrayList<String> list2) {
        super(context,R.layout.custombookdetails,list1);
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.custombookdetails,null,true);
        TextView name=rowView.findViewById(R.id.textView5);
        TextView count=rowView.findViewById(R.id.textView6);

        name.setText(list1.get(position));
        count.setText(list2.get(position));

        return  rowView;
    }
}
