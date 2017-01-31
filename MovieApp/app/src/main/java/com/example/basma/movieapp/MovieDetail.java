package com.example.basma.movieapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MovieDetail extends Fragment {


View v;

    public MovieObject movieselected=new MovieObject();
    String t;
    String ov;
    Double avg;
    String date;
    String m;
    String posterpath;
    int id;


    ImageView imageView;
    TextView title;
    TextView overview;
    TextView rate;
    TextView date1;
    TextView movieName;
    String url;

    String modifyIfTrailerorReview;
    ListView list;
    ListView list2;

    ArrayList <String> youtublist1=new ArrayList();
    ArrayList <String> bookmarklist=new ArrayList();

    SharedPreferences preferences;

    public MovieDetail() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*

    private boolean readState() {
        SharedPreferences aSharedPreferences = getActivity().getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        return aSharedPreferences.getBoolean("State", true);
    }
    private void saveState(boolean isFavourite) {
        if(isFavourite==false)
        {
            SharedPreferences aSharedPreferences = (getActivity()).getSharedPreferences(
                    "Favourite", Context.MODE_PRIVATE);
            SharedPreferences.Editor aSharedPreferencesEdit = aSharedPreferences.edit();
            //aSharedPreferencesEdit.remove("title");
            //aSharedPreferencesEdit.remove("overview");
            //aSharedPreferencesEdit.remove("release_date");
           // aSharedPreferencesEdit.remove("vote_average");
            aSharedPreferencesEdit.remove("poster_path");
            bookmarklist.remove(posterpath);

            aSharedPreferencesEdit.commit();


        }
        else {

            SharedPreferences aSharedPreferences = (getActivity()).getSharedPreferences(
                    "Favourite", Context.MODE_PRIVATE);
            SharedPreferences.Editor aSharedPreferencesEdit = aSharedPreferences
                    .edit();
            //aSharedPreferencesEdit.putString("title", title.getText().toString());
          // aSharedPreferencesEdit.putString("overview", overview.getText().toString());
            //aSharedPreferencesEdit.putString("release_date", date.getText().toString());
            //aSharedPreferencesEdit.putString("vote_average", rate.getText().toString());
            //aSharedPreferencesEdit.putString("title", movieName.getText().toString());
            aSharedPreferencesEdit.putString("poster_path",posterpath.toString());
            bookmarklist.add(posterpath);

            aSharedPreferencesEdit.commit();



        }
    } */




    public interface favouritmovieListener
    {
        public void favmovie(String p);
    }

    public    favouritmovieListener  listener=new favouritmovieListener() {
        @Override
        public void favmovie(String p)
        {

        }


    };

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener= ( favouritmovieListener ) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement listener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_movie_detail, container, false);
      Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);



        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

       TextView t1=(TextView)v.findViewById(R.id.toolbar_title1);
        Bundle bundle = getArguments();
        if (bundle != null) {
            t = bundle.getString("title");// t= movies.getResults().get(p).getTitle(); //
            ov = bundle.getString("overview");//ov=movies.getResults().get(p).getOverview(); //
            date = bundle.getString("release_date");//  d=movies.getResults().get(p).getReleaseDate();//
            posterpath = bundle.getString("poster_path");
            avg = bundle.getDouble("vote_average");// r=movies.getResults().get(p).getVoteAverage();//
            id=bundle.getInt("id");// id=movies.getResults().get(p).getId(); //
        }
        movieName = (TextView) v.findViewById(R.id.moviename);
        movieName.setText(t);


        imageView = (ImageView) v.findViewById(R.id.imageView);
         url = "http://image.tmdb.org/t/p/w185" + posterpath;
        Picasso.with(getActivity()).load(url).into(imageView);
        movieselected.setPosterPath(posterpath);

        rate = (TextView) v.findViewById(R.id.rate1);
        String av=""+avg;
        rate.setText(av);
        movieselected.setVoteAverage(avg);

        date1 = (TextView) v.findViewById(R.id.date);
        date1.setText(date);
        movieselected.setReleaseDate(date);

        overview = (TextView) v.findViewById(R.id.overview);
        overview.setText("overview : "+ov);
        movieselected.setOverview(ov);

        //ScrollView scrollView = (ScrollView) v.findViewById(R.id);
        //scrollView.setVisibility(View.VISIBLE); //this make error */

        //trailer
//TextView trailer=(TextView)v.findViewById(R.id.trailertext);
 //trailer.setText("Trailers:");


       JsonObjectRequest request;
        url="https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=c2214231a6f7fb99706b8c91e78aee39";
        RequestQueue queue;
        queue= Volley.newRequestQueue(getContext());
        request=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                try {
                    final   ArrayList <String>youtublist=new ArrayList();
                    final   ArrayList <String>youtublist1=new ArrayList();
                    JSONArray Idarray =response.getJSONArray("results");
                    int j=1;
                    youtublist1.add("Trailers :");
                    youtublist.add("trailers");
                    for (int i = 0; i < Idarray.length(); i++) {
                        JSONObject jsonmovie=Idarray.getJSONObject(i);

                        String type=jsonmovie.getString("type");

                        String key=jsonmovie.getString("key");


                        if(type.equals("Trailer"))
                        {
                            String urlYoutube="https://www.youtube.com/watch?v="+key;
                            youtublist.add(urlYoutube);

                            youtublist1.add("trailer #"+j);
                              j++;
                        }
                        else
                        {
                            continue;
                        }

                    }
                    Toast.makeText(getContext(),"You can press on any trailer to show short video ", Toast.LENGTH_SHORT).show();
                    Trailer_andReviewAdapter Adapter1=new Trailer_andReviewAdapter(getActivity(),R.layout.trailerorreviewitem, youtublist1);
                    list=(ListView)v.findViewById(R.id.listViewTr);
                    list.setClickable(true);
                    list.setAdapter(Adapter1);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
                            // When clicked, show youtup
                            Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse( youtublist.get(position)));
                            startActivity(i);
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

            }
        });



        queue.add(request);
//reviews
      //  TextView review=(TextView)v.findViewById(R.id.rev);
      //  review.setText("Reviews");

        JsonObjectRequest request2;
      String  url2="https://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=c2214231a6f7fb99706b8c91e78aee39";
        queue= Volley.newRequestQueue(getContext());
        request2=new JsonObjectRequest(url2, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                try {
                    final   ArrayList <String>reviewlist=new ArrayList();
                    JSONArray Idarray =response.getJSONArray("results");

                    reviewlist.add("Reviews :");
                    for (int i = 0; i < Idarray.length(); i++) {
                        JSONObject jsonmovie=Idarray.getJSONObject(i);

                        String authorname=jsonmovie.getString("author");
                        String review=jsonmovie.getString("content");


                        reviewlist.add("Author: "+authorname+"    "+"     "+"     "+"  "+"     "+"  "+"   "+review);
                    }

                    if(reviewlist.size()==1)
                    {
                        //Toast toast = Toast.makeText(getContext(),"This film has not review", Toast.LENGTH_SHORT);
                       // toast.show();
                        reviewlist.add("This movie has not any review");
                    }

                        Trailer_andReviewAdapter Adapter2 = new Trailer_andReviewAdapter(getActivity(), R.layout.trailerorreviewitem, reviewlist);
                        list2 = (ListView) v.findViewById(R.id.listViewRev);

                        list2.setAdapter(Adapter2);

                }


                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        queue.add(request2);


        ImageButton backbutton=(ImageButton)v.findViewById(R.id.backbutton);
      backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i=new Intent(getActivity(),MainActivity.class);
               startActivity(i);
            }
        });






/*//saveButton
<Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/savebtn"
        android:layout_below="@id/date"
        android:text="Mark as favourite"
        android:layout_alignParentRight="true"
        android:layout_marginTop="17dp"
        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/unsavebtn"
        android:layout_below="@id/savebtn"
        android:text="Mark as unfavourite"
        android:layout_alignParentRight="true"
        android:layout_marginTop="17dp"
        />
 */
/*
        Button b=(Button)v.findViewById(R.id.savebtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.favmovie(posterpath);

            }
        });

        /*
          <ImageButton android:id="@+id/favouritebtn"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginLeft="250dp"
        android:src="@mipmap/ic_staroff"
        android:background="#00ffffff"
        android:onClick="onToggleStar"
        android:clickable="true"/>

    <ImageButton android:id="@+id/favbtn2"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_marginLeft="250dp"
        android:src="@mipmap/ic_staroff"
        android:background="#00ffffff"
        android:onClick="onToggleStar"
        android:clickable="true"/>
         */
        /*
        final   ImageButton  im2 = (ImageButton) v.findViewById(R.id.favbtn2);
    final   ImageButton  im1 = (ImageButton) v.findViewById(R.id.favouritebtn);
        im1.setVisibility(View.VISIBLE);
        im2.setVisibility(View.INVISIBLE);
       im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*boolean isFavourite = readState();

                if (isFavourite) {
                    im.setBackgroundResource(R.mipmap.ic_staroff);
                    isFavourite = false;
                    saveState(isFavourite);

                } else {
                    im.setBackgroundResource(R.mipmap.ic_staron);
                    isFavourite = true;
                    saveState(isFavourite);

    }
                im2.setVisibility(View.VISIBLE);
                im1.setVisibility(View.INVISIBLE);
            }
        }); */

        return  v;
    }


/*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();



        if (id == R.id.action_setting1) {
            AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
            builder1.setMessage("Are you want to transfere to your favourite movies "
            );
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i=new Intent(getActivity(),Favouritemovie.class);
                            startActivity(i);
                        }
                    }
            );
            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }
            );

            return super.onOptionsItemSelected(item);  }
/

   */


}

