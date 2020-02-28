// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.data.db;

import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppDbHelper_Factory implements Factory<AppDbHelper> {
  private final Provider<MealsDatabase> dbProvider;

  public AppDbHelper_Factory(Provider<MealsDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AppDbHelper get() {
    return new AppDbHelper(dbProvider.get());
  }

  public static AppDbHelper_Factory create(Provider<MealsDatabase> dbProvider) {
    return new AppDbHelper_Factory(dbProvider);
  }

  public static AppDbHelper newInstance(MealsDatabase db) {
    return new AppDbHelper(db);
  }
}