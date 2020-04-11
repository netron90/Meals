package com.personnalize_design.meals.ui.day_menu.presenter;

import android.util.Log;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.AllCompanyModel;
import com.personnalize_design.meals.data.model.OtherDayMenuModel;
import com.personnalize_design.meals.data.model.TodayDateModel;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.day_menu.interfaces.DayMenuMvpView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class DayMenuPresenter <V extends DayMenuMvpView> extends BasePresenter<V> implements DayMenuMvpPresenter<V> {

    private Disposable disposable, disposable2, disposable3;
    List<AllCompanyModel.DataBean> allCompanyList;
    List<OtherDayMenuModel.DataBean.OtherMenuBean> otherDayMenuList;
    @Inject
    public DayMenuPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void menuDayRequest() {

        if(allCompanyList != null){
            allCompanyList.clear();
        }else{
            allCompanyList = new ArrayList<>();
        }

        //Today Date request
        getDataManager().reqGetTodayDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TodayDateModel>() {
                    @Override
                    public void accept(TodayDateModel todayDateModel) throws Exception {

                        Log.d("DATE GET SERVER", "Date get from server: " + todayDateModel);
                        Timber.d("Date From Server: " + todayDateModel);
                        if(getView() != null)
                        {
                            Log.d("GET VIEW METHOD", "Get view Method: " + getView());
                            getView().setTodayDateMenu(todayDateModel);
                        }
                    }
                }, throwable -> throwable.printStackTrace());

        //All COmpany Request
        getDataManager().reqGetAllCompany()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllCompanyModel.DataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(AllCompanyModel.DataBean dataBean) {
                        allCompanyList.add(dataBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(getView() != null){
                            getView().onErrorOccured();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(disposable != null)
                        {
                            disposable.dispose();
                            disposable = null;
                        }

                        if(allCompanyList.size() == 0 || allCompanyList.isEmpty() ){
                            if(getView() != null){
                                getView().getAllCompanyData(allCompanyList, 0);
                            }
                        }else if(allCompanyList.size() == 1){
                            if(getView() != null){
                                getView().getAllCompanyData(allCompanyList, 1);
                            }
                        }else{
                            if(getView() != null){
                                getView().getAllCompanyData(allCompanyList, -1);
                            }
                        }


                    }
                });

    }

    public void getOtherMenuCompany(String username, String companyCoverImage, String deliveryPrice) {

        otherDayMenuList = new ArrayList<>();

        getDataManager().reqCompanyOtherMenu(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OtherDayMenuModel.DataBean.OtherMenuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable2 = d;
                    }

                    @Override
                    public void onNext(OtherDayMenuModel.DataBean.OtherMenuBean otherMenuBean) {
                        Log.d("OTHER MENU", "Other Menu Array: " +otherMenuBean);
                        otherDayMenuList.add(otherMenuBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(getView() != null){
                            getView().onErrorOccured();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(disposable2 != null)
                        {
                            disposable2.dispose();
                            disposable2 = null;
                        }


                        if(otherDayMenuList.size() == 0 ){
                            if(getView() != null){
                                getView().getAllOtherMenuCompany(otherDayMenuList, companyCoverImage, true, deliveryPrice);
                            }
                        }else{
                            if(getView() != null){
                                getView().getAllOtherMenuCompany(otherDayMenuList, companyCoverImage, false, deliveryPrice);
                            }
                        }
                    }
                });
    }

    public void getOneCompany(String companyName) {

        if(allCompanyList != null)
        {
            allCompanyList.clear();
        }else{
            allCompanyList = new ArrayList<>();
        }

        getDataManager().reqGetTodayDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TodayDateModel>() {
                    @Override
                    public void accept(TodayDateModel todayDateModel) throws Exception {

                        Log.d("DATE GET SERVER", "Date get from server: " + todayDateModel);
                        if(getView() != null)
                        {
                            getView().setTodayDateMenu(todayDateModel);
                        }
                    }
                }, throwable -> throwable.printStackTrace());


        getDataManager().reqOneCompany(companyName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllCompanyModel.DataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable3 = d;
                    }

                    @Override
                    public void onNext(AllCompanyModel.DataBean dataBean) {
                        allCompanyList.add(dataBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(getView() != null){
                            getView().onErrorOccured();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(disposable3 != null)
                        {
                        disposable3.dispose();
                        disposable3 = null;
                        }
                        if(getView() != null){
                        getView().getAllCompanyData(allCompanyList, 1);
                        }
                    }
                });
//                .concatMap(new Function<AllCompanyModel, ObservableSource<AllCompanyModel.DataBean>>() {
//                    @Override
//                    public ObservableSource<AllCompanyModel.DataBean> apply(AllCompanyModel allCompanyModel) throws Exception {
//                        return Observable.fromIterable(allCompanyModel.getData());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AllCompanyModel.DataBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable3 = d;
//                    }
//
//                    @Override
//                    public void onNext(AllCompanyModel.DataBean dataBean) {
//                        allCompanyList.add(dataBean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        if(getView() != null){
//                            getView().onErrorOccured();
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if(disposable3 != null)
//                        {
//                            disposable3.dispose();
//                            disposable3 = null;
//                        }
//                        if(getView() != null){
//                            getView().getAllCompanyData(allCompanyList, 1);
//                        }
//                    }
//                });


    }
}



//.subscribe(new Observer<AllCompanyModel.DataBean>() {
//@Override
//public void onSubscribe(Disposable d) {
//        disposable3 = d;
//        }
//
//@Override
//public void onNext(AllCompanyModel.DataBean dataBean) {
//
//        allCompanyList.add(dataBean);
//        }
//
//@Override
//public void onError(Throwable e) {
//        e.printStackTrace();
//        }
//
//@Override
//public void onComplete() {
//        if(disposable3 != null)
//        {
//        disposable3.dispose();
//        disposable3 = null;
//        }
//        if(getView() != null){
//        getView().getAllCompanyData(allCompanyList, 1);
//        }
//        }
//        });
