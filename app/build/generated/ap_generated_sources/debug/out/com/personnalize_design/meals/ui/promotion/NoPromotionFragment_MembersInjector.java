// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.promotion;

import com.personnalize_design.meals.ui.promotion.presenter.NoPromotionPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NoPromotionFragment_MembersInjector implements MembersInjector<NoPromotionFragment> {
  private final Provider<NoPromotionPresenter<NoPromotionFragment>> mPresenterProvider;

  public NoPromotionFragment_MembersInjector(
      Provider<NoPromotionPresenter<NoPromotionFragment>> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<NoPromotionFragment> create(
      Provider<NoPromotionPresenter<NoPromotionFragment>> mPresenterProvider) {
    return new NoPromotionFragment_MembersInjector(mPresenterProvider);}

  @Override
  public void injectMembers(NoPromotionFragment instance) {
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static void injectMPresenter(NoPromotionFragment instance,
      NoPromotionPresenter<NoPromotionFragment> mPresenter) {
    instance.mPresenter = mPresenter;
  }
}
