package com.example.basma.movieapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


public class Favouritemoviefra extends Fragment {
ArrayList<String> ArrayOfData=new ArrayList<>();
    View v;
    public Favouritemoviefra() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_favouritemoviefra, container, false);

        Bundle b=getArguments();
      String s=  b.getString("pos");
        ArrayOfData.add(s);

     GridView   gridView = (GridView)v.findViewById(R.id.gridView);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        MovieImageAdapter Adapter1 = new MovieImageAdapter(getContext(), R.layout.griditem, ArrayOfData);

        gridView.setAdapter(Adapter1);


        return v;
    }


}
