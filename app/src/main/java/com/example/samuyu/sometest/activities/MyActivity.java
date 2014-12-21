package com.example.samuyu.sometest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.utils.Depended;
import com.example.samuyu.sometest.utils.DependedModule;


import butterknife.ButterKnife;
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
