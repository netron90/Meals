package com.personnalize_design.meals.ui.menu_quantity.interfaces;

import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.ui.base.MvpView;

public interface MenuQuantityMvpView extends MvpView {
    void onSetMealsTotalPrice(String priceString);

    void onSuccessOrderSent();

    void onFaillureOrderSent();

    void onErrorOccured(int i);
}
