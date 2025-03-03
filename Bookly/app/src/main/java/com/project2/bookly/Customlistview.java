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

public class Customlistview extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> list1;
    private final ArrayList<String> list2;
    private final ArrayList<String> list3;
    private final ArrayList<String> list4;
    private final ArrayList<String> list5;
    private final ArrayList<String> list6;


    public Customlistview(@NonNull Context context, ArrayList<String> list1, ArrayList<String> list2,
                          ArrayList<String> list3, ArrayList<String> list4, ArrayList<String> list5, ArrayList<String> list6) {
        super(context, R.layout.customtransactionlayout,list1);
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        this.list6 = list6;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.customtransactionlayout,null,true);
        TextView bookname=rowView.findViewById(R.id.bname);
        TextView borrowdate=rowView.findViewById(R.id.borrowdate);
        TextView estretdate=rowView.findViewById(R.id.estrutndate);
        TextView status=rowView.findViewById(R.id.Status);
        TextView actreturndate=rowView.findViewById(R.id.areturndate);
        TextView fine=rowView.findViewById(R.id.fine);



        bookname.setText(list1.get(position));
        borrowdate.setText("Borrow date     : "+list2.get(position));
        estretdate.setText("Est. Return date: "+list3.get(position));
            status.setText("Status : "+list4.get(position));
            actreturndate.setText("returned date: "+ list5.get(position));
              fine.setText("Fine   : "+list6.get(position));
        return rowView;
    }
}
