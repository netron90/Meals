package com.personnalize_design.meals.ui.search.interfaces;

import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.ui.base.MvpView;

public interface SearchCompanyMvpView extends MvpView {
    void onErrorOccured();

    void onFoundOneCOmpanySuccess(OneCompanySearchModel oneCompanySearchModel);

    void onNoCompanyFound();
}
