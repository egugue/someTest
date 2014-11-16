package com.example.samuyu.sometest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomActionBarActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mDataList;
    private MyAdapter mAdapter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CustomActionBarActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_action_bar);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDataList = new ArrayList<String>();
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");
        mDataList.add("aaa");

        Log.d("hoge", mDataList.toString());


        mAdapter = new MyAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);

        Button button = (Button)findViewById(R.id.adding_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addToList("adding", 0);
                mRecyclerView.scrollToPosition(0);
            }
        });

        Button deleteButton = (Button)findViewById(R.id.deleting_item_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mAdapter.removeFromList(mAdapter.getItemCount());
                mAdapter.removeFromList(0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_action_bar, menu);
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
