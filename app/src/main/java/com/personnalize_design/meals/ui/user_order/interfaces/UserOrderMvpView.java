package com.personnalize_design.meals.ui.user_order.interfaces;

import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.ui.base.MvpView;

import java.util.List;

public interface UserOrderMvpView extends MvpView {
    void OnNoUserOrder();

    void onUserOrderInfoSucces(UserOrderModel.UserOrder userOrder);

    void onGetListMealOrderSuccess(List<UserOrderModel.MealListBean> mealListBeans, UserOrderModel.UserOrder userOrder);
}
