package com.personnalize_design.meals.constants;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.network.AppApiHelper;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class Mutils {
    private AppApiHelper mAppApiHelper;

    private Context context;
    public Mutils( AppApiHelper mAppApiHelper) {
        this.mAppApiHelper = mAppApiHelper;
    }

    //URL REST API
    public static final String BASE_URL = "http://p-meals.herokuapp.com";
//    public static final String BASE_URL = "http://localhost:3000";

    //TODAY DAY MENU
    public static final String TODAY_DATE = BASE_URL + "/today_date";

    //ALL COMPANY DATA
    public static final String FIND_ALL_COMPANY = BASE_URL + "/api/all_company";

    //ALL COMPANY OTHER MENU
    public static final String COMPANY_OTHER_MENU = BASE_URL + "/api/other_menu";

    //ALL COMPANY OTHER MENU
    public static final String ADDITION_MENU = BASE_URL + "/accompagnement_menu";

    //ALL COMPANY
    public static final String ALL_COMPANY_SEARCH = BASE_URL + "/api/all-company-search";

    //ONE COMPANY
    public static final String GET_ONE_COMPANY = BASE_URL + "/api/one_company";

    //ONE COMPANY SEARCH
    public static final String GET_ONE_COMPANY_SEARCH = BASE_URL + "/api/one-company-search";

    //END HOUR MEAL ORDER
    public static final String END_OUR_MEAL_ORDER = BASE_URL + "/end_hour_meal_order";

    //CHECK TIME - NOTIFY USER
    public static final String CHECK_TIME = BASE_URL + "/api/check-time";

    //CHECK TIME - NOTIFY USER
    public static final String CHECK_SUGGESTION_TIME = BASE_URL + "/api/check-suggestion-time";

    //SEND USER ORDER TO COMPANY
    public static final  String URL_SEND_USER_ORDER = BASE_URL + "/sendUserOrder";

    //ACCESS CODE VALIDATION
    public static final String ACCESS_CODE = BASE_URL + "/api/access-code";

    //COMPANY_SUGGESTION_ENABLE
    public static final String COMPANY_SUGGESTION_ENABLE = BASE_URL + "/api/company-suggestion-enable";

    //SEND_SUGGESTION_MEAL
    public static final String SEND_SUGGESTION_MEAL = BASE_URL + "/api/send-suggestion-meal";

    //COMPANY_CATALOGUE
    public static final String COMPANY_CATALOGUE = BASE_URL + "/api/catalog";

    //COMPANY PROMOTION
    public static final String COMPANY_PROMOTION = BASE_URL + "/api/pub";

    //APP CONSTANTS
    public static final long ANIM_DURATION = 4000;
    public static final long ONE_HOUR_IN_MIL_SEC_CONSTANT = 36000000;
    public static final long HOUR_IN_MIL_SEC_CONSTANT = 36000000 * 5;
    public static final long MIN_IN_MIL_SEC_CONSTANT = 1200000;
    public static final long EVERY_15_SEC = 15000;
    public static final String FRAGMENT_STATE_SHOW = "fragment_sate_show";
    public static final String SEARCH_STATE = "search_state";
    public static final String USER_ORDER_FROM_SERVER = "user_order_from_server";
    public static final String USER_DELIVERY_STATE = "user_delivery_state";
    public static final String MEAL_ORDER_EXIST = "user_meal_order_exist";

    public static final String MAIN_MEAL_SELECTED_ITEM = "main_meal_selected_item";

    public static final String DELETE_LIST_USER_MEAL = "delete_list_user_meal";
    public static final String DELETE_ALL_USER_DATA = "delete_all_user_data";
    public static final String SERVICE_CHECK_ORDER_TIME = "service_check_order_time";
    public static final String COMPANY_USERNAME = "companyUsername";
    public static final String COMPANY_PHONE = "companyPhoneNumber";
    public static final String SUGGESTION_SEND = "suggestionSend";
    public static final String USER_ACCESS_CODE = "user_access_code";

    public static final String ACTION_SEND_BROADCAST = "com.personnalize_design.meals.START_NOTIFY_USER_SERVICE.broadcast";
    public static final String ACTION_TIME_DELETE_USER_ORDER = "com.personnalize_design.meals.TIME_TO_DELETE_USER_ORDER.broadcast";










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
