package com.dev4free.devbuyandroidclient.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/4/26.
 */
public class ActivityUtils {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){

        activities.add(activity);

    }

    public static void removeActivity(Activity activity){

        activities.remove(activity);
        activity.finish();
    }


    public static void removeAllActivity(){
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
