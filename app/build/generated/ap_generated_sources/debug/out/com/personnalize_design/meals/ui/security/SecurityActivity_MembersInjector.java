// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.security;

import android.content.Context;
import com.personnalize_design.meals.ui.base.BaseActivity_MembersInjector;
import com.personnalize_design.meals.ui.security.presenter.SecurityPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SecurityActivity_MembersInjector implements MembersInjector<SecurityActivity> {
  private final Provider<Context> contextProvider;

  private final Provider<SecurityPresenter<SecurityActivity>> mPresenterProvider;

  public SecurityActivity_MembersInjector(Provider<Context> contextProvider,
      Provider<SecurityPresenter<SecurityActivity>> mPresenterProvider) {
    this.contextProvider = contextProvider;
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<SecurityActivity> create(Provider<Context> contextProvider,
      Provider<SecurityPresenter<SecurityActivity>> mPresenterProvider) {
    return new SecurityActivity_MembersInjector(contextProvider, mPresenterProvider);}

  @Override
  public void injectMembers(SecurityActivity instance) {
    BaseActivity_MembersInjector.injectContext(instance, contextProvider.get());
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static void injectMPresenter(SecurityActivity instance,
      SecurityPresenter<SecurityActivity> mPresenter) {
    instance.mPresenter = mPresenter;
  }
}