package com.example.samuyu.sometest.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.entities.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyamaosamuyu on 2014/11/16.
 */
public class AppAdapter extends ArrayAdapter<AppInfo>{
    private static final String TAG = AppAdapter.class.getSimpleName();

    public AppAdapter(Context context) {
        super(context, -1, new ArrayList<AppInfo>());
    }

    public AppAdapter(Context context, List<AppInfo> list) {
        super(context, -1, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.list_item_app, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        AppInfo appInfo = getItem(position);
        holder.bindView(appInfo);
        if ( appInfo.isForbidden ) {
            //holder.appName.setBackgroundColor(R.color.green);
            //convertView.setBackgroundColor(R.color.green);
        }

        //((ListView)parent).setItemChecked(position, appInfo.isForbidden);

        return convertView;
    }

    private static class ViewHolder{

        public ImageView appIcon;
        public TextView appName;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            appIcon = (ImageView)view.findViewById(R.id.app_icon);
            appName = (TextView)view.findViewById(R.id.app_name);
            checkBox = (CheckBox)view.findViewById(R.id.check_box);
        }

        public void bindView(AppInfo appInfo) {
            appIcon.setImageDrawable(appInfo.icon);
            appName.setText(appInfo.name);
            checkBox.setChecked(appInfo.isForbidden);

            //appName.setBackgroundColor(R.color.white);
            if (appInfo.isForbidden) {
                appName.setBackgroundColor(R.color.white);
            } else {
                appName.setBackgroundColor(R.color.green);
            }
        }

    }

}
