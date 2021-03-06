package com.personnalize_design.meals.data;

import android.content.Context;

import com.personnalize_design.meals.data.db.DbHelper;
import com.personnalize_design.meals.data.model.AddOnMenuModel;
import com.personnalize_design.meals.data.model.AllCompanyModel;
import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.data.model.BillClientInformation;
import com.personnalize_design.meals.data.model.CompanyAccessCode;
import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.data.model.CompanyPromotion;
import com.personnalize_design.meals.data.model.CompanySuggestion;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.data.model.OtherDayMenuModel;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.data.model.TodayDateModel;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.data.network.ApiHelper;
import com.personnalize_design.meals.data.preferences.PreferenceHelper;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private final Context mContext;
    private final PreferenceHelper preferenceHelper;
    private final ApiHelper apiHelper;
    private final DbHelper dbHelper;

    @Inject
    public AppDataManager(Context mContext, PreferenceHelper preferenceHelper, ApiHelper apiHelper, DbHelper dbHelper) {
        this.mContext = mContext;
        this.preferenceHelper = preferenceHelper;
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
    }

    public Context getmContext() {
        return mContext;
    }

    public PreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }

    public ApiHelper getApiHelper() {
        return apiHelper;
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }


    @Override
    public Observable<TodayDateModel> reqGetTodayDate() {
        return apiHelper.reqGetTodayDate();
    }

    @Override
    public Observable<AllCompanyModel.DataBean> reqGetAllCompany() {
        return apiHelper.reqGetAllCompany();
    }

    @Override
    public Observable<OtherDayMenuModel.DataBean.OtherMenuBean> reqCompanyOtherMenu(String companyName) {
        return apiHelper.reqCompanyOtherMenu(companyName);
    }

    @Override
    public Observable<AddOnMenuModel.DataBean.AccompagnementBean> reqAdditionMenu(String companyName) {
        return apiHelper.reqAdditionMenu(companyName);
    }

    @Override
    public Observable<UserOrderModel> reqSendUserOrderToCompany(BillClientInformation billClientInformation) {
        return apiHelper.reqSendUserOrderToCompany(billClientInformation);
    }

    @Override
    public Observable<AllCompanySearch> reqAllCompanySearch() {
        return apiHelper.reqAllCompanySearch();
    }

    @Override
    public Observable<AllCompanyModel.DataBean> reqOneCompany(String companyName) {
        return apiHelper.reqOneCompany(companyName);
    }

    @Override
    public Observable<OneCompanySearchModel> reqOneCompanySearch(String companyName) {
        return apiHelper.reqOneCompanySearch(companyName);
    }

    @Override
    public Observable<ServerResponse> reqEndHourMealsOrder(String companyName) {
        return apiHelper.reqEndHourMealsOrder(companyName);
    }

    @Override
    public Single<ServerResponse> reqCheckTime() {
        return apiHelper.reqCheckTime();
    }

    @Override
    public Single<ServerResponse> reqCheckSuggestionTime() {
        return apiHelper.reqCheckSuggestionTime();
    }

    @Override
    public Observable<CompanyAccessCode> reqAccessCodeValidation(String accessCode) {
        return apiHelper.reqAccessCodeValidation(accessCode);
    }

    @Override
    public Observable<CompanySuggestion> reqCheckCompanySuggestionEnable() {
        return apiHelper.reqCheckCompanySuggestionEnable();
    }

    @Override
    public Observable<ServerResponse> reqSendSuggestionMeal(String suggestionMealText) {
        return apiHelper.reqSendSuggestionMeal(suggestionMealText);
    }

    @Override
    public Observable<CompanyCatalog> reqCompanyCatalog(String companyName) {
        return apiHelper.reqCompanyCatalog(companyName);
    }

    @Override
    public Observable<CompanyPromotion> reqCompanyPromotion(String companyName) {
        return apiHelper.reqCompanyPromotion(companyName);
    }


//    @Override
//    public Observable<ServerResponse> reqSendUserOrderToCompany(String totalMealsPrices, String companyName, String clienName, String clientContact, String clientLocalisation, List<MainMealSelectedModel> listMealSelected) {
//        return apiHelper.reqSendUserOrderToCompany(totalMealsPrices, companyName, clienName, clientContact, clientLocalisation, listMealSelected);
//    }

    @Override
    public void setFragmentStateToShow(int fragmentState) {
        preferenceHelper.setFragmentStateToShow(fragmentState);
    }

    @Override
    public int getFragmentStateToShow() {
        return preferenceHelper.getFragmentStateToShow();
    }

    @Override
    public void setDeliveryOrderState(boolean deliveryOrderState) {
        preferenceHelper.setDeliveryOrderState(deliveryOrderState);
    }

    @Override
    public boolean isDeliveryStateEnable() {
        return preferenceHelper.isDeliveryStateEnable();
    }

    @Override
    public void setSearchFragmentEnable(boolean state) {
        preferenceHelper.setSearchFragmentEnable(state);
    }

    @Override
    public boolean isSearchFragmentEnable() {
        return preferenceHelper.isSearchFragmentEnable();
    }

    @Override
    public void setMealOrderExist(boolean mealOrderExist) {
        preferenceHelper.setMealOrderExist(mealOrderExist);
    }

    @Override
    public boolean isMealOrderExist() {
        return preferenceHelper.isMealOrderExist();
    }

    @Override
    public void setDeleteListUserMeals(boolean state) {
        preferenceHelper.setDeleteListUserMeals(state);
    }

    @Override
    public boolean isDeleteListUserMeals() {
        return preferenceHelper.isDeleteListUserMeals();
    }

    @Override
    public void setServiceCheckOrderTime(boolean valuOrderTime) {
        preferenceHelper.setServiceCheckOrderTime(valuOrderTime);
    }

    @Override
    public boolean isServiceCheckOrderTime() {
        return preferenceHelper.isServiceCheckOrderTime();
    }

    @Override
    public void setCompanyUserName(String companyUserName) {
        preferenceHelper.setCompanyUserName(companyUserName);
    }

    @Override
    public String getCopanyUserName() {
        return preferenceHelper.getCopanyUserName();
    }

    @Override
    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        preferenceHelper.setCompanyPhoneNumber(companyPhoneNumber);
    }

    @Override
    public String getCopanyPhoneNumber() {
        return preferenceHelper.getCopanyPhoneNumber();
    }

    @Override
    public void setSendSuggestion(boolean suggestionValue) {
        preferenceHelper.setSendSuggestion(suggestionValue);
    }

    @Override
    public boolean isSendSuggestionSet() {
        return preferenceHelper.isSendSuggestionSet();
    }

    @Override
    public void setUserAccessCode(String accessCode) {
        preferenceHelper.setUserAccessCode(accessCode);
    }

    @Override
    public String getUserAccessCode() {
        return preferenceHelper.getUserAccessCode();
    }

//    @Override
//    public void setDeleteUserData(boolean state) {
//        preferenceHelper.setDeleteUserData(state);
//    }
//
//    @Override
//    public boolean isDeleteUserData() {
//        return preferenceHelper.isDeleteUserData();
//    }

    @Override
    public Completable saveUserOrder(UserOrderModel.UserOrder userOrder) {
        return dbHelper.saveUserOrder(userOrder);
    }

    @Override
    public Completable saveUserListOrder(List<UserOrderModel.MealListBean> listUserOrderMeal) {
        return dbHelper.saveUserListOrder(listUserOrderMeal);
    }

    @Override
    public Single<UserOrderModel.UserOrder> getUserOrderInfo() {
        return dbHelper.getUserOrderInfo();
    }

    @Override
    public Flowable<List<UserOrderModel.MealListBean>> getUserListMeal(int id) {
        return dbHelper.getUserListMeal(id);
    }

    @Override
    public Completable deleteAllUserOrder() {
        return dbHelper.deleteAllUserOrder();
    }

    @Override
    public Completable deleteAllUserOrderMealList() {
        return dbHelper.deleteAllUserOrderMealList();
    }
}

