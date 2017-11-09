package com.wallmart.busroute;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class ApplicationClassContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        configStetho();
    }

    private void configStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
