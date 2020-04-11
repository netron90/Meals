package com.personnalize_design.meals.ui.promotion.interfaces;

import com.personnalize_design.meals.data.model.CompanyPromotion;
import com.personnalize_design.meals.ui.base.MvpView;

public interface PromotionMvpView extends MvpView {
    void OnErrorOccured();

    void OnGetCompanyPromotion(CompanyPromotion companyPromotion);

    void OnGetNoCompanyPromotion();
}
