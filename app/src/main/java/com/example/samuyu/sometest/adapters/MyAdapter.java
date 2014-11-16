package com.example.samuyu.sometest.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuyu.sometest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyamaosamuyu on 2014/11/01.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private static final String TAG = MyAdapter.class.getSimpleName();
    private ArrayList<String> mDataset;

    public MyAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.layout_each_recycle_view, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(i+" : "+mDataset.get(i));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addToList(String text, int position) {
        mDataset.add(position, text);
        notifyItemInserted(position);
    }

    public void removeFromList(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.each_text);
            iv = (ImageView)itemView.findViewById(R.id.each_image);
        }
    }
}
