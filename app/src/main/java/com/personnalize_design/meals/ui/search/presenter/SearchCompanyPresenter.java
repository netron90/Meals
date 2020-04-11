package com.personnalize_design.meals.ui.search.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.search.interfaces.SearchCompanyMvpView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchCompanyPresenter<V extends SearchCompanyMvpView> extends BasePresenter<V> implements SearchCompanyMvpPresenter<V> {
    @Inject
    public SearchCompanyPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void getOneCompanySearch(String query) {
        getDataManager().reqOneCompanySearch(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneCompanySearchModel>() {
                    @Override
                    public void accept(OneCompanySearchModel oneCompanySearchModel) throws Exception {
                        if(oneCompanySearchModel.getMessage().equals("aucun resultat")){
                            if(getView() != null){
                                getView().onNoCompanyFound();
                            }
                        }else{
                            if(getView() != null){
                                getView().onFoundOneCompanySuccess(oneCompanySearchModel.getData());
                            }
                        }

                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if(getView() != null){
                        getView().onErrorOccured();
                    }
                });
    }
}

