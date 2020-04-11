package com.personnalize_design.meals.ui.catalog.interfaces;

import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.ui.base.MvpView;

public interface CatalogMvpView extends MvpView {
    void OnErrorOccured();

    void OnNoUserFound();

    void OnCatalogFound(CompanyCatalog.UserDataBean userData);
}
