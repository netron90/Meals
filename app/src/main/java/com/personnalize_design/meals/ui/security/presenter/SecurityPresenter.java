package com.personnalize_design.meals.ui.security.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.CompanyAccessCode;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.security.interfaces.SecurityMvpView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SecurityPresenter <V extends SecurityMvpView> extends BasePresenter<V> implements SecurityMvpPresenter<V> {

    private Disposable disposable;
    @Inject
    public SecurityPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void checkAccessCode(String accessCodeText) {
        getDataManager().reqAccessCodeValidation(accessCodeText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyAccessCode>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CompanyAccessCode serverResponse) {
                        if (serverResponse.getMessage().equals("aucune correspondance")){
//                            getDataManager().setUserAccessCode("");
                            if(getView() != null){
                                getView().OnAccessCodeValidationIncorrect();
                            }
                        }else if(serverResponse.getMessage().equals("Abonnement termine")){
                            getDataManager().setUserAccessCode("");
                            if(getView() != null){
                                getView().OnSubscriptionEnd();
                            }
                        } else {
                            if(getDataManager().getUserAccessCode().equals("")){
                                getDataManager().setUserAccessCode(serverResponse.getCompanyData().getCodeDacces());
                                if(getView() != null){
                                    getView().OnSuccessAccessCodeValidation();
                                }
                            }else{
                                if(getView() != null){

                                    getView().OnSuccessAccessCodeValidation();
                                }
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(disposable != null){
                            disposable.dispose();
                            disposable = null;
                        }
                        if(getView() != null){
                            getView().OnErrorAccessCodeValidation();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(disposable != null){
                            disposable.dispose();
                            disposable = null;
                        }
                    }
                });
    }
}
