package com.apps.instagramproject;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.apps.adapters.restfuladapter;
import com.apps.interfaces.services.fetchmorerestfulcall;
import com.apps.interfaces.services.restfulconnections;
import com.apps.models.instagramdetails;

import java.util.ArrayList;

public class InstagramMainActivity extends AppCompatActivity {
    public ListView MasterLV;
    public ArrayList<instagramdetails> masterrestfuldata;
    public restfulconnections calltorinstagramapi;
    public restfuladapter adapter;
    private SwipeRefreshLayout swipeContainer;
    public  fetchmorerestfulcall refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh);



        masterrestfuldata = new ArrayList<instagramdetails>();
        adapter = new restfuladapter(this,masterrestfuldata);
        MasterLV = (ListView) findViewById(R.id.lvMasterdetails);

        //refresh class
        refresh = new fetchmorerestfulcall();


        //swipe to refresh
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        Log.e("11111111111111111111", "click");
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                Log.e("refresjsssss", "masterrestfuldata.get(0).nexturl");
                refresh.getdata(getBaseContext(), adapter, masterrestfuldata, masterrestfuldata.get(0).nexturl, swipeContainer);

            }
        });






        calltorinstagramapi = new restfulconnections();
        calltorinstagramapi.getdata(this,adapter,masterrestfuldata);
        MasterLV.setAdapter(adapter);






        //masterrestfuldata = calltorinstagramapi.Arraydataobject;




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void refreshadapater()
    {
        adapter.notifyDataSetChanged();
    }


}
