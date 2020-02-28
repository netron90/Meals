package com.personnalize_design.meals.ui.search.interfaces;

import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.ui.base.MvpView;

import java.util.List;

public interface SearchFragmentMvpView extends MvpView {
    void onGetAllCOmpanySearch(List<AllCompanySearch.CompanyData> list);

    void onErrorOccured();
}
