// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.ui.promotion;

import com.personnalize_design.meals.ui.promotion.presenter.PromotionPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PromotionFragment_MembersInjector implements MembersInjector<PromotionFragment> {
  private final Provider<PromotionPresenter<PromotionFragment>> mPresenterProvider;

  public PromotionFragment_MembersInjector(
      Provider<PromotionPresenter<PromotionFragment>> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<PromotionFragment> create(
      Provider<PromotionPresenter<PromotionFragment>> mPresenterProvider) {
    return new PromotionFragment_MembersInjector(mPresenterProvider);}

  @Override
  public void injectMembers(PromotionFragment instance) {
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static void injectMPresenter(PromotionFragment instance,
      PromotionPresenter<PromotionFragment> mPresenter) {
    instance.mPresenter = mPresenter;
  }
}
