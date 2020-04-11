package com.personnalize_design.meals.data.db;

import com.personnalize_design.meals.constants.Converters;
import com.personnalize_design.meals.data.model.DayMenuModel;
import com.personnalize_design.meals.data.model.UserOrderModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {UserOrderModel.UserOrder.class, UserOrderModel.MealListBean.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MealsDatabase extends RoomDatabase {

    public abstract UserOrderDao userOrderDao();


}
