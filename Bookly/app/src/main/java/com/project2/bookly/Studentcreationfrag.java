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

public class Studentcreationfrag extends Fragment {
    EditText sname, sfathername, srollno, sphone, spassword;
    Button save, update, get, delete;
    Spinner sdept,ssec;
    List<String> seclist,sdeptlist;
    ArrayAdapter<String> sdeptadapter,secadapter;
    studentdatabase db;
    String name, fname, rollno, phone, dept, password,sec;
    Boolean checkresult, updateresult;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.studentcreation,container,false);

        sname = v.findViewById(R.id.Sname);
        sfathername = v.findViewById(R.id.SFname);
        srollno = v.findViewById(R.id.Srollno);
        spassword = v.findViewById(R.id.sPassword);
        sphone = v.findViewById(R.id.Sphoneno);
        sdept = v.findViewById(R.id.spindept);
        ssec=v.findViewById(R.id.spinyear);
        save = v.findViewById(R.id.Ssave);
        update = v.findViewById(R.id.supdate);
        delete = v.findViewById(R.id.sdelete);
        get = v.findViewById(R.id.get);



        db = new studentdatabase(getContext());

        seclist= Arrays.asList(getResources().getStringArray(R.array.yearlist));
        secadapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,seclist);
        ssec.setAdapter(secadapter);

        sdeptlist= Arrays.asList(getResources().getStringArray(R.array.deptlist));
        sdeptadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sdeptlist);
        sdept.setAdapter(sdeptadapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = sname.getText().toString();
                fname = sfathername.getText().toString();
                rollno = srollno.getText().toString();
                password = spassword.getText().toString();
                phone = sphone.getText().toString();
                sec = ssec.getSelectedItem().toString();
                dept = sdept.getSelectedItem().toString();

                if (name.equals("") || fname.equals("") || rollno.equals("") || password.equals("") || phone.equals("")) {
                    Toast.makeText(getContext(), "The above fields shouldn't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    checkresult = db.insertdata(name, fname, rollno, password, phone, dept,sec);
                    if (checkresult == true) {
                        Toast.makeText(getContext(), "Student created", Toast.LENGTH_LONG).show();
                        sname.setText("");
                        sfathername.setText("");
                        srollno.setText("");
                        spassword.setText("");
                        sphone.setText("");

                    } else {
                        Toast.makeText(getContext(), "not created", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=sname.getText().toString();
                fname=sfathername.getText().toString();
                rollno=srollno.getText().toString();
                password=spassword.getText().toString();
                phone=sphone.getText().toString();
                dept=sdept.getSelectedItem().toString();
                sec=ssec.getSelectedItem().toString();

                if(name.equals("") || fname.equals("") ||rollno.equals("")||password.equals("")||phone.equals("")){
                    Toast.makeText(getContext(), "The above fields shouldn't be empty", Toast.LENGTH_SHORT).show();
                }else {
                    updateresult = db.updatedata(name, fname, rollno,password, phone, dept, sec);
                    if(updateresult==true){
                        Toast.makeText(getContext(),"Student updated",Toast.LENGTH_LONG).show();
                        sname.setText("");
                        sfathername.setText("");
                        srollno.setText("");
                        spassword.setText("");
                        sphone.setText("");

                    }else{
                        Toast.makeText(getContext(),"not updated",Toast.LENGTH_LONG).show();
                    } }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemselected = srollno.getText().toString();
                if (itemselected.equals("")) {
                    Toast.makeText(getContext(), "Invalid student ID", Toast.LENGTH_LONG).show();
                } else {
                    Boolean checkdeleted = db.deletedata(itemselected);
                    if (checkdeleted == true) {
                        Toast.makeText(getContext(), "student deleted", Toast.LENGTH_LONG).show();
                        sname.setText("");
                        sfathername.setText("");
                        srollno.setText("");
                        spassword.setText("");
                        sphone.setText("");
                    } else {
                        Toast.makeText(getContext(), "not deleted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getroll=srollno.getText().toString();
                if(getroll.equals("")){
                    Toast.makeText(getContext(),"This field shouldn't be empty",Toast.LENGTH_LONG).show();
                }else{
                    Cursor rollnumber=db.getdata(getroll);
                    if(rollnumber.getCount()<=0){
                        Toast.makeText(getContext(),"Invalid student ID",Toast.LENGTH_LONG).show();
                    }else{
                        while(rollnumber.moveToNext()){
                            String tsname=rollnumber.getString(0);
                            String tfname=rollnumber.getString(1);
                            String tpass=rollnumber.getString(3);
                            String tphone=rollnumber.getString(4);
                            String tdept=rollnumber.getString(5);
                            String tsec=rollnumber.getString(6);

                            sname.setText(tsname);
                            sfathername.setText(tfname);
                            sphone.setText(tphone);
                            spassword.setText(tpass);
                            int spinpos=sdeptadapter.getPosition(tdept);
                            sdept.setSelection(spinpos);
                            int spinsec=secadapter.getPosition(tsec);
                            ssec.setSelection(spinsec);

                        }
                    }
                }
            }
        });










        return v;
    }
}
