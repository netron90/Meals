package com.personnalize_design.meals.ui.user_order.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.user_order.interfaces.NoOrderMvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NoOrderPresenter<V extends NoOrderMvpView> extends BasePresenter<V> implements NoOrderMvpPresenter<V> {

    @Inject
    public NoOrderPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
