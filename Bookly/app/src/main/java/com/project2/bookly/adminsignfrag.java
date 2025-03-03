package com.project2.bookly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class adminsignfrag extends Fragment {
    EditText user,pass;
    Button signup;
    admindatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.adminsignlayout,container,false);
        user=v.findViewById(R.id.adminsignuser);
        pass=v.findViewById(R.id.adminsignpassword);
        signup=v.findViewById(R.id.adminsignbut);

        db=new admindatabase(getContext());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String username=user.getText().toString();
               String password=pass.getText().toString();

                if(username.equals("")||password.equals("")){
                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }else {
                    boolean insertdata = db.insertdata(username, password);
                    if (insertdata == true) {
                        Toast.makeText(getContext(), "Account created", Toast.LENGTH_SHORT).show();
                        user.setText("");
                        pass.setText("");
                        getFragmentManager().beginTransaction().replace(R.id.adminframe,new adminlogfrag()).commit();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        return v;
    }
}
