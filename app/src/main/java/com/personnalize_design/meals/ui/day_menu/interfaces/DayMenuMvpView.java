package com.personnalize_design.meals.ui.day_menu.interfaces;

import com.personnalize_design.meals.data.model.AllCompanyModel;
import com.personnalize_design.meals.data.model.OtherDayMenuModel;
import com.personnalize_design.meals.data.model.TodayDateModel;
import com.personnalize_design.meals.ui.base.MvpView;

import java.util.List;

public interface DayMenuMvpView extends MvpView {
    void setTodayDateMenu(TodayDateModel todayDateModel);

    void getAllCompanyData(List<AllCompanyModel.DataBean> allCompanyList, int tyOfFragmentTOShow);

    void getAllOtherMenuCompany(List<OtherDayMenuModel.DataBean.OtherMenuBean> otherDayMenuList, String companyCoverImage, boolean isOtherMenuSet, String deliveryPrice);

    void onErrorOccured();
}
