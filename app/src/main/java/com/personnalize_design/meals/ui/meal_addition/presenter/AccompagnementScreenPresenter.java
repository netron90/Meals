package com.personnalize_design.meals.ui.meal_addition.presenter;

import android.util.Log;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.AddOnMenuModel;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.meal_addition.interfaces.AccompagnementScreenMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccompagnementScreenPresenter <V extends AccompagnementScreenMvpView> extends BasePresenter<V> implements AccompagnementScreenMvpPresenter<V> {

    private Disposable disposable;

    private List<AddOnMenuModel.DataBean.AccompagnementBean> accompagnementList;
    @Inject
    public AccompagnementScreenPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void getAddOnMenu(String companyNameService) {
        accompagnementList = new ArrayList<>();
        getDataManager().reqAdditionMenu(companyNameService)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddOnMenuModel.DataBean.AccompagnementBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(AddOnMenuModel.DataBean.AccompagnementBean accompagnementBean) {
                        accompagnementList.add(accompagnementBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        if(disposable != null){
                            disposable.dispose();
                            disposable = null;
                        }

                        if(getView() != null){
                            if(accompagnementList == null || accompagnementList.size() == 0)
                            {
                                Log.d("ACCOMPAGNEMENT LIST", "LISTE des accompagnements false: " + accompagnementList.size());
                                getView().onAdditionMenuReceive(accompagnementList, true);
                            }else{
                                Log.d("ACCOMPAGNEMENT LIST", "LISTE des accompagnements true: " + accompagnementList.size());
                                getView().onAdditionMenuReceive(accompagnementList, false);
                            }

                        }
                    }
                });
    }
}
