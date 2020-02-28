package com.personnalize_design.meals.ui.base;

import android.content.Context;

public interface MvpPresenter<V extends MvpView> {

    void onAttachView(V mvpView);
}
