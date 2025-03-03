package com.project2.bookly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Adminmainactivity extends AppCompatActivity {
    TextView login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmainactivity);
        login=findViewById(R.id.adminlogin);
        signup=findViewById(R.id.adminsignup);

        getSupportFragmentManager().beginTransaction().replace(R.id.adminframe,new adminlogfrag()).commit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.adminframe,new adminlogfrag()).commit();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.adminframe,new adminsignfrag()).commit();

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