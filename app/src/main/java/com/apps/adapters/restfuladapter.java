package com.apps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.instagramproject.R;
import com.apps.models.instagramdetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by htammare on 9/17/2015.
 */
public class restfuladapter extends ArrayAdapter<instagramdetails> {


    public restfuladapter(Context context, ArrayList<instagramdetails> data) {
        super(context, 0, data);
    }


    public class ViewHolder{
        TextView name;
        TextView details;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //return super.getView(position, convertView, parent);
        instagramdetails data = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameid);
            viewHolder.details = (TextView) convertView.findViewById(R.id.detailsid);
            viewHolder.image =(ImageView) convertView.findViewById(R.id.imageid);
            convertView.setTag(viewHolder);

        }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }



        viewHolder.name.setText(data.username);
        viewHolder.details.setText(data.caption);
        viewHolder.image.setImageResource(0);
        Picasso.with(getContext()).load(data.imageurl).into(viewHolder.image);

        //viewHolder.image.setImageResource(0);
        //Picasso.with(getContext()).load(data.imageurl).into(viewHolder.image);

        //ImageView imv = (ImageView) convertView.findViewById(R.id.imageid);


                return convertView;




    }


}
