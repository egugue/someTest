package com.example.samuyu.sometest.activities.iosched;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.activities.BaseActivity;
import com.example.samuyu.sometest.activities.MyActivity;
import com.example.samuyu.sometest.views.ObservableScrollView;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailActivity extends BaseActivity implements ObservableScrollView.ScrollViewListener{
    private static final String TAG = "DetailActivity";

    /* View Inject */
    @InjectView(R.id.detail_background_image)
    ImageView mBackgroundImageView;

    @InjectView(R.id.detail_background_image_container)
    FrameLayout mBackgroundImageLayout;

    @InjectView(R.id.detail_scrollview)
    ObservableScrollView mScrollView;

    Toolbar mToolbar;

    @InjectView(R.id.detail_contents_layout)
    LinearLayout mContentsLayout;

    /* constants */
    private static final float PHOTO_ASPECT_RATIO = 1.7777777f;

    /* private members */
    private int mBackImageHeight;
    private int mToolbarHeight;
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener
            = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            recomputeView();
        }
    };

    public static Intent createIntent(Context context) {
        return new Intent(context, DetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        setupFloatingWindow();

        Toolbar toolbar = getToolBar();
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                navigateUpToFromChild(DetailActivity.this, intent);
            }
        });

        mToolbar = toolbar;
        mScrollView.setOnScrollViewListener(this);

        ViewTreeObserver vto = mScrollView.getViewTreeObserver();
        if (vto.isAlive()) {
            vto.addOnGlobalLayoutListener(mGlobalLayoutListener);
        }

        Picasso picasso = Picasso.with(this);
        picasso.setDebugging(true);
        picasso.setLoggingEnabled(true);
        picasso.load("http://i.imgur.com/DvpvklR.png")
                .into(mBackgroundImageView);
    }

    private void setupFloatingWindow() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width     = getResources().getDimensionPixelSize(R.dimen.session_details_floating_width);
        params.height    = getResources().getDimensionPixelSize(R.dimen.session_details_floating_height);
        params.alpha     = 1;
        params.dimAmount = 0.4f;
        params.flags    |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);
    }


    private void recomputeView() {
        mToolbarHeight = mToolbar.getHeight();

        //mBackImageHeight = 0;
        if (true) {
            //背景画像の高さを縮小する
            //mBackImageHeight = mBackgroundImageView.getWidth();
            mBackImageHeight = (int) (mBackgroundImageView.getWidth() / PHOTO_ASPECT_RATIO );
            mBackImageHeight = Math.min(mBackImageHeight, mScrollView.getHeight()*2/3);
        }

        ViewGroup.LayoutParams lp = mBackgroundImageLayout.getLayoutParams();
        if (lp.height != mBackImageHeight) {
            lp.height = mBackImageHeight;
            mBackgroundImageLayout.setLayoutParams(lp);
        }

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)
                mContentsLayout.getLayoutParams();
        if (mlp.topMargin != mBackImageHeight+mToolbarHeight) {
            mlp.topMargin = mBackImageHeight+mToolbarHeight;
            mContentsLayout.setLayoutParams(mlp);
        }

        Log.d(TAG, "recomputeViewが終了");
        onScrollChanged(null, 0, 0, 0, 0);
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        //Log.d("HOGE", "x = "+x+  "y = "+y+  "oldx = "+oldx + "oldy = "+oldy);

        int scrollY = mScrollView.getScrollY();

        float newTop = Math.max(mBackImageHeight, scrollY);
        mToolbar.setTranslationY(newTop);

        mBackgroundImageLayout.setTranslationY(scrollY*0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
