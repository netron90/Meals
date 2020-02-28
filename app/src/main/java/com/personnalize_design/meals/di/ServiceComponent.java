package com.personnalize_design.meals.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {ApplicationModule.class})
public interface ServiceComponent {

//    void inject ( CheckUpdate checkUpdate );
}
