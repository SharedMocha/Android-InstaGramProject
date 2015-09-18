package com.apps.instagramproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.apps.adapters.restfuladapter;
import com.apps.interfaces.services.restfulconnections;
import com.apps.models.instagramdetails;

import java.util.ArrayList;

public class InstagramMainActivity extends AppCompatActivity {
    public ListView MasterLV;
    public ArrayList<instagramdetails> masterrestfuldata;
    public restfulconnections calltorinstagramapi;
    public restfuladapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_main);


        masterrestfuldata = new ArrayList<instagramdetails>();
        adapter = new restfuladapter(this,masterrestfuldata);
        MasterLV = (ListView) findViewById(R.id.lvMasterdetails);


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
