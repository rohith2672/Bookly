package com.project2.bookly;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class adminlogfrag extends Fragment {
    EditText user,pass;
    Button login;
    admindatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.adminloglayout,container,false);
        user=v.findViewById(R.id.adminusertext);
        pass=v.findViewById(R.id.adminpasswordtext);
        login=v.findViewById(R.id.adminloginbut);

        db=new admindatabase(getContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=user.getText().toString();
                String password=pass.getText().toString();

                Boolean check=db.userddetail(username,password);
                if(check==true){
                    Toast.makeText(getContext(),"login success", Toast.LENGTH_SHORT).show();
                   Intent gotohome=new Intent(getActivity(),Adminmainframe.class);
                    user.setText("");
                    pass.setText("");
                    gotohome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                   startActivity(gotohome);
                   getActivity().finishAffinity();

                }else{
                    Toast.makeText(getContext(),"Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return v;
    }
}
