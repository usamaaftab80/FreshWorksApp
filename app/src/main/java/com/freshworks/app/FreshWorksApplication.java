package com.freshworks.app;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.WindowManager;

import com.freshworks.app.data.Constant;

/**
 * Created by Usama Aftab on 2018-01-25.
 */

public class FreshWorksApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Put stuff that you want to do at the application startup.

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // How to handle low memory situation. Kill Quartz job, if any.
    }
}
