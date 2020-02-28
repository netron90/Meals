package com.personnalize_design.meals.ui.menu_quantity.presenter;

import android.util.Log;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.BillClientInformation;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.menu_quantity.interfaces.MenuQuantityMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MenuQuantityPresenter <V extends MenuQuantityMvpView> extends BasePresenter<V> implements MenuQuantityMvpPresenter<V> {

    private Disposable disposable;

    @Inject
    public MenuQuantityPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void getTotalMealPrice(String totalPrice) {
//        int priceString = 0;
//        mealTotalPrice = 0;
//        for(int i = 0; i < listMealSelected.size(); i++){
//            mealTotalPrice = mealTotalPrice + Integer.valueOf(listMealSelected.get(i).getMainMealPrice().split("f")[0]);
//           // mealTotalPrice = mealTotalPrice + priceString;
//            Log.d("TOTAL MEALS PRICE", "PRICES MEALS: " + mealTotalPrice);
//        }
//        if(getView() != null){
//            getView().onSetMealsTotalPrice(String.valueOf(totalPrice));
//        }
    }

    public void sendUserOrder(BillClientInformation billClientInformation) {
        getDataManager().reqSendUserOrderToCompany(billClientInformation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserOrderModel>() {
                    @Override
                    public void accept(UserOrderModel serverResponse) throws Exception {
                        if(serverResponse.getMessage().equals("user order success")){

                            //TODO: SAVE FIRST GLOBAL DATA INTO DB
                            List<UserOrderModel.MealListBean> temp;
                            UserOrderModel.UserOrder userOrder = new UserOrderModel.UserOrder();
                            userOrder.setCompanyName(serverResponse.getCompanyName());
                            userOrder.setTodayDate(serverResponse.getTodayDate());
                            userOrder.setCompanyContact(serverResponse.getCompanyContact());
                            userOrder.setCompanyLocalisation(serverResponse.getCompanyLocalisation());
                            temp = serverResponse.getMealList();
                            getDataManager().saveUserOrder(userOrder)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(Schedulers.io())
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            disposable = d;
                                        }

                                        @Override
                                        public void onComplete() {
                                            if(disposable != null){
                                                disposable.dispose();
                                                disposable = null;
                                            }
                                            //TODO: SAVE LIST MEAL
                                            getDataManager().getUserOrderInfo()
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(new Consumer<UserOrderModel.UserOrder>() {
                                                        @Override
                                                        public void accept(UserOrderModel.UserOrder userOrder) throws Exception {
                                                            if(userOrder != null){
                                                                List<UserOrderModel.MealListBean> listBean = new ArrayList<>();
                                                                listBean = serverResponse.getMealList();
                                                                for(int i = 0; i < temp.size(); i++){
                                                                    listBean.get(i).setIdList(userOrder.getId());
                                                                }
                                                                getDataManager().saveUserListOrder(listBean).subscribeOn(Schedulers.io())
                                                                        .observeOn(AndroidSchedulers.mainThread())
                                                                        .subscribe(new CompletableObserver() {
                                                                            @Override
                                                                            public void onSubscribe(Disposable d) {
                                                                                disposable = d;
                                                                            }

                                                                            @Override
                                                                            public void onComplete() {
                                                                                if(disposable != null){
                                                                                    disposable.dispose();
                                                                                    disposable = null;
                                                                                }
                                                                                if(getView() != null){
                                                                                    getView().onSuccessOrderSent();
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onError(Throwable e) {
                                                                                e.printStackTrace();
                                                                                if(getView() != null){
                                                                                    getView().onErrorOccured(-1);
                                                                                }
                                                                            }
                                                                        });
                                                            }
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            e.printStackTrace();
                                            if(getView() != null){
                                                Log.d("USER COMMAND SCREEN", "Send user to command Screen");
                                                getView().onFaillureOrderSent();
                                            }
                                        }
                                    });
                        }else{
                            if(getView() != null){
                                Log.d("USER COMMAND SCREEN", "Send user to command Screen");
                                getView().onFaillureOrderSent();
                            }
                        }
//                            if(getView() != null){
//                                Log.d("USER COMMAND SCREEN", "Send user to command Screen");
//                                getView().onSuccessOrderSent();
//                            }
//                        }else{
//                            if(getView() != null){
//                                Log.d("USER COMMAND SCREEN", "Send user to command Screen");
//                                getView().onFaillureOrderSent();
//                            }
//                        }
                    }
                }, throwable -> throwable.printStackTrace());
    }

}
