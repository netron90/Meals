package com.personnalize_design.meals.data.network;

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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Query;

public interface ApiHelper {

//    Observable<ServerResponse> reqYaourtOrder(String ouncesValue, String compositionValue,
//                                              String quantityValue, String deliveryDayValue,
//                                              String locationPlaceValue,
//                                              String userPhoneNumberValue,
//                                              String deliveryPlaceValue,
//                                              String moreDetailPlaceValue);

    Observable<TodayDateModel> reqGetTodayDate();

    Observable<AllCompanyModel.DataBean> reqGetAllCompany();

    Observable<OtherDayMenuModel.DataBean.OtherMenuBean> reqCompanyOtherMenu(String companyName);

    Observable<AddOnMenuModel.DataBean.AccompagnementBean> reqAdditionMenu(String companyName);
//
//    Observable<ServerResponse> reqSendUserOrderToCompany(String totalMealsPrices,
//                                                         String companyName,
//                                                         String clienName,
//                                                         String clientContact,
//                                                         String clientLocalisation,
//                                                         List<MainMealSelectedModel> listMealSelected);


    Observable<UserOrderModel> reqSendUserOrderToCompany(BillClientInformation billClientInformation);
    Observable<AllCompanySearch> reqAllCompanySearch();
    Observable<AllCompanyModel.DataBean> reqOneCompany(String companyName);
    Observable<OneCompanySearchModel> reqOneCompanySearch( String companyName);
    Observable<ServerResponse> reqEndHourMealsOrder(String companyName);
    Single<ServerResponse> reqCheckTime();
    Single<ServerResponse> reqCheckSuggestionTime();
    Observable<CompanyAccessCode> reqAccessCodeValidation(String accessCode);
//    Observable<CompanySuggestion> reqCheckCompanySuggestionEnable(String companyName);
    Observable<CompanySuggestion> reqCheckCompanySuggestionEnable();
    Observable<ServerResponse> reqSendSuggestionMeal(String suggestionMealText);
    Observable<CompanyCatalog> reqCompanyCatalog(String companyName);
    Observable<CompanyPromotion> reqCompanyPromotion(String companyName);

}
