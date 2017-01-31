package com.example.basma.movieapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BlankFragment.listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState) {
            BlankFragment b = new BlankFragment();
            b.onAttach(this);
            FragmentManager fr = getSupportFragmentManager();
            FragmentTransaction ft = fr.beginTransaction();
            ft.add(R.id.fragment2, new BlankFragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void selectedobject(String t,
                               String ov, String date,
                               Double avg, String posterpath,
                               int id)  {
        Intent i = new Intent(this, ActivityDetails.class);
        i.putExtra("title",t);
        i.putExtra("overview",ov);
        i.putExtra("release_date", date);
        i.putExtra("poster_path",posterpath);
        i.putExtra("vote_average",avg);
        i.putExtra("id", id);

        startActivity(i);
      //  Intent i2=new Intent(this,TrailerandRevActivty.class);
      //  i2.putExtra("id",id);
     //   startActivity(i2);
    }
}
