package com.personnalize_design.meals.data.preferences;

public interface PreferenceHelper {

    void setFragmentStateToShow(int fragmentState);
    int getFragmentStateToShow();

    void  setDeliveryOrderState(boolean deliveryOrderState);
    boolean isDeliveryStateEnable();

    void setSearchFragmentEnable(boolean state);
    boolean isSearchFragmentEnable();

    void setMealOrderExist(boolean mealOrderExist);
    boolean isMealOrderExist();

    void setDeleteListUserMeals(boolean state);
    boolean isDeleteListUserMeals();

    void setServiceCheckOrderTime(boolean valuOrderTime);
    boolean isServiceCheckOrderTime();

    void setCompanyUserName(String companyUserName);
    String getCopanyUserName();

    void setCompanyPhoneNumber(String companyPhoneNumber);
    String getCopanyPhoneNumber();

    void setSendSuggestion(boolean suggestionValue);
    boolean isSendSuggestionSet();

    void setUserAccessCode(String accessCode);
    String getUserAccessCode();

//    void setDeleteUserData(boolean state);
//    boolean isDeleteUserData();
}
