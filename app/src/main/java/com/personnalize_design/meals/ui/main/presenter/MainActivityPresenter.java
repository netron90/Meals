package com.personnalize_design.meals.ui.main.presenter;

import android.content.Context;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.main.interfaces.MainActivityMvpView;


import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter<V extends MainActivityMvpView> extends BasePresenter<V> implements MainActivityMvpPresenter<V> {


    private Disposable disposable;
    private Context context;

    @Inject
    public MainActivityPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
