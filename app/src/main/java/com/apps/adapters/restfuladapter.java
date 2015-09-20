package com.apps.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.instagramproject.R;
import com.apps.models.instagramdetails;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

/**
 * Created by htammare on 9/17/2015.
 */
public class restfuladapter extends ArrayAdapter<instagramdetails> {


    public restfuladapter(Context context, ArrayList<instagramdetails> data) {
        super(context, 0, data);
    }
    instagramdetails data;


    public class ViewHolder{
        TextView name;
        TextView details;
        ImageView image;
        TextView likes;
        TextView location;
        TextView datetime;
        TextView nameontop;
        ImageView userviewimage;
    }
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            //canvas.drawC

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //return super.getView(position, convertView, parent);
        data = getItem(position);

        final ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameid);
            viewHolder.nameontop = (TextView) convertView.findViewById(R.id.nameontop);

            viewHolder.details = (TextView) convertView.findViewById(R.id.detailsid);

            viewHolder.likes = (TextView) convertView.findViewById(R.id.likes);
            viewHolder.location = (TextView) convertView.findViewById(R.id.locationn);
            viewHolder.datetime = (TextView) convertView.findViewById(R.id.datetime);

            viewHolder.image =(ImageView) convertView.findViewById(R.id.imageid);
            viewHolder.userviewimage =(ImageView) convertView.findViewById(R.id.useridimage);
            convertView.setTag(viewHolder);

        }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }



        viewHolder.name.setText(data.username);
        viewHolder.details.setText(data.caption);

        viewHolder.likes.setText(data.likes);
        viewHolder.location.setText(data.location);
        viewHolder.datetime.setText(data.datetime);
        viewHolder.nameontop.setText(data.username);


        viewHolder.image.setImageResource(0);
        viewHolder.userviewimage.setImageResource(0);
        //Picasso.with(getContext()).load("http://i.imgur.com/DvpvklR.png").into(viewHolder.image);
        //Picasso.with(getContext()).load(data.imageurl).into(viewHolder.image);

        //-ROUND USER IMAGE

        Picasso.with(getContext()).load(data.userimageurl).transform(new CircleTransform()).into(viewHolder.userviewimage);


        //----------------main image


        Picasso.with(getContext())
                .load(R.drawable.loading) // thumbnail url goes here
                .into(viewHolder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(getContext())
                                .load(data.imageurl) // image url goes here
                                .placeholder(viewHolder.image.getDrawable())
                                .into(viewHolder.image);
                    }

                    @Override
                    public void onError() {

                    }
                });


        //viewHolder.image.setImageResource(0);
        //Picasso.with(getContext()).load(data.imageurl).into(viewHolder.image);

        //ImageView imv = (ImageView) convertView.findViewById(R.id.imageid);


                return convertView;




    }


}
