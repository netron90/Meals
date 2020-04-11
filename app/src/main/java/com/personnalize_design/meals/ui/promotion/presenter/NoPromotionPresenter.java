package com.personnalize_design.meals.ui.promotion.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.promotion.interfaces.NoPromotionMvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NoPromotionPresenter <V extends NoPromotionMvpView> extends BasePresenter<V> implements NoPromotionMvpPresenter<V> {

    @Inject
    public NoPromotionPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
