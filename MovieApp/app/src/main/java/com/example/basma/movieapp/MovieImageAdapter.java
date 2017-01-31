package com.example.basma.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basma on 9/22/2016.
 */
public class MovieImageAdapter extends BaseAdapter {

    Context context;
    private int layoutResourceId;
    List<String> ArrayOfData =new ArrayList<>();
    LayoutInflater inflater;


    public MovieImageAdapter(Context context,int layoutResourceId,List<String>ArrayOfData)
    {


        this.context= context;
        this.ArrayOfData=ArrayOfData;
        this.layoutResourceId = layoutResourceId;


    }

    @Override
    public int getCount() {
        return ArrayOfData.size();
    }

    @Override
    public String getItem(int i) {
        return ArrayOfData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        ImageView img ;

        if(v==null)
        {


            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.griditem, null);
            img=(ImageView)v.findViewById(R.id.grid_item_image) ;




        }
        else
        {
            img = (ImageView)v.findViewById(R.id.grid_item_image);
        }
        //MovieObject Griditem=ArrayOfData.get(i) ;

        String url="http://image.tmdb.org/t/p/w185"+ArrayOfData.get(i);

        Picasso.with(context).load(url).into(img);
        return v;
    }

}
