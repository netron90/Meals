package com.personnalize_design.meals.data.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MealsDatabase_Impl extends MealsDatabase {
  private volatile UserOrderDao _userOrderDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_order` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `today_date` TEXT, `company_name` TEXT, `company_contact` TEXT, `company_localisation` TEXT, `current_meal_order_time` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_meal_list` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id_list` INTEGER NOT NULL, `nom_nourriture` TEXT, `prix_nourriture` TEXT, `image_nourriture` TEXT, `quantity_nourriture` TEXT, FOREIGN KEY(`id_list`) REFERENCES `user_order`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd4b9d58f058179addf796a118b68b28f')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `user_order`");
        _db.execSQL("DROP TABLE IF EXISTS `user_meal_list`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUserOrder = new HashMap<String, TableInfo.Column>(6);
        _columnsUserOrder.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserOrder.put("today_date", new TableInfo.Column("today_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserOrder.put("company_name", new TableInfo.Column("company_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserOrder.put("company_contact", new TableInfo.Column("company_contact", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserOrder.put("company_localisation", new TableInfo.Column("company_localisation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserOrder.put("current_meal_order_time", new TableInfo.Column("current_meal_order_time", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserOrder = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserOrder = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserOrder = new TableInfo("user_order", _columnsUserOrder, _foreignKeysUserOrder, _indicesUserOrder);
        final TableInfo _existingUserOrder = TableInfo.read(_db, "user_order");
        if (! _infoUserOrder.equals(_existingUserOrder)) {
          return new RoomOpenHelper.ValidationResult(false, "user_order(com.personnalize_design.meals.data.model.UserOrderModel.UserOrder).\n"
                  + " Expected:\n" + _infoUserOrder + "\n"
                  + " Found:\n" + _existingUserOrder);
        }
        final HashMap<String, TableInfo.Column> _columnsUserMealList = new HashMap<String, TableInfo.Column>(6);
        _columnsUserMealList.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserMealList.put("id_list", new TableInfo.Column("id_list", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserMealList.put("nom_nourriture", new TableInfo.Column("nom_nourriture", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserMealList.put("prix_nourriture", new TableInfo.Column("prix_nourriture", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserMealList.put("image_nourriture", new TableInfo.Column("image_nourriture", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserMealList.put("quantity_nourriture", new TableInfo.Column("quantity_nourriture", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserMealList = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysUserMealList.add(new TableInfo.ForeignKey("user_order", "NO ACTION", "NO ACTION",Arrays.asList("id_list"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesUserMealList = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserMealList = new TableInfo("user_meal_list", _columnsUserMealList, _foreignKeysUserMealList, _indicesUserMealList);
        final TableInfo _existingUserMealList = TableInfo.read(_db, "user_meal_list");
        if (! _infoUserMealList.equals(_existingUserMealList)) {
          return new RoomOpenHelper.ValidationResult(false, "user_meal_list(com.personnalize_design.meals.data.model.UserOrderModel.MealListBean).\n"
                  + " Expected:\n" + _infoUserMealList + "\n"
                  + " Found:\n" + _existingUserMealList);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d4b9d58f058179addf796a118b68b28f", "f04601d3017cf0122e7e0d6d2b342f4f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_order","user_meal_list");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `user_meal_list`");
      _db.execSQL("DELETE FROM `user_order`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UserOrderDao userOrderDao() {
    if (_userOrderDao != null) {
      return _userOrderDao;
    } else {
      synchronized(this) {
        if(_userOrderDao == null) {
          _userOrderDao = new UserOrderDao_Impl(this);
        }
        return _userOrderDao;
      }
    }
  }
}
