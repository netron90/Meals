// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.day_menu.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.day_menu.interfaces.DayMenuMvpView;
import dagger.internal.Factory;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DayMenuPresenter_Factory<V extends DayMenuMvpView> implements Factory<DayMenuPresenter<V>> {
  private final Provider<DataManager> dataManagerProvider;

  private final Provider<CompositeDisposable> compositeDisposableProvider;

  public DayMenuPresenter_Factory(Provider<DataManager> dataManagerProvider,
      Provider<CompositeDisposable> compositeDisposableProvider) {
    this.dataManagerProvider = dataManagerProvider;
    this.compositeDisposableProvider = compositeDisposableProvider;
  }

  @Override
  public DayMenuPresenter<V> get() {
    return new DayMenuPresenter<V>(dataManagerProvider.get(), compositeDisposableProvider.get());
  }

  public static <V extends DayMenuMvpView> DayMenuPresenter_Factory<V> create(
      Provider<DataManager> dataManagerProvider,
      Provider<CompositeDisposable> compositeDisposableProvider) {
    return new DayMenuPresenter_Factory<V>(dataManagerProvider, compositeDisposableProvider);
  }

  public static <V extends DayMenuMvpView> DayMenuPresenter<V> newInstance(DataManager dataManager,
      CompositeDisposable compositeDisposable) {
    return new DayMenuPresenter<V>(dataManager, compositeDisposable);
  }
}