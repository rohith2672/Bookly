package com.project2.bookly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Studentmainframe extends AppCompatActivity {
    TextView ongoing,closed;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmainframe);
        ongoing=findViewById(R.id.ongoing);
        closed=findViewById(R.id.completed);
        logout=findViewById(R.id.studentlogout);

        getSupportFragmentManager().beginTransaction().replace(R.id.studentframe,new studentongoingfrag()).commit();

        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.studentframe,new studentongoingfrag()).commit();
            }
        });

        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.studentframe,new studentcompletedfrag()).commit();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(i);
                finishAffinity();
            }
        });
    }

    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            //  super.onBackPressed();
            Intent i=new Intent(getApplicationContext(),Studentlogactivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(i);
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce=true;
        Toast.makeText(this, "Click Back again to Logout", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        },2000);
    }
}