// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.suggestion.presenters;

import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.ui.suggestion.interfaces.SuggestionMvpView;
import dagger.internal.Factory;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SuggestionPresenter_Factory<V extends SuggestionMvpView> implements Factory<SuggestionPresenter<V>> {
  private final Provider<DataManager> dataManagerProvider;

  private final Provider<CompositeDisposable> compositeDisposableProvider;

  public SuggestionPresenter_Factory(Provider<DataManager> dataManagerProvider,
      Provider<CompositeDisposable> compositeDisposableProvider) {
    this.dataManagerProvider = dataManagerProvider;
    this.compositeDisposableProvider = compositeDisposableProvider;
  }

  @Override
  public SuggestionPresenter<V> get() {
    return new SuggestionPresenter<V>(dataManagerProvider.get(), compositeDisposableProvider.get());
  }

  public static <V extends SuggestionMvpView> SuggestionPresenter_Factory<V> create(
      Provider<DataManager> dataManagerProvider,
      Provider<CompositeDisposable> compositeDisposableProvider) {
    return new SuggestionPresenter_Factory<V>(dataManagerProvider, compositeDisposableProvider);
  }

  public static <V extends SuggestionMvpView> SuggestionPresenter<V> newInstance(
      DataManager dataManager, CompositeDisposable compositeDisposable) {
    return new SuggestionPresenter<V>(dataManager, compositeDisposable);
  }
}