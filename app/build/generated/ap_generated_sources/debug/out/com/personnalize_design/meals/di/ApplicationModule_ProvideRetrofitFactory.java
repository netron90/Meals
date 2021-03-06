// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final ApplicationModule module;

  private final Provider<String> baseUrlProvider;

  private final Provider<OkHttpClient> clientProvider;

  public ApplicationModule_ProvideRetrofitFactory(ApplicationModule module,
      Provider<String> baseUrlProvider, Provider<OkHttpClient> clientProvider) {
    this.module = module;
    this.baseUrlProvider = baseUrlProvider;
    this.clientProvider = clientProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofit(module, baseUrlProvider.get(), clientProvider.get());
  }

  public static ApplicationModule_ProvideRetrofitFactory create(ApplicationModule module,
      Provider<String> baseUrlProvider, Provider<OkHttpClient> clientProvider) {
    return new ApplicationModule_ProvideRetrofitFactory(module, baseUrlProvider, clientProvider);
  }

  public static Retrofit provideRetrofit(ApplicationModule instance, String baseUrl,
      OkHttpClient client) {
    return Preconditions.checkNotNull(instance.provideRetrofit(baseUrl, client), "Cannot return null from a non-@Nullable @Provides method");
  }
}
