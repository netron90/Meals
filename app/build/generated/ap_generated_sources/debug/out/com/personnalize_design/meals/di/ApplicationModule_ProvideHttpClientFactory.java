// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvideHttpClientFactory implements Factory<OkHttpClient> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideHttpClientFactory(ApplicationModule module) {
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return provideHttpClient(module);
  }

  public static ApplicationModule_ProvideHttpClientFactory create(ApplicationModule module) {
    return new ApplicationModule_ProvideHttpClientFactory(module);
  }

  public static OkHttpClient provideHttpClient(ApplicationModule instance) {
    return Preconditions.checkNotNull(instance.provideHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
