package com.example.basma.movieapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;

import com.android.volley.toolbox.StringRequest;
//import com.example.basma.finalproject2phase.R;
//import android.app.FragmentTransaction;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class BlankFragment extends Fragment {
    public RequestQueue queue;
    private String url="https://api.themoviedb.org/3/movie/popular?api_key=c2214231a6f7fb99706b8c91e78aee39";
    private  JsonObjectRequest request;
    // MovieObject m,selectedmovie;
    // public List<MovieObject> ArrayOfData;
    public List<String> ArrayOfurl;
    //  ArrayOfmovie movies=new ArrayOfmovie();
    public MovieObject m;
    View v;
    GridView gridView;
    public   listener  listenerselectedmovie=new listener() {
        @Override
        public void selectedobject( String t,
                String ov, String date,
                Double avg, String posterpath,
                int id) {

        }
    };

    public interface listener {

        public void selectedobject(String t,
                                   String ov, String date,
                                   Double avg, String posterpath,
                                   int id);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listenerselectedmovie= (listener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement listener");
        }
    }


    public BlankFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v= inflater.inflate(R.layout.fragment_blank, container, false);
        gridView = (GridView)v.findViewById(R.id.gridView);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
       // setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      //  ((AppCompatActivity)getActivity()).getSupportActionBar().getDisplayOptions();

        TextView t=(TextView)v.findViewById(R.id.toolbar_title);
        queue= Volley.newRequestQueue(getContext());
        request=new JsonObjectRequest(url ,null, new Response.Listener<JSONObject>() {//error during run
            @Override
            public void onResponse(JSONObject response) {

                try {
                    MovieObject m;
                    final  ArrayOfmovie   movies=new ArrayOfmovie();

                    final    List   <MovieObject> ArrayOfData = new ArrayList<>();
                    final    List   <String> ArrayOfData1 = new ArrayList<>();
                    JSONArray array = response.getJSONArray("results");

                    for (int i = 0; i <array.length(); i++) {
                        m = new MovieObject();
                        JSONObject jsonMovie = array.getJSONObject(i);

                        String title = jsonMovie.getString("title");
                        m.setTitle(title);

                        String overview = jsonMovie.getString("overview");
                        m.setOverview(overview);
                        String urlPoster = jsonMovie.getString("poster_path");
                        m.setPosterPath(urlPoster);
                        String date = jsonMovie.getString("release_date");
                        m.setReleaseDate(date);
                        Double rate = jsonMovie.getDouble("vote_average");
                        m.setVoteAverage(rate);

                        int id = jsonMovie.getInt("id");
                        m.setId(id);



                        ArrayOfData1.add(urlPoster);
                        ArrayOfData.add(m);




                    }
                    MovieImageAdapter Adapter1 = new MovieImageAdapter(getContext(), R.layout.griditem, ArrayOfData1);

                    gridView.setAdapter(Adapter1);
                    movies.setResults(ArrayOfData);
                    //handle click event
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                            MovieObject GridItem=ArrayOfData.get(position);
                            //  MovieObject GridItem = (MovieObject) parent.getItemAtPosition(position);

                            if(listenerselectedmovie!=null)
                                listenerselectedmovie.selectedobject(GridItem.getTitle(),GridItem.getOverview(),
                                        GridItem.getReleaseDate(),GridItem.getVoteAverage(),
                                        GridItem.getPosterPath(),     GridItem.getId());








                        }



                    });



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getActivity(), "can not fetch data", Toast.LENGTH_SHORT);
                toast.show();


            }
        });
        queue.add(request);





        return v;

    }





/*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {

            return true;
        }
        if(id==R.id.star)
        {

            Intent intent=new Intent(getActivity(),favouritmovies.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    } */

}

