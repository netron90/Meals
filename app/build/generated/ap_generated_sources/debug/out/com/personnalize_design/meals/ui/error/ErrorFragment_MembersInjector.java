// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.error;

import com.personnalize_design.meals.ui.error.presenter.ErrorFragmentPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ErrorFragment_MembersInjector implements MembersInjector<ErrorFragment> {
  private final Provider<ErrorFragmentPresenter<ErrorFragment>> mPresenterProvider;

  public ErrorFragment_MembersInjector(
      Provider<ErrorFragmentPresenter<ErrorFragment>> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<ErrorFragment> create(
      Provider<ErrorFragmentPresenter<ErrorFragment>> mPresenterProvider) {
    return new ErrorFragment_MembersInjector(mPresenterProvider);}

  @Override
  public void injectMembers(ErrorFragment instance) {
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static void injectMPresenter(ErrorFragment instance,
      ErrorFragmentPresenter<ErrorFragment> mPresenter) {
    instance.mPresenter = mPresenter;
  }
}