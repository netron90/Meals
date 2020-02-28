package com.personnalize_design.meals.ui.day_menu.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.day_menu.interfaces.MainScreenMvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainScreenPresenter<V extends MainScreenMvpView> extends BasePresenter<V> implements MainScreenMvpPresenter<V> {

    @Inject
    public MainScreenPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
