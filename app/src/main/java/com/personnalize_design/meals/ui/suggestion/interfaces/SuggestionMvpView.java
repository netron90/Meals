package com.personnalize_design.meals.ui.suggestion.interfaces;

import com.personnalize_design.meals.ui.base.MvpView;

public interface SuggestionMvpView extends MvpView {
    void OnErrorAccessCodeValidation();

    //void OnSuggestionNotEnable();

    //void OnSuggestionEnable(String planName);

    void onErrorOccured();

    void SuggestionSendSuccessfully();

    void isNotTimeSuggestion();

    void isTimeSuggestion();
}
