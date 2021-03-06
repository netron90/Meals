// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.services;

import com.personnalize_design.meals.data.DataManager;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NotifyUserService_MembersInjector implements MembersInjector<NotifyUserService> {
  private final Provider<DataManager> mDataManagerProvider;

  public NotifyUserService_MembersInjector(Provider<DataManager> mDataManagerProvider) {
    this.mDataManagerProvider = mDataManagerProvider;
  }

  public static MembersInjector<NotifyUserService> create(
      Provider<DataManager> mDataManagerProvider) {
    return new NotifyUserService_MembersInjector(mDataManagerProvider);}

  @Override
  public void injectMembers(NotifyUserService instance) {
    injectMDataManager(instance, mDataManagerProvider.get());
  }

  public static void injectMDataManager(NotifyUserService instance, DataManager mDataManager) {
    instance.mDataManager = mDataManager;
  }
}
