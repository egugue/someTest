package com.example.samuyu.sometest.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.views.ObservableScrollView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ToolbarActivity extends BaseActivity {

    @InjectView(R.id.scrollView)
    ObservableScrollView mScrollView;

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitleTextView;

    public static Intent createIntent(Context context) {
        return new Intent(context, ToolbarActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        ButterKnife.inject(this);
        setupToolbar();
    }

    private void setupToolbar() {

        final Toolbar toolbar = getToolBar();
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        Log.d("HOGE", "Onclick");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Onclik", Toast.LENGTH_LONG).show();
                Log.d("HOGE", "Onclick");
                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                boolean result = navigateUpToFromChild(ToolbarActivity.this, intent);
                Log.d("HOGE", "result = "+Boolean.toString(result));
            }
        });

        // ツールバーをアクションバーとしてセット
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().setStatusBarColor(R.color.indigo_400);
        }

        mToolbarTitleTextView.setAlpha(0);
        toolbar.getBackground().setAlpha(0);

        mScrollView.setOnScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                //Log.d("HOGE", "x = "+x +"y = "+y +"oldx = "+oldx +"oldy = "+oldy );
                int alpha;

                if (y < 0) {
                    alpha = 0;
                } else if (y > 500) {
                    alpha = 255;
                } else {
                    alpha = (int) ((y / 500.0) * 255);
                }

                toolbar.getBackground().setAlpha(alpha);
                mToolbarTitleTextView.setAlpha(alpha);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
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
}
