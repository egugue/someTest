package com.example.samuyu.sometest.activities;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.samuyu.sometest.R;

/**
 * Created by toyamaosamuyu on 2014/12/21.
 */
public class BaseActivity extends ActionBarActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getToolBar();
    }

    /**
     * 毎回setSupportActionbarを呼ぶのがめんどうなので、ここで集約
     * @return
     */
    protected Toolbar getToolBar() {
        if (mToolbar != null) return mToolbar;

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (mToolbar != null) {
            Log.d(TAG, "setSupportActionBar()");
            setSupportActionBar(mToolbar);
        }

        return mToolbar;
    }
}
