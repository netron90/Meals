package com.personnalize_design.meals.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import static com.personnalize_design.meals.constants.Mutils.COMPANY_PHONE;
import static com.personnalize_design.meals.constants.Mutils.COMPANY_USERNAME;
import static com.personnalize_design.meals.constants.Mutils.DELETE_ALL_USER_DATA;
import static com.personnalize_design.meals.constants.Mutils.DELETE_LIST_USER_MEAL;
import static com.personnalize_design.meals.constants.Mutils.FRAGMENT_STATE_SHOW;
import static com.personnalize_design.meals.constants.Mutils.MEAL_ORDER_EXIST;
import static com.personnalize_design.meals.constants.Mutils.SEARCH_STATE;
import static com.personnalize_design.meals.constants.Mutils.SERVICE_CHECK_ORDER_TIME;
import static com.personnalize_design.meals.constants.Mutils.SUGGESTION_SEND;
import static com.personnalize_design.meals.constants.Mutils.USER_ACCESS_CODE;
import static com.personnalize_design.meals.constants.Mutils.USER_DELIVERY_STATE;
import static com.personnalize_design.meals.constants.Mutils.USER_ORDER_FROM_SERVER;


public class AppPreferenceHelper implements PreferenceHelper {

    private SharedPreferences mPref;
    private Context context;
    private String fileName;

    @Inject
    public AppPreferenceHelper(Context context){
        mPref = context.getSharedPreferences("meals_pref", Context.MODE_PRIVATE);
        Log.d("PREF VALUE", "mPref Value: " + mPref);
    }


    @Override
    public void setFragmentStateToShow(int fragmentState) {
        this.mPref.edit().putInt(FRAGMENT_STATE_SHOW, fragmentState).apply();
    }

    @Override
    public int getFragmentStateToShow() {
        return this.mPref.getInt(FRAGMENT_STATE_SHOW, 0);
    }


    @Override
    public void setDeliveryOrderState(boolean deliveryOrderState) {
        this.mPref.edit().putBoolean(USER_DELIVERY_STATE, deliveryOrderState).apply();
    }

    @Override
    public boolean isDeliveryStateEnable() {
        return this.mPref.getBoolean(USER_DELIVERY_STATE, true);
    }

    @Override
    public void setSearchFragmentEnable(boolean state) {
        this.mPref.edit().putBoolean(SEARCH_STATE, state).apply();
    }

    @Override
    public boolean isSearchFragmentEnable() {
        return this.mPref.getBoolean(SEARCH_STATE, false);
    }

    @Override
    public void setMealOrderExist(boolean mealOrderExist) {
        this.mPref.edit().putBoolean(MEAL_ORDER_EXIST, mealOrderExist).apply();
    }

    @Override
    public boolean isMealOrderExist() {
        return this.mPref.getBoolean(MEAL_ORDER_EXIST, false);
    }

    @Override
    public void setDeleteListUserMeals(boolean state) {
        this.mPref.edit().putBoolean(DELETE_LIST_USER_MEAL, state).apply();
    }

    @Override
    public boolean isDeleteListUserMeals() {
        return this.mPref.getBoolean(DELETE_LIST_USER_MEAL, false);
    }

    @Override
    public void setServiceCheckOrderTime(boolean valuOrderTime) {
        this.mPref.edit().putBoolean(SERVICE_CHECK_ORDER_TIME, valuOrderTime).apply();
    }

    @Override
    public boolean isServiceCheckOrderTime() {
        return this.mPref.getBoolean(SERVICE_CHECK_ORDER_TIME, false);
    }

    @Override
    public void setCompanyUserName(String companyUserName) {
        this.mPref.edit().putString(COMPANY_USERNAME, companyUserName).apply();
    }

    @Override
    public String getCopanyUserName() {
        return this.mPref.getString(COMPANY_USERNAME, "");
    }

    @Override
    public void setCompanyPhoneNumber(String companyUserName) {
        this.mPref.edit().putString(COMPANY_PHONE, companyUserName).apply();
    }

    @Override
    public String getCopanyPhoneNumber() {
        return this.mPref.getString(COMPANY_PHONE, "");
    }

    @Override
    public void setSendSuggestion(boolean suggestionValue) {
        this.mPref.edit().putBoolean(SUGGESTION_SEND, suggestionValue).apply();
    }

    @Override
    public boolean isSendSuggestionSet() {
        return this.mPref.getBoolean(SUGGESTION_SEND, false);
    }

    @Override
    public void setUserAccessCode(String accessCode) {
        this.mPref.edit().putString(USER_ACCESS_CODE, accessCode).apply();
    }

    @Override
    public String getUserAccessCode() {
       return this.mPref.getString(USER_ACCESS_CODE, "");
    }

//    @Override
//    public void setDeleteUserData(boolean state) {
//        this.mPref.edit().putBoolean(DELETE_ALL_USER_DATA, state).apply();
//    }
//
//    @Override
//    public boolean isDeleteUserData() {
//        return this.mPref.getBoolean(DELETE_ALL_USER_DATA, false);
//    }
//

}
