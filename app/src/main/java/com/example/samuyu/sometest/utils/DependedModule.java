package com.example.samuyu.sometest.utils;

import android.content.Context;

import com.example.samuyu.sometest.activities.MyActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by toyamaosamuyu on 2014/11/20.
 */
//@Module(injects = Hoge.class)
//@Module(injects = MyActivity.class)
public class DependedModule {
    private static final String TAG = DependedModule.class.getSimpleName();

    private Context mContext;

    /*
    @Provides
    Depended provideDepended() {
        return new DependedImpl(100);
    }
    */
}
