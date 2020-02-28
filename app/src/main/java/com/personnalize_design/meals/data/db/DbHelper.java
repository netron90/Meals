package com.personnalize_design.meals.data.db;

import com.personnalize_design.meals.data.model.UserOrderModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DbHelper {

    Completable saveUserOrder(UserOrderModel.UserOrder userOrder);
    Completable saveUserListOrder(List<UserOrderModel.MealListBean> listUserOrderMeal);
    Single<UserOrderModel.UserOrder> getUserOrderInfo();
    Flowable<List<UserOrderModel.MealListBean>> getUserListMeal(int id);
    Completable deleteAllUserOrder();
    Completable deleteAllUserOrderMealList();
}
