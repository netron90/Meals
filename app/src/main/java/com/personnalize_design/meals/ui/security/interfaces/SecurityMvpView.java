package com.personnalize_design.meals.ui.security.interfaces;

import com.personnalize_design.meals.ui.base.MvpView;

public interface SecurityMvpView extends MvpView {
    void OnErrorAccessCodeValidation();

    void OnSuccessAccessCodeValidation();

    void OnAccessCodeValidationIncorrect();

    void OnSubscriptionEnd();
}
