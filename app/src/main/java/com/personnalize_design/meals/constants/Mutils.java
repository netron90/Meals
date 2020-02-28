package com.personnalize_design.meals.constants;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.network.AppApiHelper;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class Mutils {
    private AppApiHelper mAppApiHelper;

    public Mutils( AppApiHelper mAppApiHelper) {
        this.mAppApiHelper = mAppApiHelper;
    }

    //URL REST API
    public static final String BASE_URL = "http://p-meals.herokuapp.com";
//    public static final String BASE_URL = "http://localhost:3000";

    //TODAY DAY MENU
    public static final String TODAY_DATE = BASE_URL + "/today_date";

    //ALL COMPANY DATA
    public static final String FIND_ALL_COMPANY = BASE_URL + "/all_company";

    //ALL COMPANY OTHER MENU
    public static final String COMPANY_OTHER_MENU = BASE_URL + "/other_menu";

    //ALL COMPANY OTHER MENU
    public static final String ADDITION_MENU = BASE_URL + "/accompagnement_menu";

    //ALL COMPANY
    public static final String ALL_COMPANY_SEARCH = BASE_URL + "/all-company-search";

    //ONE COMPANY
    public static final String GET_ONE_COMPANY = BASE_URL + "/one_company";

    //ONE COMPANY SEARCH
    public static final String GET_ONE_COMPANY_SEARCH = BASE_URL + "/one-company-search";

    //SEND USER ORDER TO COMPANY
    public static final  String URL_SEND_USER_ORDER = BASE_URL + "/sendUserOrder";

    //APP CONSTANTS
    public static final long ANIM_DURATION = 4000;
    public static final String FRAGMENT_STATE_SHOW = "fragment_sate_show";
    public static final String SEARCH_STATE = "search_state";
    public static final String USER_ORDER_FROM_SERVER = "user_order_from_server";
    public static final String USER_DELIVERY_STATE = "user_delivery_state";

    public static final String MAIN_MEAL_SELECTED_ITEM = "main_meal_selected_item";

    public static final String POKEMON_TRAINER_NAME = "message_prof_chen";

    public static final int PROF_CHEN_MSG1_VALUE = 1;
    public static final int PROF_CHEN_MSG2_VALUE = 2;
    public static final int PROF_CHEN_MSG3_VALUE = 3;
    public static final int PROF_CHEN_MSG4_VALUE = 4;









    public ObservableSource<?> retryOnErrorGetData(Observable<Throwable> errors, Observable<?> source){

        return errors.flatMap(error -> {
            Log.d("Error OnErrorAuth", ""+error);
            if(error instanceof HttpException)
            {
                HttpException e = (HttpException) error;
                if(e.code() == 408 || e.code() == 504)
                {

                    Log.d("Error OnErrorAuth", "Error 408 or 504. RetryWhen is executed");
                    return source;
                }
            }else if(error instanceof SocketTimeoutException){
                Log.d("Error OnErrorAuth", "Error SocketTimeOutException. RetryWhen is executed");
                return source;
            }
            Log.d("Error OnErrorAuth", "Error RetryWhen not sucribing again");
            return Observable.error(error);
        });
    }

}
