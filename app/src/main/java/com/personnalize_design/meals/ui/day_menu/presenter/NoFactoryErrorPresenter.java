package com.personnalize_design.meals.ui.day_menu.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.day_menu.interfaces.NoFactoryErrorMvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NoFactoryErrorPresenter <V extends NoFactoryErrorMvpView> extends BasePresenter<V> implements NoFactoryErrorMvpPresenter<V> {

    @Inject
    public NoFactoryErrorPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
