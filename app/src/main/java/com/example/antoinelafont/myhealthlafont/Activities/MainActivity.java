package com.example.antoinelafont.myhealthlafont.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoinelafont.myhealthlafont.R;
import com.example.antoinelafont.myhealthlafont.Helpers.DAO;
import com.example.antoinelafont.myhealthlafont.Models.Personne;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, DataProviderActivity.class);
        startActivity(intent);
    }
}
