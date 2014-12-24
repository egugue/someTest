package com.example.samuyu.sometest.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.activities.iosched.DetailActivity;
import com.example.samuyu.sometest.activities.iosched.ImageListActivity;
import com.example.samuyu.sometest.views.CheckableFrameLayout;
import com.example.samuyu.sometest.views.FloatingActionButton;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MyActivity extends Activity {

    /*
    @Inject Depended mDepended;

    private MyActivityComponent component;
    @Component(modules = DependedModule.class)
    interface MyActivityComponent {
        void inject();
    }
    */

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MyActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ButterKnife.inject(this);

        //component = Dagger_MyActivityComponent.create();
        //component.inject();

        /*
        ObjectGraph objectGraph = ObjectGraph.create(new DependedModule());
        objectGraph.inject(this);
        */
    }


    @OnClick(R.id.open_cutstom_activity_button)
    public void openCustomActionBarActivity() {
        Intent intent = CustomActionBarActivity.createIntent(getApplicationContext());
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    @OnClick(R.id.open_some_style_actionbar_button)
    public void openSomeActionBarActivity() {
        Intent intent = SomeStyleActionBarActivity.createIntent(getApplicationContext());
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

    @OnClick(R.id.button3)
    public void openAppListActivity() {
        Intent intent = AppListActivity.createIntent(getApplicationContext());
        startActivity(intent);
    }

    @OnClick(R.id.open_toobar_activity)
    public void openToolbarActivity() {
        Intent intent = ToolbarActivity.createIntent(getApplicationContext());
        startActivity(intent);
    }

    @OnClick(R.id.open_detail_activity_button)
    public void openDetailActivity() {
        Intent intent = DetailActivity.createIntent(getApplicationContext());
        startActivity(intent);
    }

    @OnClick(R.id.open_image_list_activity_button)
    public void openImageListActivity() {
        Intent intent = ImageListActivity.createIntent(getApplicationContext());
        startActivity(intent);
    }

    /*
    うまくいかない
    @OnClick(R.id.my_floating_action_button)
    //public void onClickFab(FloatingActionButton floatingActionButton) {
    public void onClickFab(CheckableFrameLayout floatingActionButton) {
        floatingActionButton.toggle();
        //floatingActionButton.toggleIconWithAnimation();
    }
    */


    @InjectView(R.id.my_selector_icon) ImageView mIconImageView;
    @InjectView(R.id.my_selector_layout) CheckableFrameLayout mCheckableFrameLayout;
    @OnClick(R.id.my_selector_layout)
    public void onClickMySelectorTestLayout() {

        // toggleをすることで、CheckableFrameLayout内で、
        // setChecked経由でrefreshDrawableStateが呼ばれ、drawableファイルのselectorに書いた設定のように変更される
        mCheckableFrameLayout.toggle();

        boolean isChecked = mCheckableFrameLayout.isChecked();
        setCheckedImageViewWithAnimate(mIconImageView, isChecked);

    }

    private void setCheckedImageViewWithAnimate(final ImageView imageView, boolean isChecked) {

        final int duration = 200;
        final int iconResId = isChecked
                ? R.drawable.add_schedule_button_icon_checked
                : R.drawable.add_schedule_button_icon_unchecked;


        //一旦消すアニメーション
        Animator outAnimator = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0f);
        outAnimator.setDuration(duration/2);
        outAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setImageResource(iconResId);
            }
        });

        //変更した画像をアニメーションを使って表示する
        AnimatorSet inAnimator = new AnimatorSet();
        inAnimator.setDuration(duration);
        inAnimator.playTogether(
                ObjectAnimator.ofFloat(imageView, View.ALPHA, 1f),
                ObjectAnimator.ofFloat(imageView, View.ROTATION, 0f, 360f),
                ObjectAnimator.ofFloat(imageView, View.SCALE_X, 0f, 1f),
                ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 0f, 1f)
        );

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(outAnimator, inAnimator);
        animatorSet.start();

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


}
