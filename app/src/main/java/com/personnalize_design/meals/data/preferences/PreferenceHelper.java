package com.personnalize_design.meals.data.preferences;

public interface PreferenceHelper {

    void setFragmentStateToShow(int fragmentState);
    int getFragmentStateToShow();

    void  setDeliveryOrderState(boolean deliveryOrderState);
    boolean isDeliveryStateEnable();

    void setSearchFragmentEnable(boolean state);
    boolean isSearchFragmentEnable();

}
