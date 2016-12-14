package ghn.shopandgo;

import android.app.Application;

/**
 * Created by bukbukbukh on 11/11/16.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
