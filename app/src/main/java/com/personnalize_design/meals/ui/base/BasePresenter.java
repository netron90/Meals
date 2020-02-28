package com.personnalize_design.meals.ui.base;

import com.personnalize_design.meals.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;
    private Disposable subscription = null;


    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable)
    {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public void onAttachView(V view) {
        if(view != null)
        {
            this.view = view;
        }

    }

    public V getView() {
        return view;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

}
