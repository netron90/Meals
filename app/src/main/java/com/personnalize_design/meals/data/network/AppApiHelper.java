package com.personnalize_design.meals.data.network;

import com.personnalize_design.meals.constants.Mutils;
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
import com.personnalize_design.meals.data.preferences.PreferenceHelper;
import com.personnalize_design.meals.ui.meal_addition.AccompagnementScreen;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

public class AppApiHelper implements ApiHelper {

    private PreferenceHelper mPref;
    private Retrofit retrofit;
    private Mutils mUtils;

    @Inject
    public AppApiHelper(Retrofit retrofit, PreferenceHelper mPref) {
        this.retrofit = retrofit;
        this.mPref = mPref;
        this.mUtils = new Mutils(this);
    }


    public PreferenceHelper getmPref() {
        return mPref;
    }

    public void setmPref(PreferenceHelper mPref) {
        this.mPref = mPref;
    }

    @Override
    public Observable<TodayDateModel> reqGetTodayDate() {
        Observable<TodayDateModel> source = retrofit.create (RestClient.class).reqGetTodayDate ();
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<AllCompanyModel.DataBean> reqGetAllCompany() {
        Observable<AllCompanyModel> source = retrofit.create (RestClient.class).reqGetAllCompany ();
        return source.concatMap(new Function<AllCompanyModel, ObservableSource<AllCompanyModel.DataBean>>() {
            @Override
            public ObservableSource<AllCompanyModel.DataBean> apply(AllCompanyModel allCompanyModel) throws Exception {
                return Observable.fromIterable(allCompanyModel.getData())
                        .retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));

            }
        });
    }

    @Override
    public Observable<OtherDayMenuModel.DataBean.OtherMenuBean> reqCompanyOtherMenu(String companyName) {
        Observable<OtherDayMenuModel> source = retrofit.create (RestClient.class).reqCompanyOtherMenu (companyName);
        return source.concatMap(new Function<OtherDayMenuModel, ObservableSource<OtherDayMenuModel.DataBean.OtherMenuBean>>() {
            @Override
            public ObservableSource<OtherDayMenuModel.DataBean.OtherMenuBean> apply(OtherDayMenuModel otherDayMenuModel) throws Exception {
                return Observable.fromIterable(otherDayMenuModel.getData().getOtherMenu())
                        .retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));

            }
        });
    }

    @Override
    public Observable<AddOnMenuModel.DataBean.AccompagnementBean> reqAdditionMenu(String companyName) {
        Observable<AddOnMenuModel> source = retrofit.create (RestClient.class).reqAdditionMenu (companyName);
        return source.concatMap(new Function<AddOnMenuModel, ObservableSource<AddOnMenuModel.DataBean.AccompagnementBean>>() {

            @Override
            public ObservableSource<AddOnMenuModel.DataBean.AccompagnementBean> apply(AddOnMenuModel addOnMenuModel) throws Exception {
                return Observable.fromIterable(addOnMenuModel.getData().getAccompagnement())
                        .retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
            }
        });
    }

    @Override
    public Observable<UserOrderModel> reqSendUserOrderToCompany(BillClientInformation billClientInformation) {
        Observable<UserOrderModel> source = retrofit.create (RestClient.class).reqSendUserOrderToCompany(billClientInformation);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<AllCompanySearch> reqAllCompanySearch() {
        Observable<AllCompanySearch> source = retrofit.create (RestClient.class).reqAllCompanySearch ();
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));    }

    @Override
    public Observable<AllCompanyModel.DataBean> reqOneCompany(String companyName) {

//        Observable<AllCompanyModel> source = retrofit.create (RestClient.class).reqOneCompany (companyName);
//
//        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));

//        return  retrofit.create (RestClient.class).reqOneCompany (companyName);

//        source.flatMap(new Function<AllCompanyModel, Single<AllCompanyModel.DataBean>>() {
//            @Override
//            public Single<AllCompanyModel.DataBean> apply(AllCompanyModel allCompanyModel) throws Exception {
//                return Single.create(new SingleOnSubscribe<AllCompanyModel.DataBean>() {
//                    @Override
//                    public void subscribe(SingleEmitter<AllCompanyModel.DataBean> emitter) throws Exception {
//
//                    }
//                });
//            }
//        });


        Observable<AllCompanyModel> source = retrofit.create (RestClient.class).reqOneCompany (companyName);
        return source.concatMap(new Function<AllCompanyModel, ObservableSource<AllCompanyModel.DataBean>>() {
            @Override
            public ObservableSource<AllCompanyModel.DataBean> apply(AllCompanyModel allCompanyModel) throws Exception {
                return Observable.fromIterable(allCompanyModel.getData())
                        .retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));

            }
        });

//        .concatMap(new Function<AllCompanyModel, ObservableSource<AllCompanyModel.DataBean>>() {
//            @Override
//            public ObservableSource<AllCompanyModel.DataBean> apply(AllCompanyModel allCompanyModel) throws Exception {
//                return Observable.fromIterable(allCompanyModel.getData())
//                        .retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
//
//            }
//        });
    }

    @Override
    public Observable<OneCompanySearchModel> reqOneCompanySearch(String companyName) {
        Observable<OneCompanySearchModel> source = retrofit.create (RestClient.class).reqOneCompanySearch (companyName);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<ServerResponse> reqEndHourMealsOrder(String companyName) {
        Observable<ServerResponse> source = retrofit.create (RestClient.class).reqEndHourMealsOrder (companyName);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Single<ServerResponse> reqCheckTime() {
        Single<ServerResponse> source = retrofit.create (RestClient.class).reqCheckTime();
        return source;
    }

    @Override
    public Single<ServerResponse> reqCheckSuggestionTime() {
        Single<ServerResponse> source = retrofit.create (RestClient.class).reqCheckSuggestionTime();
        return source;
    }

    @Override
    public Observable<CompanyAccessCode> reqAccessCodeValidation(String accessCode) {
        Observable<CompanyAccessCode> source = retrofit.create (RestClient.class).reqAccessCodeValidation (accessCode);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<CompanySuggestion> reqCheckCompanySuggestionEnable() {
//        Observable<CompanySuggestion> source = retrofit.create (RestClient.class).reqCheckCompanySuggestionEnable (companyName);
        Observable<CompanySuggestion> source = retrofit.create (RestClient.class).reqCheckCompanySuggestionEnable ();
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<ServerResponse> reqSendSuggestionMeal(String suggestionMealText) {
        Observable<ServerResponse> source = retrofit.create (RestClient.class).reqSendSuggestionMeal (suggestionMealText);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<CompanyCatalog> reqCompanyCatalog(String companyName) {
        Observable<CompanyCatalog> source = retrofit.create (RestClient.class).reqCompanyCatalog (companyName);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }

    @Override
    public Observable<CompanyPromotion> reqCompanyPromotion(String companyName) {
        Observable<CompanyPromotion> source = retrofit.create (RestClient.class).reqCompanyPromotion (companyName);
        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
//        return source.map(companyPromotion -> companyPromotion.getCompanyEvent())
//                .retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
    }


//    @Override
//    public Observable<ServerResponse> reqSendUserOrderToCompany(String totalMealsPrices,
//                                                                String companyName,
//                                                                String clientName,
//                                                                String clientContact,
//                                                                String clientLocalisation,
//                                                                List<MainMealSelectedModel> listMealSelected) {
//        Observable<ServerResponse> source = retrofit.create (RestClient.class).reqSendUserOrderToCompany (
//                                                            totalMealsPrices, companyName, clientName, clientContact,
//                                                            clientLocalisation, listMealSelected);
//
//        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
//    }


//    @Override
//    public Observable<ServerResponse> reqYaourtOrder ( String ouncesValue , String compositionValue , String quantityValue , String deliveryDayValue , String locationPlaceValue , String userPhoneNumberValue, String deliveryPlaceValue, String moreDetailPlaceValue ) {
//        Observable<ServerResponse> source = retrofit.create (RestClient.class).reqYogurtOrder (ouncesValue, compositionValue, quantityValue, deliveryDayValue, locationPlaceValue, userPhoneNumberValue, deliveryPlaceValue, moreDetailPlaceValue);
//        return source.retryWhen (throwableObservable -> mUtils.retryOnErrorGetData (throwableObservable, source));
//    }



}
