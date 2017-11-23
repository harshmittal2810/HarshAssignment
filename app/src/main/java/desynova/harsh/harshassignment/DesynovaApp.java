package desynova.harsh.harshassignment;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.multidex.MultiDexApplication;

import desynova.harsh.harshassignment.di.DaggerMainComponent;
import desynova.harsh.harshassignment.di.MainComponent;

/**
 * Created by harshmittal on 23/11/17.
 */

public class DesynovaApp extends MultiDexApplication {
    private static Context context;
    private MainComponent mainComponent;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mainComponent = DaggerMainComponent.create();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @VisibleForTesting
    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}
