package com.example.samuyu.sometest.utils;

import android.app.Application;

import java.util.Arrays;
import java.util.List;


/**
 * Created by toyamaosamuyu on 2014/11/20.
 */
public class DaggerApplication extends Application{
    private static final String TAG = DaggerApplication.class.getSimpleName();
//    private ObjectGraph graph;

    @Override public void onCreate() {
        super.onCreate();

 //       graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<DependedModule> getModules() {
        return Arrays.asList(
                new DependedModule()
        );
    }

    public void inject(Object object) {
  //      graph.inject(object);
    }
}
