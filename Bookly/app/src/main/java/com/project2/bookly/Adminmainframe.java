package com.project2.bookly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Adminmainframe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmainframe);

        BottomNavigationView botnav = findViewById(R.id.botnav);
        botnav.setOnNavigationItemSelectedListener(navlistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new adminhomefrag()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new Bookmasterfrag()).commit();
                Toast.makeText(getApplicationContext(), "Book Creation", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new Studentcreationfrag()).commit();
                Toast.makeText(getApplicationContext(), "Student Creation", Toast.LENGTH_SHORT).show();

                break;


            case R.id.item3:
                Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(i);
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navlistener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfrag = null;
            switch (item.getItemId()) {
                case R.id.Bookfrag:
                    selectedfrag = new bookdetails();
                    break;
                case R.id.booklend:
                    selectedfrag = new Booklendfrag();
                    break;
                case R.id.bookreturn:
                    selectedfrag = new Bookreturnfrag();
                    break;
                case R.id.transactions:
                    selectedfrag = new Booktransactions();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, selectedfrag).commit();
            return true;
        }
    };

    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
          //  super.onBackPressed();
            Intent i=new Intent(getApplicationContext(),Adminmainactivity.class);
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