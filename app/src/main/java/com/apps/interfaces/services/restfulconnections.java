package com.apps.interfaces.services;

import android.content.Context;
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
                         Log.e("type", tempdate.mediatype);
                         Log.e("name", tempdate.username);
                         Log.e("caption", tempdate.caption);
                         Log.e("iameURL", tempdate.imageurl);
                         Arraydataobject.add(tempdate);
                         details.add(tempdate);


                         Log.e("namo", Arraydataobject.get(i).username);
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
