package com.personnalize_design.meals.di;

import com.personnalize_design.meals.services.CheckMealBillTimeService;
import com.personnalize_design.meals.services.CheckSuggestionTime;
import com.personnalize_design.meals.services.DeleteBillService;
import com.personnalize_design.meals.ui.catalog.CatalogActivity;
import com.personnalize_design.meals.ui.catalog.CatalogFragment;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.main.MainActivity;
import com.personnalize_design.meals.ui.day_menu.NoFactoryErrorFragment;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.day_menu.DayMenuFragment;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.meal_addition.AccompagnementScreen;
import com.personnalize_design.meals.ui.menu_quantity.MenuQuantityActivity;
import com.personnalize_design.meals.ui.promotion.NoPromotionFragment;
import com.personnalize_design.meals.ui.promotion.PromotionFragment;
import com.personnalize_design.meals.ui.search.SearchCompany;
import com.personnalize_design.meals.ui.search.SearchFragment;
import com.personnalize_design.meals.ui.security.SecurityActivity;
import com.personnalize_design.meals.ui.suggestion.SuggestionFragment;
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
    void inject (SecurityActivity securityActivity);
    void inject (SuggestionFragment suggestionFragment);
    void inject (CatalogFragment catalogFragment);
    void inject (CatalogActivity catalogActivity);
    void inject (PromotionFragment promotionFragment);
    void inject (NoPromotionFragment noPromotionFragment);


}
