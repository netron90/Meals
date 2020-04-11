package com.personnalize_design.meals.ui.catalog.presenters;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.catalog.interfaces.CatalogMvpView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CatalogPresenter <V extends CatalogMvpView> extends BasePresenter<V> implements CatalogMvpPresenter<V> {

    private Disposable disposable;

    @Inject
    public CatalogPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void getCompanyCatalog(String companyUserName) {
        getDataManager().reqCompanyCatalog(companyUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyCatalog>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CompanyCatalog companyCatalog) {

                        if(companyCatalog.getMessage().equals("no user found")){
                            if(getView() != null){
                                getView().OnNoUserFound();
                            }
                        }else{
                            if(getView() != null){
                                getView().OnCatalogFound(companyCatalog.getUserData());
                            }
                        }
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

                    }
                });
    }
}
