package com.example.basma.movieapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Favouritemovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritemovie);

        Bundle b = getIntent().getExtras();
        if (null == savedInstanceState) {
            Favouritemoviefra a = new Favouritemoviefra();

            if (b != null)
                a.setArguments(b);//recieve data from detailActivity

            FragmentManager fr = getSupportFragmentManager();
            FragmentTransaction ft = fr.beginTransaction();
            ft.add(R.id.fragment3, a).commit();




        }
}}
