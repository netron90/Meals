package com.personnalize_design.meals.ui.user_order.presenter;

import android.util.Log;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.user_order.interfaces.UserOrderMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UserOrderPresenter <V extends UserOrderMvpView> extends BasePresenter<V> implements UserOrderMvpPresenter<V> {

    @Inject
    public UserOrderPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void getUserMealOrderFromDb() {
        getDataManager().getUserOrderInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserOrderModel.UserOrder>() {
                    @Override
                    public void accept(UserOrderModel.UserOrder userOrder) throws Exception {
                        if(userOrder != null){
                            if(getView() != null){
                                Log.d("MEAL TIME", "L'heure de suppression: " + userOrder.getCurrentMealOrderTime());
                                getView().onUserOrderInfoSucces(userOrder);
                            }
                        }else {
                            if(getView() != null){
                                getView().OnNoUserOrder();
                            }
                        }
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                   if(throwable.getMessage().equals("Query returned empty result set: SELECT * FROM user_order")){
                       if(getView() != null){
                           getView().OnNoUserOrder();
                       }
                   }

                });
    }

    public void getMealOrderList(UserOrderModel.UserOrder userOrder) {
        getDataManager().getUserListMeal(userOrder.id)
               .map(new Function<List<UserOrderModel.MealListBean>, List<UserOrderModel.MealListBean>>() {
                   @Override
                   public List<UserOrderModel.MealListBean> apply(List<UserOrderModel.MealListBean> mealListBeans) throws Exception {
                       List<UserOrderModel.MealListBean> temp = new ArrayList<>();
                       for(UserOrderModel.MealListBean e : mealListBeans){
                           temp.add(e);
                       }
                       return temp;
                   }
               })
                .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<UserOrderModel.MealListBean>>() {
            @Override
            public void accept(List<UserOrderModel.MealListBean> mealListBeans) throws Exception {
                if(getView() != null){
                    getView().onGetListMealOrderSuccess(mealListBeans, userOrder);
                }
            }
        }, throwable -> throwable.printStackTrace());
    }
}
