package com.personnalize_design.meals.data.db;

import android.database.Cursor;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.personnalize_design.meals.data.model.UserOrderModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserOrderDao_Impl implements UserOrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserOrderModel.UserOrder> __insertionAdapterOfUserOrder;

  private final EntityInsertionAdapter<UserOrderModel.MealListBean> __insertionAdapterOfMealListBean;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUserOrder;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUserOrderMealList;

  public UserOrderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserOrder = new EntityInsertionAdapter<UserOrderModel.UserOrder>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user_order` (`id`,`today_date`,`company_name`,`company_contact`,`company_localisation`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserOrderModel.UserOrder value) {
        stmt.bindLong(1, value.id);
        if (value.todayDate == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.todayDate);
        }
        if (value.companyName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.companyName);
        }
        if (value.companyContact == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.companyContact);
        }
        if (value.companyLocalisation == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.companyLocalisation);
        }
      }
    };
    this.__insertionAdapterOfMealListBean = new EntityInsertionAdapter<UserOrderModel.MealListBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user_meal_list` (`id`,`id_list`,`nom_nourriture`,`prix_nourriture`,`image_nourriture`,`quantity_nourriture`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserOrderModel.MealListBean value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.idList);
        if (value.nomDeLaBouf == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.nomDeLaBouf);
        }
        if (value.prixUnitaireDeLaBouf == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.prixUnitaireDeLaBouf);
        }
        if (value.imageDeLaBouf == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.imageDeLaBouf);
        }
        if (value.quantiteDeLaBouf == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.quantiteDeLaBouf);
        }
      }
    };
    this.__preparedStmtOfDeleteAllUserOrder = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user_order";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllUserOrderMealList = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user_meal_list";
        return _query;
      }
    };
  }

  @Override
  public Completable saveUserOrder(final UserOrderModel.UserOrder userOrder) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserOrder.insert(userOrder);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable saveUserListOrder(final List<UserOrderModel.MealListBean> listUserOrderMeal) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMealListBean.insert(listUserOrderMeal);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable deleteAllUserOrder() {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUserOrder.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllUserOrder.release(_stmt);
        }
      }
    });
  }

  @Override
  public Completable deleteAllUserOrderMealList() {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUserOrderMealList.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllUserOrderMealList.release(_stmt);
        }
      }
    });
  }

  @Override
  public Single<UserOrderModel.UserOrder> getUserOrderInfo() {
    final String _sql = "SELECT * FROM user_order";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createSingle(new Callable<UserOrderModel.UserOrder>() {
      @Override
      public UserOrderModel.UserOrder call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTodayDate = CursorUtil.getColumnIndexOrThrow(_cursor, "today_date");
          final int _cursorIndexOfCompanyName = CursorUtil.getColumnIndexOrThrow(_cursor, "company_name");
          final int _cursorIndexOfCompanyContact = CursorUtil.getColumnIndexOrThrow(_cursor, "company_contact");
          final int _cursorIndexOfCompanyLocalisation = CursorUtil.getColumnIndexOrThrow(_cursor, "company_localisation");
          final UserOrderModel.UserOrder _result;
          if(_cursor.moveToFirst()) {
            _result = new UserOrderModel.UserOrder();
            _result.id = _cursor.getInt(_cursorIndexOfId);
            _result.todayDate = _cursor.getString(_cursorIndexOfTodayDate);
            _result.companyName = _cursor.getString(_cursorIndexOfCompanyName);
            _result.companyContact = _cursor.getString(_cursorIndexOfCompanyContact);
            _result.companyLocalisation = _cursor.getString(_cursorIndexOfCompanyLocalisation);
          } else {
            _result = null;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<UserOrderModel.MealListBean>> getUserListMeal(final int id) {
    final String _sql = "SELECT * FROM user_meal_list WHERE id_list = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return RxRoom.createFlowable(__db, false, new String[]{"user_meal_list"}, new Callable<List<UserOrderModel.MealListBean>>() {
      @Override
      public List<UserOrderModel.MealListBean> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdList = CursorUtil.getColumnIndexOrThrow(_cursor, "id_list");
          final int _cursorIndexOfNomDeLaBouf = CursorUtil.getColumnIndexOrThrow(_cursor, "nom_nourriture");
          final int _cursorIndexOfPrixUnitaireDeLaBouf = CursorUtil.getColumnIndexOrThrow(_cursor, "prix_nourriture");
          final int _cursorIndexOfImageDeLaBouf = CursorUtil.getColumnIndexOrThrow(_cursor, "image_nourriture");
          final int _cursorIndexOfQuantiteDeLaBouf = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity_nourriture");
          final List<UserOrderModel.MealListBean> _result = new ArrayList<UserOrderModel.MealListBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserOrderModel.MealListBean _item;
            _item = new UserOrderModel.MealListBean();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.idList = _cursor.getInt(_cursorIndexOfIdList);
            _item.nomDeLaBouf = _cursor.getString(_cursorIndexOfNomDeLaBouf);
            _item.prixUnitaireDeLaBouf = _cursor.getString(_cursorIndexOfPrixUnitaireDeLaBouf);
            _item.imageDeLaBouf = _cursor.getString(_cursorIndexOfImageDeLaBouf);
            _item.quantiteDeLaBouf = _cursor.getString(_cursorIndexOfQuantiteDeLaBouf);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
