package com.personnalize_design.meals.ui.menu_quantity.interfaces;

import com.personnalize_design.meals.data.model.MealFacture;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.ui.base.MvpView;

import java.util.List;

public interface MenuQuantityMvpView extends MvpView {


    void onSuccessOrderSent();

    void onFaillureOrderSent();

    void onErrorOccured(int i);


    void onCheckEndOrderMeal(boolean b, String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealSelected);
}
