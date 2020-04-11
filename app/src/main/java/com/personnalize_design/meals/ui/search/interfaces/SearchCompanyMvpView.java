package com.personnalize_design.meals.ui.search.interfaces;

import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.ui.base.MvpView;

import java.util.List;

public interface SearchCompanyMvpView extends MvpView {
    void onErrorOccured();

    void onFoundOneCompanySuccess(List<OneCompanySearchModel.CompanyData> oneCompanySearchModel);

    void onNoCompanyFound();
}
