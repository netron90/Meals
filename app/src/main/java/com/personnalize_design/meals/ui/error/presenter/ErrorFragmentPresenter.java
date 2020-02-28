package com.personnalize_design.meals.ui.error.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.error.interfaces.ErrorMvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ErrorFragmentPresenter <V extends ErrorMvpView> extends BasePresenter<V> implements ErrorMvpPresenter<V> {

    @Inject
    public ErrorFragmentPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
