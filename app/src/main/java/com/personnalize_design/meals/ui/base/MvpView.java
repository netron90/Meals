package com.personnalize_design.meals.ui.base;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;


import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.di.ApplicationComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import androidx.fragment.app.Fragment;
import butterknife.Unbinder;

public interface MvpView {

    void showProgressBar(View v);

    void hideProgressBar( View v);

    void showToast( Context context, String message);

    void showLongToast(Context context, String message);

    void showDiaologBox(Context context, String title, String message);

    void showConfirmDiaologBox( Context ctn, String title, String message, Callable<Void> action, Callable<Void> action2 );

    void showBialnMealCustomDialogBox(Context ctn, String companyDeliveryPrice, String totalMealPrice, String companyName, List<MainMealSelectedModel> listMealsSelected, ArrayList<String> mealQuantity);

//    void profChenMessageNext(Context ctn, String title, String message, TrainerChoices trainerChoices);
//
//    void profChenTrainerName( Context ctn, String title);

    void showConfirmDiaologBoxClosing( String title, String message, Callable<Void> action );

    void showDiaologBoxWithClosingApp(String title, String message);

    void startActivity(Context context, Class destination);

    void changeFragment(Class fragment);

    void changeFragment( Fragment fragment);
    void changeFragment( Fragment fragment, String companyName);

    void changeFragment( Class fragment, String fragmentParam);

    void changeFragment(Fragment fragment, RelativeLayout container);

    void setUnbinder( Unbinder unbinder);

    boolean isNetworkAvailable();

    void onConnectionError();

    ApplicationComponent getComponent();

}
