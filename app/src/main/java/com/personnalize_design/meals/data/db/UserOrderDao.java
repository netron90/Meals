package com.personnalize_design.meals.data.db;

import com.personnalize_design.meals.data.model.UserOrderModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface UserOrderDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void updateChildrenFault( ChildFaultsLate.FautesBean faultBean );

//    //Select Query
//    @Query("SELECT * FROM trainer")
//    public Flowable<List<ParentChild.ElevesBean>> getAllChild();

    @Insert
    public Completable saveUserOrder(UserOrderModel.UserOrder userOrder);

    @Insert
    public Completable saveUserListOrder(List<UserOrderModel.MealListBean> listUserOrderMeal);

    @Query("SELECT * FROM user_order")
    public Single<UserOrderModel.UserOrder> getUserOrderInfo();

    @Query("SELECT * FROM user_meal_list WHERE id_list = :id")
    public Flowable<List<UserOrderModel.MealListBean>> getUserListMeal(int id);

    @Query("DELETE FROM user_order")
    public Completable deleteAllUserOrder();

    @Query("DELETE FROM user_meal_list")
    public Completable deleteAllUserOrderMealList();

}
