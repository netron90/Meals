package com.personnalize_design.meals.di;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    private ApplicationComponent mComponent;
    private ServiceComponent mServiceComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        this.mServiceComponent = DaggerServiceComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();


    }

    public ApplicationComponent getmComponent(){
        return this.mComponent;
    }

    public ServiceComponent getServiceComponent(){
        return this.mServiceComponent;
    }


}
