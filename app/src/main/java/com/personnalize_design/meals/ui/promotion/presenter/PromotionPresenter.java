package com.personnalize_design.meals.ui.promotion.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.CompanyPromotion;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.promotion.interfaces.PromotionMvpView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PromotionPresenter <V extends PromotionMvpView> extends BasePresenter<V> implements PromotionMvpPresenter<V> {

    private Disposable disposable;

    @Inject
    public PromotionPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void checkPromotion(String copanyUserName) {
        getDataManager().reqCompanyPromotion(copanyUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyPromotion>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CompanyPromotion companyPromotion) {
                        if(companyPromotion.getMessage().equals("request success")){
                            if(getView() != null){
                                getView().OnGetCompanyPromotion(companyPromotion);
                            }
                        }else if(companyPromotion.getMessage().equals("company are not allowed to use this options")){
                            if(getView() != null){
                                getView().OnGetNoCompanyPromotion();
                            }
                        }else{}

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(disposable != null){
                            disposable.dispose();
                            disposable = null;
                        }
                        if(getView() != null){
                            getView().OnErrorOccured();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(disposable != null){
                            disposable.dispose();
                            disposable = null;
                        }
                    }
                });

    }
}
