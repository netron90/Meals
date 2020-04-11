package com.personnalize_design.meals.ui.suggestion.presenters;

import android.util.Log;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.CompanySuggestion;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.suggestion.interfaces.SuggestionMvpView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SuggestionPresenter <V extends SuggestionMvpView> extends BasePresenter<V> implements SuggestionMvpPresenter<V> {

    private Disposable disposable, disposable2;
    private ServerResponse serverResponses = null;

    @Inject
    public SuggestionPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

//    public void checkCompanySuggestionEnable(String copanyUserName) {
//        getDataManager().reqCheckCompanySuggestionEnable(copanyUserName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CompanySuggestion>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable = d;
//                    }
//
//                    @Override
//                    public void onNext(CompanySuggestion companySuggestion) {
//                        if (companySuggestion.getMessage().equals("no plan enable")){
//                            if(getView() != null){
//                                getView().OnSuggestionNotEnable();
//                            }
//                        }else {
//                            if(getView() != null){
//                                String planName = companySuggestion.getPlanName().getPlanName();
//                                getView().OnSuggestionEnable(planName);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        if(disposable != null){
//                            disposable.dispose();
//                            disposable = null;
//                        }
//                        if(getView() != null){
//                            getView().OnErrorAccessCodeValidation();
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if(disposable != null){
//                            disposable.dispose();
//                            disposable = null;
//                        }
//                    }
//                });
//    }

    public void sendSuggestionMeal(String suggestionMeal) {
//        String companyName = getDataManager().getCopanyUserName();
        getDataManager().reqSendSuggestionMeal(suggestionMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable2 = d;
                    }

                    @Override
                    public void onNext(ServerResponse serverResponse) {
                       // serverResponses = serverResponse;
                        if(serverResponse.getMessage().equals("missing parameters")){
                            if(getView() != null){
                                getView().onErrorOccured();
                            }
                        }else if(serverResponse.getMessage().equals("suggestion meals send successfully")){
                            if(getView() != null){
                                Log.d("MEAL SEND SUCCES", "Meqal send successfully");
                                getView().SuggestionSendSuccessfully();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(disposable2 != null){
                            disposable2.dispose();
                            disposable2 = null;
                        }
                        if(getView() != null){
                            getView().onErrorOccured();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(disposable2 != null){
                            disposable2.dispose();
                            disposable2 = null;
                        }


                    }
                });
    }

    public void checkSuggestionTime() {
        getDataManager().reqCheckSuggestionTime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ServerResponse>() {
                    @Override
                    public void accept(ServerResponse serverResponse) throws Exception {
                        if(serverResponse.getMessage().equals("C'est l'heure de faire une suggestion")){
                            if(getView() != null){

                                getView().isTimeSuggestion();
                            }
                        }else{
                            if(getView() != null){

                                getView().isNotTimeSuggestion();
                            }
                        }

                    }
                }, throwable -> {
                   throwable.printStackTrace();
                   if(getView() != null){
                       getView().onErrorOccured();
                   }
                });
    }
}
