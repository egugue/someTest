package com.example.samuyu.sometest.utils;

/**
 * Created by toyamaosamuyu on 2014/11/20.
 */
public class DependedImpl implements Depended{
    private static final String TAG = DependedImpl.class.getSimpleName();

    private int value;

    public DependedImpl(int i) {
       value = i;
    }

    @Override
    public int get() {
        return value;
    }

    @Override
    public void set(int i) {
        value = i;
    }
}
