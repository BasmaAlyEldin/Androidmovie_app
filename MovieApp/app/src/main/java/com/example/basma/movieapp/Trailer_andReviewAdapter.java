package com.example.basma.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by basma on 1/21/2017.
 */
public class Trailer_andReviewAdapter extends BaseAdapter {
    Context context;
    private int layoutResourceId;
    ArrayList<String> Data =new ArrayList<>();
    LayoutInflater inflater;

    public Trailer_andReviewAdapter(Context c,int layoutResourceId,ArrayList A)
    {
        context=c;
        this.layoutResourceId=layoutResourceId;
        Data=A;

    }
    @Override
    public int getCount() {
        return Data.size();
    }

    @Override
    public Object getItem(int position) {
        return Data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        TextView t;

        if(v==null)
        {


            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.trailerorreviewitem, null);
            t=(TextView) v.findViewById(R.id.trailerorRev) ;




        }
        else
        {
            t=(TextView) v.findViewById(R.id.trailerorRev) ;
        }


       // t.setText("trailer #"+(position+1));

        t.setText(Data.get(position));


        return v;
    }
}
