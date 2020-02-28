package com.personnalize_design.meals.di;

import android.app.Application;
import android.content.Context;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.personnalize_design.meals.data.AppDataManager;
import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.db.AppDbHelper;
import com.personnalize_design.meals.data.db.DbHelper;
import com.personnalize_design.meals.data.db.MealsDatabase;
import com.personnalize_design.meals.data.network.ApiHelper;
import com.personnalize_design.meals.data.network.AppApiHelper;
import com.personnalize_design.meals.data.preferences.AppPreferenceHelper;
import com.personnalize_design.meals.data.preferences.PreferenceHelper;
import com.personnalize_design.meals.ui.base.BasePresenter;
import com.personnalize_design.meals.ui.base.MvpPresenter;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return this.mApplication.getApplicationContext();
    }


    @Provides
    public MvpPresenter provideBasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable){
        return new BasePresenter(dataManager, compositeDisposable);
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }


    @Provides
    public DataManager provideDataManager(Context context, ApiHelper apiHelper, PreferenceHelper preferenceHelper, DbHelper dbHelper){
        return new AppDataManager(context, preferenceHelper, apiHelper, dbHelper);
    }
//

    @Provides
    public OkHttpClient provideHttpClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public ApiHelper provideApiHelper(){
        return new AppApiHelper(provideRetrofit(BASE_URL, provideHttpClient()), providePreference(provideContext()));
    }

    @Provides
    public PreferenceHelper providePreference(Context context){
        return new AppPreferenceHelper(context);
    }


    @Provides
    @Singleton
    public DbHelper provideAppDbHelper(MealsDatabase db)
    {
        return new AppDbHelper(db);
    }

    @Provides
    @Singleton
    public MealsDatabase provideRoomDatabase(Context context){
        return Room.databaseBuilder(context, MealsDatabase.class, "meals")
                .build();
    }
}

