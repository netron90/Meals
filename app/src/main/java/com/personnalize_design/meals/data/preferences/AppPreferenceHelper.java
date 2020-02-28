package com.personnalize_design.meals.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import static com.personnalize_design.meals.constants.Mutils.FRAGMENT_STATE_SHOW;
import static com.personnalize_design.meals.constants.Mutils.SEARCH_STATE;
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
        this.mPref.edit().putBoolean(USER_DELIVERY_STATE, deliveryOrderState);
    }

    @Override
    public boolean isDeliveryStateEnable() {
        return this.mPref.getBoolean(USER_DELIVERY_STATE, true);
    }

    @Override
    public void setSearchFragmentEnable(boolean state) {
        this.mPref.edit().putBoolean(SEARCH_STATE, state);
    }

    @Override
    public boolean isSearchFragmentEnable() {
        return this.mPref.getBoolean(SEARCH_STATE, false);
    }



}
