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
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.personnalize_design.meals.constants.Mutils.ACCESS_CODE;
import static com.personnalize_design.meals.constants.Mutils.ADDITION_MENU;
import static com.personnalize_design.meals.constants.Mutils.ALL_COMPANY_SEARCH;
import static com.personnalize_design.meals.constants.Mutils.CHECK_SUGGESTION_TIME;
import static com.personnalize_design.meals.constants.Mutils.CHECK_TIME;
import static com.personnalize_design.meals.constants.Mutils.COMPANY_CATALOGUE;
import static com.personnalize_design.meals.constants.Mutils.COMPANY_OTHER_MENU;
import static com.personnalize_design.meals.constants.Mutils.COMPANY_PROMOTION;
import static com.personnalize_design.meals.constants.Mutils.COMPANY_SUGGESTION_ENABLE;
import static com.personnalize_design.meals.constants.Mutils.END_OUR_MEAL_ORDER;
import static com.personnalize_design.meals.constants.Mutils.FIND_ALL_COMPANY;
import static com.personnalize_design.meals.constants.Mutils.GET_ONE_COMPANY;
import static com.personnalize_design.meals.constants.Mutils.GET_ONE_COMPANY_SEARCH;
import static com.personnalize_design.meals.constants.Mutils.SEND_SUGGESTION_MEAL;
import static com.personnalize_design.meals.constants.Mutils.TODAY_DATE;
import static com.personnalize_design.meals.constants.Mutils.URL_SEND_USER_ORDER;

public interface RestClient {

//    @FormUrlEncoded
//    @POST(URL_SELL_POT_ORDER)
//    Observable<ServerResponse> reqSellPotOrder   ( @Field("locationPlaceParam") String locationValue,
//                                                   @Field ("locationPlaceMoreDetailParam") String locationMoreDetailValue,
//                                                   @Field ("phoneNumberParam") String phoneNumberValue);

    @GET(TODAY_DATE)
    Observable<TodayDateModel> reqGetTodayDate();

    @GET(FIND_ALL_COMPANY)
    Observable<AllCompanyModel> reqGetAllCompany();

    @GET(COMPANY_OTHER_MENU)
    Observable<OtherDayMenuModel> reqCompanyOtherMenu(@Query("companyName") String companyName);

    @GET(ADDITION_MENU)
    Observable<AddOnMenuModel> reqAdditionMenu(@Query("companyName") String companyName);

    @GET(ALL_COMPANY_SEARCH)
    Observable<AllCompanySearch> reqAllCompanySearch();

    @GET(GET_ONE_COMPANY)
    Observable<AllCompanyModel> reqOneCompany(@Query("companyName") String companyName);

    @GET(GET_ONE_COMPANY_SEARCH)
    Observable<OneCompanySearchModel> reqOneCompanySearch(@Query("companyName") String companyName);

    @GET(END_OUR_MEAL_ORDER)
    Observable<ServerResponse> reqEndHourMealsOrder(@Query("companyName") String companyName);

    @GET(CHECK_TIME)
    Single<ServerResponse> reqCheckTime();

    @GET(CHECK_SUGGESTION_TIME)
    Single<ServerResponse> reqCheckSuggestionTime();

//    @FormUrlEncoded
    @POST(URL_SEND_USER_ORDER)
    Observable<UserOrderModel> reqSendUserOrderToCompany  (
                                                  @Body BillClientInformation billClientInformation);
//                                                  @Field("companyName") String companyName,
//                                                  @Field("clientName") String clientName,
//                                                  @Field("clientContact") String clientContact,
//                                                  @Field("clientLocalisation") String clientLocalisastion,
//                                                  @Field ("listMealSelected[]") List<MainMealSelectedModel> listMealSelected);

    @GET(ACCESS_CODE)
    Observable<CompanyAccessCode> reqAccessCodeValidation(@Query("accessCode") String accessCode);

    @GET(COMPANY_SUGGESTION_ENABLE)
//    Observable<CompanySuggestion> reqCheckCompanySuggestionEnable(@Query("companyName") String companyName);
    Observable<CompanySuggestion> reqCheckCompanySuggestionEnable();

    @FormUrlEncoded
    @POST(SEND_SUGGESTION_MEAL)
    Observable<ServerResponse> reqSendSuggestionMeal(@Field ("suggestionMealText") String suggestionMealText);

    @GET(COMPANY_CATALOGUE)
    Observable<CompanyCatalog> reqCompanyCatalog(@Query("companyName") String companyName);

    @GET(COMPANY_PROMOTION)
    Observable<CompanyPromotion> reqCompanyPromotion(@Query("companyName") String companyName);

}
