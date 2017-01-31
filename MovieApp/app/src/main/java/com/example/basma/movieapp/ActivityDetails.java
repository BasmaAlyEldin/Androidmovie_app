package com.example.basma.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ActivityDetails extends AppCompatActivity implements MovieDetail.favouritmovieListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_details);

        Bundle b = getIntent().getExtras();
        if (null == savedInstanceState) {
            MovieDetail a = new MovieDetail();

            if (b != null)
                a.setArguments(b);//recieve data from mainActivity

            FragmentManager fr = getSupportFragmentManager();
            FragmentTransaction ft = fr.beginTransaction();
            ft.add(R.id.fragment, a).commit();

          //  a.onAttach(this);



    }

    }




    //this method implemented from interface to transfere the posterpath to favourite but i canceld this until
    //update this app
    @Override
    public void favmovie(String p) {/*
       SharedPreferences  preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("imageposterpath", p); */

        Intent i = new Intent(this, Favouritemovie.class);
        i.putExtra("pos",p);
        startActivity(i);
    }
}
