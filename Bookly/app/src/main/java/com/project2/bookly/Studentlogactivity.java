package com.project2.bookly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Studentlogactivity extends AppCompatActivity {
    EditText user,pass;
    Button login;
    studentdatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogactivity);
        user=findViewById(R.id.studentusertext);
        pass=findViewById(R.id.studentpasswordtext);
        login=findViewById(R.id.studentloginbut);

        db=new studentdatabase(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=user.getText().toString();
                String password=pass.getText().toString();

                Boolean check=db.userddetail(username,password);
                if(check==true){
                    Toast.makeText(getApplicationContext(),"login success", Toast.LENGTH_SHORT).show();
                    Intent gotohome=new Intent(getApplicationContext(),Studentmainframe.class);
                    gotohome.putExtra("name",username);
                    user.setText("");
                    pass.setText("");
                    gotohome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    startActivity(gotohome);
                    finishAffinity();

                }else{
                    Toast.makeText(getApplicationContext(),"Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(i);
        finishAffinity();
    }
}