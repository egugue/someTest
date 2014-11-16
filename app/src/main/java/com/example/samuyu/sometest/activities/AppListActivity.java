package com.example.samuyu.sometest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.adapters.AppAdapter;
import com.example.samuyu.sometest.entities.AppInfo;

import java.util.List;

public class AppListActivity extends Activity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, AppListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        /*
        List<AppInfo> appInfoList = AppInfo.readAppInfo(this);
        AppAdapter adapter = new AppAdapter(this);
        ListView listView = (ListView)findViewById(R.id.app_list);
        listView.setAdapter(adapter);
        adapter.addAll(appInfoList);
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
