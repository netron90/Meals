package com.personnalize_design.meals.di;

import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.main.MainActivity;
import com.personnalize_design.meals.ui.day_menu.NoFactoryErrorFragment;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.day_menu.DayMenuFragment;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.meal_addition.AccompagnementScreen;
import com.personnalize_design.meals.ui.menu_quantity.MenuQuantityActivity;
import com.personnalize_design.meals.ui.search.SearchCompany;
import com.personnalize_design.meals.ui.search.SearchFragment;
import com.personnalize_design.meals.ui.user_order.NoOrderFragment;
import com.personnalize_design.meals.ui.user_order.UserOrderFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject( BaseActivity baseActivity);
    void inject( MainActivity mainActivity );
    void inject( MainScreenActivity mainScreenActivity );
    void inject(DayMenuFragment dayMenuFragment);
    void inject(NoFactoryErrorFragment noFactoryErrorFragment);
    void inject (AccompagnementScreen accompagnementScreen);
    void inject (MenuQuantityActivity menuQuantityActivity);
    void inject (UserOrderFragment userOrderFragment);
    void inject (ErrorFragment errorFragment);
    void inject (NoOrderFragment noOrderFragment);
    void injext (SearchFragment searchFragment);
    void inject (SearchCompany searchCompany);

}
