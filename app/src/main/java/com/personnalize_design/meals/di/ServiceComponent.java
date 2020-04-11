package com.personnalize_design.meals.di;

import com.personnalize_design.meals.services.CheckMealBillTimeService;
import com.personnalize_design.meals.services.CheckSuggestionTime;
import com.personnalize_design.meals.services.DeleteBillService;
import com.personnalize_design.meals.services.NotifyUserService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {ApplicationModule.class})
public interface ServiceComponent {

//    void inject ( CheckUpdate checkUpdate );

    void inject (CheckMealBillTimeService checkMealBillTimeService);
    void inject (DeleteBillService deleteBillService);
    void inject (NotifyUserService notifyUserService);
    void inject (CheckSuggestionTime suggestionTime);
}
