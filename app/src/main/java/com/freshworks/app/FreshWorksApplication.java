package com.freshworks.app;

import android.app.Application;
import android.view.WindowManager;

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
