package com.personnalize_design.meals.data.network;

import com.personnalize_design.meals.data.model.AddOnMenuModel;
import com.personnalize_design.meals.data.model.AllCompanyModel;
import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.data.model.BillClientInformation;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.data.model.OtherDayMenuModel;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.data.model.TodayDateModel;
import com.personnalize_design.meals.data.model.UserOrderModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.personnalize_design.meals.constants.Mutils.ADDITION_MENU;
import static com.personnalize_design.meals.constants.Mutils.ALL_COMPANY_SEARCH;
import static com.personnalize_design.meals.constants.Mutils.COMPANY_OTHER_MENU;
import static com.personnalize_design.meals.constants.Mutils.FIND_ALL_COMPANY;
import static com.personnalize_design.meals.constants.Mutils.GET_ONE_COMPANY;
import static com.personnalize_design.meals.constants.Mutils.GET_ONE_COMPANY_SEARCH;
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

//    @FormUrlEncoded
    @POST(URL_SEND_USER_ORDER)
    Observable<UserOrderModel> reqSendUserOrderToCompany  (
                                                  @Body BillClientInformation billClientInformation);
//                                                  @Field("companyName") String companyName,
//                                                  @Field("clientName") String clientName,
//                                                  @Field("clientContact") String clientContact,
//                                                  @Field("clientLocalisation") String clientLocalisastion,
//                                                  @Field ("listMealSelected[]") List<MainMealSelectedModel> listMealSelected);

}
