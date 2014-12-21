package com.example.samuyu.sometest.utils;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by toyamaosamuyu on 2014/11/24.
 */
public abstract class MoreLoadListener implements AbsListView.OnScrollListener{

    private static final String TAG = MoreLoadListener.class.getSimpleName();
    private ListView mListView;
    private View mFooterView;
    private boolean isFinish = false;

    public abstract void onLoadMore();


    public MoreLoadListener(ListView listView, View footerView) {
        mListView = listView;
        mFooterView = footerView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) { }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (isEndOfList() && !isFinish) {
            onLoadMore();
        }
    }

    private boolean isEndOfList() {

        if (mListView.getAdapter() == null || mListView.getChildCount() == 0) {
            return false;
        }

        final int totalItemCount = mListView.getCount() - 1;
        final int lastItemBottomPosition = mListView.getChildAt( mListView.getChildCount()-1 ).getBottom();

        Log.d("HOGE", "----------");
        Log.d("HOGE", mListView.getLastVisiblePosition()+ "  "+ totalItemCount);
        Log.d("HOGE", lastItemBottomPosition + "  "+ mListView.getHeight() );
        Log.d("HOGE", "----------");

        return (mListView.getLastVisiblePosition() == totalItemCount)
                && (lastItemBottomPosition <= mListView.getHeight());
    }

    public void finish() {
        isFinish = true;

        //if (mListView.getFooterViewsCount() > 0 || mFooterView != null) {
        if (mFooterView != null) {
            mListView.removeFooterView(mFooterView);
        }
    }
}


