// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.day_menu;

import com.personnalize_design.meals.ui.day_menu.presenter.NoFactoryErrorPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NoFactoryErrorFragment_MembersInjector implements MembersInjector<NoFactoryErrorFragment> {
  private final Provider<NoFactoryErrorPresenter<NoFactoryErrorFragment>> mPresenterProvider;

  public NoFactoryErrorFragment_MembersInjector(
      Provider<NoFactoryErrorPresenter<NoFactoryErrorFragment>> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<NoFactoryErrorFragment> create(
      Provider<NoFactoryErrorPresenter<NoFactoryErrorFragment>> mPresenterProvider) {
    return new NoFactoryErrorFragment_MembersInjector(mPresenterProvider);}

  @Override
  public void injectMembers(NoFactoryErrorFragment instance) {
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static void injectMPresenter(NoFactoryErrorFragment instance,
      NoFactoryErrorPresenter<NoFactoryErrorFragment> mPresenter) {
    instance.mPresenter = mPresenter;
  }
}
