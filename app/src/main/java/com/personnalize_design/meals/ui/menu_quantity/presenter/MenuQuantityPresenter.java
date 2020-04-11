package com.personnalize_design.meals.ui.menu_quantity.presenter;

import android.util.Log;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.BillClientInformation;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.MealFacture;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.menu_quantity.interfaces.MenuQuantityMvpView;

import java.util.ArrayList;
import java.util.Calendar;
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

    private Disposable disposable, disposable2;

    @Inject
    public MenuQuantityPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
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
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.HOUR, 5);
                            List<UserOrderModel.MealListBean> temp;
                            UserOrderModel.UserOrder userOrder = new UserOrderModel.UserOrder();
                            userOrder.setCompanyName(serverResponse.getCompanyName());
                            userOrder.setTodayDate(serverResponse.getTodayDate());
                            userOrder.setCompanyContact(serverResponse.getCompanyContact());
                            userOrder.setCompanyLocalisation(serverResponse.getCompanyLocalisation());
                            userOrder.setCurrentMealOrderTime(calendar.getTime());
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
                                                                    Log.d("ID SETTING", "Id Set: " + userOrder.getId());
                                                                    listBean.get(i).setIdList(userOrder.getId());
                                                                }
                                                                getDataManager().saveUserListOrder(listBean).subscribeOn(Schedulers.io())
                                                                        .observeOn(AndroidSchedulers.mainThread())
                                                                        .subscribe(new CompletableObserver() {
                                                                            @Override
                                                                            public void onSubscribe(Disposable d) {
                                                                                disposable2 = d;
                                                                            }

                                                                            @Override
                                                                            public void onComplete() {
                                                                                if(disposable2 != null){
                                                                                    disposable2.dispose();
                                                                                    disposable2 = null;
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


    public void checkEndHourMealOrder(String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealSelected) {
        getDataManager().reqEndHourMealsOrder(companyName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ServerResponse>() {
                    @Override
                    public void accept(ServerResponse serverResponse) throws Exception {
                        if(serverResponse.getMessage().equals("Vous pouvez passer une commande")){
                            if(getView() != null){
                                Log.d("END ORDER MEAL", "End Order meal true. You can pass order");
                                getView().onCheckEndOrderMeal(true, totalMealsPrices, companyName, clientName, clientContact, clientLocalisation, listMealSelected);
                            }
                        }else{
                            if(getView() != null){
                                Log.d("END ORDER MEAL", "End Order meal false. You can not pass order");
                                getView().onCheckEndOrderMeal(false, totalMealsPrices, companyName, clientName, clientContact, clientLocalisation, listMealSelected);
                            }
                        }

                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if(getView() != null){
                        Log.d("USER COMMAND SCREEN", "Send user to command Screen");
                        getView().onFaillureOrderSent();
                    }
                });
    }
}
