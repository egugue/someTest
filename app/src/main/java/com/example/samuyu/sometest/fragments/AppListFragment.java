package com.example.samuyu.sometest.fragments;



import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuyu.sometest.R;
import com.example.samuyu.sometest.adapters.AppAdapter;
import com.example.samuyu.sometest.entities.AppInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AppListFragment extends ListFragment {

    private AppAdapter mAdapter;
    private ListView mAppListView;
    private Context mContext;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);

        mAdapter = new AppAdapter(mContext);
        setListAdapter(mAdapter);

        List<AppInfo> appInfoList = AppInfo.readAppInfo(mContext);
        mAdapter.addAll(appInfoList);

        Button button =(Button)view.findViewById(R.id.sort_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.sort(new MyComparator());
            }
        });

        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        AppInfo appInfo = mAdapter.getItem(position);
        appInfo.isForbidden = !appInfo.isForbidden;

        /*
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.app_list_item);

        if (appInfo.isForbidden) {
            layout.setBackgroundColor(R.color.green);
        } else {
            layout.setBackgroundColor(R.color.white);
        }
        */
        TextView appName = (TextView)v.findViewById(R.id.app_name);
        appName.setBackgroundColor(R.color.white);
        /*
        if ( appInfo.isForbidden) {
            appName.setBackgroundColor(R.color.white);
        } else {
            appName.setBackgroundColor(R.color.green);
        }
        */

        CheckBox checkBox = (CheckBox)v.findViewById(R.id.check_box);
        checkBox.setChecked(appInfo.isForbidden);

        //getListView().setItemChecked(position, appInfo.isForbidden);

        List<AppInfo> selectedList = getChoicedAppList();
        String a = "";
        for (AppInfo appInfo1 : selectedList) {
            a += appInfo1.name+"  ";
        }
        Toast.makeText(mContext, a, Toast.LENGTH_SHORT).show();
        Log.d("HOGE", a);

    }

    private List<AppInfo> getChoicedAppList() {

        int size = mAdapter.getCount();

        List<AppInfo> selectedItemList = new ArrayList<AppInfo>();

        for (int i = 0; i < size; i++) {
            AppInfo appInfo = (AppInfo)mAdapter.getItem(i);

            if (appInfo.isForbidden) {
                selectedItemList.add(appInfo);
            }
        }

        return selectedItemList;
    }


    private static class MyComparator implements Comparator<AppInfo> {

        @Override
        public int compare(AppInfo i1, AppInfo i2){

            String s1 = i1.name;
            String s2 = i2.name;

            if(s1.equals("First")==true) return -1;
            if(s2.equals("First")==true) return 1;

            if(s1.length()==0) return 1;
            if(s2.length()==0) return -1;

            if(s1.charAt(s1.length()-1)=='/'
                    && s2.charAt(s2.length()-1)!='/') return -1;
            if(s1.charAt(s1.length()-1)!='/'
                    && s2.charAt(s2.length()-1)=='/') return 1;

            return s1.compareTo(s2);
        }
    }

}
