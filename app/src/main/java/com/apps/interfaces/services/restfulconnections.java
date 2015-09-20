package com.apps.interfaces.services;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;

import com.apps.adapters.restfuladapter;
import com.apps.models.instagramdetails;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by htammare on 9/16/2015.
 */
public class restfulconnections {
    public static final String url = "https://api.instagram.com/v1/tags/nofilter/media/recent?client_id=ef1374c168964c7bb368de638f736713";
    public ArrayList<instagramdetails> Arraydataobject;




    public final void  getdata(final Context context,final restfuladapter adapter,final ArrayList<instagramdetails> details)
    {
         AsyncHttpClient connectionclient = new AsyncHttpClient();
         Arraydataobject = new ArrayList<instagramdetails>();



         connectionclient.get(url, new JsonHttpResponseHandler() {
             @Override
             public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
                 //super.onSuccess(statusCode, headers, response);
                 JSONArray arrayfeed = null;
                 //JSON
                 try {
                     arrayfeed = response.getJSONArray("data");
                     details.clear();
                     for (int i = 0; i < arrayfeed.length(); i++) {
                         instagramdetails tempdate = new instagramdetails();
                         JSONObject imageorvideoMasterObject = arrayfeed.getJSONObject(i);
                         //Log.e("data", imageorvideoMasterObject.toString());
                         tempdate.mediatype = imageorvideoMasterObject.getString("type");
                         tempdate.username = imageorvideoMasterObject.getJSONObject("user").getString("username");
                         tempdate.caption = imageorvideoMasterObject.getJSONObject("caption").getString("text");
                         tempdate.imageurl = imageorvideoMasterObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                         tempdate.likes = imageorvideoMasterObject.getJSONObject("likes").getString("count");
                         tempdate.userimageurl = imageorvideoMasterObject.getJSONObject("user").getString("profile_picture");
                         tempdate.likes = tempdate.likes+" likes";
                         Log.e("type", tempdate.mediatype);
                         Log.e("name", tempdate.username);
                         Log.e("caption", tempdate.caption);
                         Log.e("iameURL", tempdate.imageurl);
                         Log.e("likes",tempdate.likes);
                         //tempdate.likes = imageorvideoMasterObject.getJSONObject("likes").getInt("likes");

                         if(imageorvideoMasterObject.isNull("location"))
                         {
                             tempdate.location = "No Location";
                         }else
                         {
                             tempdate.location = imageorvideoMasterObject.getJSONObject("location").getString("name");
                         }

                         tempdate.datetimesnew = imageorvideoMasterObject.getLong("created_time");
                         //String Date = (String) DateUtils.getRelativeTimeSpanString(imageorvideoMasterObject.getLong("created_time")*1000);
                         String date = (String) DateUtils.getRelativeTimeSpanString(tempdate.datetimesnew*1000,System.currentTimeMillis(),DateUtils.SECOND_IN_MILLIS);
                         date = date.toString().substring(0,2).trim();
                         String datestemp = date+""+"s";
                         tempdate.datetime = datestemp;
                         //Log.e("likes", Integer.toString(tempdate.likes));
                         //Log.e("location", tempdate.datetime);
                         Arraydataobject.add(tempdate);
                         details.add(tempdate);
                     }
                 } catch (Exception e) {
                     Log.e("Error", "Unable to Fetch Data");

                 }adapter.notifyDataSetChanged();


             }

             @Override
             public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                 super.onFailure(statusCode, headers, responseString, throwable);

             }
         });

    }

}
