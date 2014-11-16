package com.example.samuyu.sometest.entities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by toyamaosamuyu on 2014/11/16.
 */
public class AppInfo {
    private static final String TAG = AppInfo.class.getSimpleName();

    public String name;
    public String packageName;
    public String className;
    public Drawable icon;
    public boolean isForbidden;

    public static List<AppInfo> readAppInfo(Context context) {

        PackageManager manager = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);

        Collections.sort(apps, new ResolveInfo.DisplayNameComparator(manager));

        List<AppInfo> appInfoList = new ArrayList<AppInfo>();
        if (apps == null) {
            return appInfoList;
        }

        for (ResolveInfo resolveInfo : apps) {

            AppInfo appInfo = new AppInfo();
            appInfo.name = (String)resolveInfo.loadLabel(manager);
            appInfo.packageName = resolveInfo.activityInfo.applicationInfo.packageName;
            appInfo.className = resolveInfo.activityInfo.name;
            appInfo.icon = resolveInfo.loadIcon(manager);
            appInfo.isForbidden = false;

            appInfoList.add(appInfo);
        }

        return appInfoList;
    }


}
