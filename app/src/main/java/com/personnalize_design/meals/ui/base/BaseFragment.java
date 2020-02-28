package com.personnalize_design.meals.ui.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.di.App;
import com.personnalize_design.meals.di.ApplicationComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.Unbinder;


public class BaseFragment extends Fragment implements MvpView {

    private ProgressBar progressBar;
    private FragmentManager fragmentManager;
    private Fragment mFragment;
    private Unbinder mUnbinder;
    private AlertDialog alertDialog;
//    private TrainerChoices trainerChoices;
//    private GetTrainerName trainerName;


    @Override
    public void showProgressBar(View v) {
        if(v instanceof ProgressBar && v != null)
        {
            v.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar(View v) {
        if(v instanceof ProgressBar && v != null)
        {
            v.setVisibility(View.GONE);
        }
    }


    @Override
    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDiaologBox(Context context, String title, String message) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {

                }).show();
    }

    @Override
    public void showConfirmDiaologBox (Context ctn,  String title , String message , Callable<Void> action, Callable<Void> action2 ) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString (R.string.yes_action), (dialogInterface, i) -> {
                    try {
                        action.call ();
                    } catch (Exception e) {
                        e.printStackTrace ();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no_action), (dialogInterface, i) -> {
                    try {
                        action2.call ();
                    } catch (Exception e) {
                        e.printStackTrace ();
                    }

                }).show();
    }

    @Override
    public void showBialnMealCustomDialogBox(Context ctn, String companyDeliveryPrice, String totalMealPrices, String companyName, List<MainMealSelectedModel> listMealsSelected, ArrayList<String> mealQuantity) {
        View view = LayoutInflater.from(ctn).inflate(R.layout.biling_dialog_box, null);
        TextView totalMealsPrice = (TextView) view.findViewById(R.id.totalMealsPrice);
        totalMealsPrice.setText("TOTAL: " + totalMealPrices + "f CFA");
        TextView companyPriceLivraison = (TextView)  view.findViewById(R.id.livraisonPrice);
        companyPriceLivraison.setText(companyDeliveryPrice);
        AppCompatCheckBox isDelivery = (AppCompatCheckBox) view.findViewById(R.id.checkboxDelivery);
        TextView clientName = (TextView)  view.findViewById(R.id.clientName);
        TextView clientContact = (TextView)  view.findViewById(R.id.clientContact);
        TextView clientLocalisation = (TextView)  view.findViewById(R.id.clientLocation);
        LinearLayout contactForm = (LinearLayout) view.findViewById(R.id.clientContactForm);
//        Button startMealRequestBtn = (Button) view.findViewById(R.id.startMealRequestBtn);
        isDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean buttonCheckedState) {
                if(!buttonCheckedState){
                    companyPriceLivraison.setEnabled(false);
                    contactForm.setVisibility(View.GONE);
                }else{
                    companyPriceLivraison.setEnabled(true);
                    contactForm.setVisibility(View.VISIBLE);
                }
            }
        });

//        startMealRequestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("CONTACT FORM INFO", "Total Meals Price: " + totalMealPrices + " \n " +
//                        "Company Delivery Price: " + companyDeliveryPrice + " \n Client Name: " +clientName.getText().toString() + " \n" +
//                        " Client Contact: " + clientContact.getText().toString() + " \n Client Localisation: "
//                        + clientLocalisation.getText().toString());
//            }
//        });

        new AlertDialog.Builder(ctn)
                .setView(view)
                .show();
    }

//    @Override
//    public void showConfirmDiaologBoxNext(Context ctn, String title, String message, Callable<Void> action, Callable<Void> action2) {
//        alertDialog = new AlertDialog.Builder(getContext())
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(getString (R.string.next_action), (dialogInterface, i) -> {
//                    try {
//                        action.call ();
//                    } catch (Exception e) {
//                        e.printStackTrace ();
//                    }
//                })
//                .setNegativeButton(getResources().getString(R.string.no_action), (dialogInterface, i) -> {
//                    try {
//                        action2.call ();
//                    } catch (Exception e) {
//                        e.printStackTrace ();
//                    }
//
//                }).show();
//    }


    @Override
    public void showConfirmDiaologBoxClosing ( String title , String message , Callable<Void> action ) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    try {
                        action.call ();
                    } catch (Exception e) {
                        e.printStackTrace ();
                    }
                });

    }

    @Override
    public void showDiaologBoxWithClosingApp(String title, String message) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    getActivity().finish();
                }).show();
    }

    @Override
    public void startActivity(Context context, Class destination) {
        Intent intent = new Intent();
        intent.setClass(context, destination);
        startActivity(intent);
    }

    @Override
    public void changeFragment(Class fragment) {

        try {
            mFragment = (Fragment) fragment.newInstance();

//            if(mFragment instanceof LoginFormFragment)
//            {
//                fragmentManager = getFragmentManager();
//                Log.d("BASE FRAGMENT", "Instance of LoginForm");
//                fragmentManager.beginTransaction().addToBackStack(LoginFragment.class.getName())
//                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.container, mFragment).commit();
//            }
//            else {
//                fragmentManager = getFragmentManager();
//                Log.d("BASE FRAGMENT", "not Instance of LoginForm");
//                fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//
//            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void changeFragment ( Fragment fragment) {
        mFragment = fragment;
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
    }

    @Override
    public void changeFragment ( Fragment fragment, String companyName ) {

        mFragment = fragment;
        Bundle bundle = new Bundle();
        bundle.putString("companyName", companyName);
        mFragment.setArguments(bundle);
        fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.container_coupon, mFragment).commit();
//        if(mFragment instanceof LoginFormFragment)
//        {
//            Log.d("BASE FRAGMENT", "Instance of LoginForm");
//            fragmentManager.beginTransaction().addToBackStack(LoginFragment.class.getName())
//                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.container, mFragment).commit();
//        }
//        else {
//            Log.d("BASE FRAGMENT", "not Instance of LoginForm");
//            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//
//        }


    }



    @Override
    public void changeFragment(Class fragment, String fragmentParam) {
//        try {
//            mFragment = (Fragment) fragment.newInstance();
//
//            if(mFragment instanceof DayMenuFragmentSearch)
//            {
//                mFragment = DayMenuFragmentSearch.newInstance(fragmentParam);
//                fragmentManager = getActivity().getSupportFragmentManager();
//                Log.d("BASE FRAGMENT", "Instance of LoginForm");
//                fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//            }
//            else {
//                mFragment = (Fragment) fragment.newInstance();
//                Bundle b = new Bundle();
//                b.putString("param", fragmentParam);
//                mFragment.setArguments(b);
//                fragmentManager = getActivity().getSupportFragmentManager();
//                Log.d("BASE FRAGMENT", "not Instance of LoginForm");
//                fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//
//            }
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (java.lang.InstantiationException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void changeFragment ( Fragment fragment, RelativeLayout container ) {
//        if(container.getVisibility () == View.VISIBLE)
//        {
//            mFragment = fragment;
//            fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//        }else{
//            mFragment = fragment;
//            fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.container_normal, mFragment).commit();
//        }

    }

    @Override
    public void setUnbinder(Unbinder unbinder) {
        this.mUnbinder = unbinder;
    }

    @Override
    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onConnectionError() {
        showLongToast(getContext(), getResources().getString(R.string.connexion_available));
    }

    @Override
    public ApplicationComponent getComponent() {
        return ((App)getActivity().getApplication()).getmComponent() ;
    }

}
