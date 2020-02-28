package com.personnalize_design.meals.ui.meal_addition.interfaces;

import com.personnalize_design.meals.data.model.AddOnMenuModel;
import com.personnalize_design.meals.ui.base.MvpView;

import java.util.List;

public interface AccompagnementScreenMvpView extends MvpView {
    void onAdditionMenuReceive(List<AddOnMenuModel.DataBean.AccompagnementBean> accompagnementList, boolean isListEmpty);
}
