package com.personnalize_design.meals.ui.search.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.search.interfaces.SearchFragmentMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchFragmentPresenter <V extends SearchFragmentMvpView> extends BasePresenter<V> implements SearchFragmentMvpPresenter<V> {

    private Disposable disposable;

    List<AllCompanySearch.CompanyData> list;

    @Inject
    public SearchFragmentPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void getAllCompanyList() {
        list = new ArrayList<>();
        getDataManager().reqAllCompanySearch()
                .concatMap(new Function<AllCompanySearch, ObservableSource<AllCompanySearch.CompanyData>>() {
                    @Override
                    public ObservableSource<AllCompanySearch.CompanyData> apply(AllCompanySearch allCompanySearch) throws Exception {
                        return Observable.fromIterable(allCompanySearch.getData());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllCompanySearch.CompanyData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(AllCompanySearch.CompanyData companyData) {
                        list.add(companyData);
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
                        if(disposable != null){
                            disposable.dispose();
                            disposable = null;
                        }
                        if(getView() != null){
                            getView().onGetAllCOmpanySearch(list);
                        }
                    }
                });

    }


}
