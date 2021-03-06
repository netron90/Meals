// Generated by Dagger (https://dagger.dev).
package com.personnalize_design.meals.data.network;

import com.personnalize_design.meals.data.preferences.PreferenceHelper;
import dagger.internal.Factory;
import javax.inject.Provider;
import retrofit2.Retrofit;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppApiHelper_Factory implements Factory<AppApiHelper> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<PreferenceHelper> mPrefProvider;

  public AppApiHelper_Factory(Provider<Retrofit> retrofitProvider,
      Provider<PreferenceHelper> mPrefProvider) {
    this.retrofitProvider = retrofitProvider;
    this.mPrefProvider = mPrefProvider;
  }

  @Override
  public AppApiHelper get() {
    return new AppApiHelper(retrofitProvider.get(), mPrefProvider.get());
  }

  public static AppApiHelper_Factory create(Provider<Retrofit> retrofitProvider,
      Provider<PreferenceHelper> mPrefProvider) {
    return new AppApiHelper_Factory(retrofitProvider, mPrefProvider);
  }

  public static AppApiHelper newInstance(Retrofit retrofit, PreferenceHelper mPref) {
    return new AppApiHelper(retrofit, mPref);
  }
}
