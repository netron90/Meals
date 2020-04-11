package com.personnalize_design.meals.data.db;

import com.personnalize_design.meals.data.model.UserOrderModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class AppDbHelper implements DbHelper {

    private MealsDatabase db;

    @Inject
    public AppDbHelper(MealsDatabase db) {
        this.db = db;
    }


    @Override
    public Completable saveUserOrder(UserOrderModel.UserOrder userOrder) {
        return db.userOrderDao().saveUserOrder(userOrder);
    }

    @Override
    public Completable saveUserListOrder(List<UserOrderModel.MealListBean> listUserOrderMeal) {
        return db.userOrderDao().saveUserListOrder(listUserOrderMeal);
    }

    @Override
    public Single<UserOrderModel.UserOrder> getUserOrderInfo() {
        return db.userOrderDao().getUserOrderInfo();
    }

    @Override
    public Flowable<List<UserOrderModel.MealListBean>> getUserListMeal(int id) {
        return db.userOrderDao().getUserListMeal(id);
    }

    @Override
    public Completable deleteAllUserOrder() {
        return db.userOrderDao().deleteAllUserOrder();
    }

    @Override
    public Completable deleteAllUserOrderMealList() {
        return db.userOrderDao().deleteAllUserOrderMealList();
    }


}
