package com.example.training2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuAct = getMenuInflater();
        menuAct.inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.keluar){
            startActivity(new Intent(this, LoginActivity.class));
        }
//        else if(item.getItemId()==R.id.dataKaryawan){
//            startActivity(new Intent(this, DataKaryawanActivity));
//        }
        return super.onOptionsItemSelected(item);
    }
}
