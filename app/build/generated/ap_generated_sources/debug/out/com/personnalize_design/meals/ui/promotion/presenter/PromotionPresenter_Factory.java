// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.promotion.presenter;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.promotion.interfaces.PromotionMvpView;
import dagger.internal.Factory;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PromotionPresenter_Factory<V extends PromotionMvpView> implements Factory<PromotionPresenter<V>> {
  private final Provider<DataManager> dataManagerProvider;

  private final Provider<CompositeDisposable> compositeDisposableProvider;

  public PromotionPresenter_Factory(Provider<DataManager> dataManagerProvider,
      Provider<CompositeDisposable> compositeDisposableProvider) {
    this.dataManagerProvider = dataManagerProvider;
    this.compositeDisposableProvider = compositeDisposableProvider;
  }

  @Override
  public PromotionPresenter<V> get() {
    return new PromotionPresenter<V>(dataManagerProvider.get(), compositeDisposableProvider.get());
  }

  public static <V extends PromotionMvpView> PromotionPresenter_Factory<V> create(
      Provider<DataManager> dataManagerProvider,
      Provider<CompositeDisposable> compositeDisposableProvider) {
    return new PromotionPresenter_Factory<V>(dataManagerProvider, compositeDisposableProvider);
  }

  public static <V extends PromotionMvpView> PromotionPresenter<V> newInstance(
      DataManager dataManager, CompositeDisposable compositeDisposable) {
    return new PromotionPresenter<V>(dataManager, compositeDisposable);
  }
}
