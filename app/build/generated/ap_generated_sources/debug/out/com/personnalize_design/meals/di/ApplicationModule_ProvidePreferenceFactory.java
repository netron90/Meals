// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.di;

import android.content.Context;
import com.personnalize_design.meals.data.preferences.PreferenceHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvidePreferenceFactory implements Factory<PreferenceHelper> {
  private final ApplicationModule module;

  private final Provider<Context> contextProvider;

  public ApplicationModule_ProvidePreferenceFactory(ApplicationModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public PreferenceHelper get() {
    return providePreference(module, contextProvider.get());
  }

  public static ApplicationModule_ProvidePreferenceFactory create(ApplicationModule module,
      Provider<Context> contextProvider) {
    return new ApplicationModule_ProvidePreferenceFactory(module, contextProvider);
  }

  public static PreferenceHelper providePreference(ApplicationModule instance, Context context) {
    return Preconditions.checkNotNull(instance.providePreference(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}