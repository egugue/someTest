package com.example.samuyu.sometest.activities.iosched;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.activities.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ImageListActivity extends BaseActivity {

    @InjectView(R.id.image_list_image)
    ImageView mImageView;

    public static Intent createIntent(Context context) {
        return new Intent(context, ImageListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.inject(this);

        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .into(mImageView);
    }

    @OnClick(R.id.image_list_image)
    public void onClickImage() {
        String transtionName = getString(R.string.transiton_name_image);


        ActivityOptions options = ActivityOptions.
                makeSceneTransitionAnimation(ImageListActivity.this, mImageView, transtionName);

        Intent intent = DetailActivity.createIntent(getApplicationContext());
        startActivity(intent, options.toBundle());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_list, menu);
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
