package com.example.samuyu.sometest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.samuyu.sometest.R;


public class MyActivity extends Activity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button openActionBarButton = (Button)findViewById(R.id.open_cutstom_activity_button);
        openActionBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomActionBarActivity();
            }

        });

        Button someActionBarButton = (Button)findViewById(R.id.open_some_style_actionbar_button);
        someActionBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSomeActionBarActivity();
            }
        });

       Button appListButton = (Button)findViewById(R.id.button3);
        appListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AppListActivity.createIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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


    private void openCustomActionBarActivity() {
        Intent intent = CustomActionBarActivity.createIntent(getApplicationContext());
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    private void openSomeActionBarActivity() {
        Intent intent = SomeStyleActionBarActivity.createIntent(getApplicationContext());
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
