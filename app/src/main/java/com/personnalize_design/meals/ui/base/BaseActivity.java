package com.personnalize_design.meals.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.MealFacture;
import com.personnalize_design.meals.di.App;
import com.personnalize_design.meals.di.ApplicationComponent;
import com.personnalize_design.meals.ui.menu_quantity.MenuQuantityActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.Unbinder;


public class BaseActivity extends AppCompatActivity implements MvpView {

    private ProgressBar progressBar;
    private FragmentManager fragmentManager;
    private Fragment mFragment;
    private Unbinder mUnbinder;
    private AlertDialog alertDialog;
    private int defaultTextColor;
//    private TrainerChoices trainerChoices;
//    private GetTrainerName trainerName;

    @Inject
    public Context context;

    @Override
    public void onCreate ( Bundle savedInstanceState , PersistableBundle persistentState ) {
        super.onCreate (savedInstanceState , persistentState);

        ((App)getApplication()).getmComponent().inject(this);
    }

    @Override
    public void showProgressBar ( View v ) {
        if(v instanceof ProgressBar && v != null)
        {
            v.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar ( View v ) {
        if(v instanceof ProgressBar && v != null)
        {
            v.setVisibility(View.GONE);
        }
    }


    @Override
    public void showToast ( Context context , String message ) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToast ( Context context , String message ) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDiaologBox (Context context, String title , String message ) {



        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    if(context instanceof MenuQuantityActivity){
                        if(title.equals(context.getResources().getString(R.string.order_faillure_sent))){
                            OnGoToUserOrder mListener = (MenuQuantityActivity) context;
                            mListener.onGoToUserOrder(0);
                        }else{
                            OnGoToUserOrder mListener = (MenuQuantityActivity) context;
                            mListener.onGoToUserOrder(1);
                        }

                    }
                }).show();
    }

    @Override
    public void showConfirmDiaologBox ( Context ctn, String title , String message ) {

        new AlertDialog.Builder(ctn)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", ( dialogInterface, i) -> {


                }).show();
    }

    @Override
    public void showBialnMealCustomDialogBox(Context ctn, String companyDeliveryPrice, String totalMealsPrices, String companyName, List<MainMealSelectedModel> listMealSelected, ArrayList<String> mealQuantity) {
        View view = LayoutInflater.from(ctn).inflate(R.layout.biling_dialog_box, null);
        TextView totalMealsPrice = (TextView) view.findViewById(R.id.totalMealsPrice);
        totalMealsPrice.setText("TOTAL: " + totalMealsPrices + "f CFA");
        TextView companyPriceLivraison = (TextView)  view.findViewById(R.id.livraisonPrice);
        companyPriceLivraison.setText(companyDeliveryPrice + "f CFA");
        AppCompatCheckBox isDelivery = (AppCompatCheckBox) view.findViewById(R.id.checkboxDelivery);
        TextView clientName = (TextView)  view.findViewById(R.id.clientName);
        TextView clientContact = (TextView)  view.findViewById(R.id.clientContact);
        TextView clientLocalisation = (TextView)  view.findViewById(R.id.clientLocation);
        LinearLayout contactForm = (LinearLayout) view.findViewById(R.id.clientContactForm);
        LinearLayout deliveryNotAvailable = (LinearLayout) view.findViewById(R.id.deliveryNotAvailable);
//        Button startMealRequestBtn = (Button) view.findViewById(R.id.startMealRequestBtn);
        ArrayList<MealFacture> mealFactureList = new ArrayList<>();
        for(int i = 0; i < listMealSelected.size(); i++){
            mealFactureList.add(new MealFacture(listMealSelected.get(i).getMainMealImage(), listMealSelected.get(i).getMainMealName(),
                    listMealSelected.get(i).getMainMealPrice(), mealQuantity.get(i)));
        }
        isDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean buttonCheckedState) {
                if(!buttonCheckedState){
                    companyPriceLivraison.setEnabled(false);
                    contactForm.setVisibility(View.GONE);
                    deliveryNotAvailable.setVisibility(View.VISIBLE);
                    if(ctn instanceof MenuQuantityActivity){
                        OnSaveDeliveryState listener = (MenuQuantityActivity) ctn;
                        listener.onSaveDeliveryState(false);
                    }
                }else{
                    companyPriceLivraison.setEnabled(true);
                    contactForm.setVisibility(View.VISIBLE);
                    deliveryNotAvailable.setVisibility(View.GONE);
                    if(ctn instanceof MenuQuantityActivity){
                        OnSaveDeliveryState listener = (MenuQuantityActivity) ctn;
                        listener.onSaveDeliveryState(true);
                    }
                }
            }
        });



        new AlertDialog.Builder(ctn)
                .setView(view)
                .setPositiveButton(getString(R.string.start_my_order), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("CONTACT FORM INFO", "Total Meals Price: " + totalMealsPrices + " \n " +
                                "Company Delivery Price: " + companyDeliveryPrice + " \n Client Name: " +clientName.getText().toString() + " \n" +
                                " Client Contact: " + clientContact.getText().toString() + " \n Client Localisation: "
                                + clientLocalisation.getText().toString() + " \n Company Name: " + companyName);


                        if(ctn instanceof MenuQuantityActivity){
                            OnSendUserOrder onSendUserOrderVar = (MenuQuantityActivity) ctn;
                            if(isDelivery.isChecked()){
                                if(TextUtils.isEmpty(clientName.getText().toString()) ||
                                        TextUtils.isEmpty(clientContact.getText().toString()) ||
                                        TextUtils.isEmpty(clientLocalisation.getText().toString())){
                                    showLongToast(ctn, getString(R.string.missing_fields));
                                }else{
                                    onSendUserOrderVar.onSendUserOrder(ctn, totalMealsPrices, companyName, clientName.getText().toString(), clientContact.getText().toString(), clientLocalisation.getText().toString(), mealFactureList);
                                }
                            }else{
                                onSendUserOrderVar.onSendUserOrder(ctn, totalMealsPrices, companyName, clientName.getText().toString(), clientContact.getText().toString(), clientLocalisation.getText().toString(), mealFactureList);
                            }

                        }
                    }
                })
                .show();



    }

    @Override
    public void showSnackBar(View view, String message, int timeToBeAvailable) {
        Snackbar.make(view, message, timeToBeAvailable).show();
    }


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
    public void showDiaologBoxWithClosingApp ( String title , String message ) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    finish();
                }).show();
    }

    @Override
    public void startActivity ( Context context , Class destination ) {
        Intent intent = new Intent(context, destination);
        startActivity (intent);
    }

    @Override
    public void changeFragment ( Class fragment ) {
        try {
            mFragment = (Fragment) fragment.newInstance();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeFragment ( Fragment fragment) {
        mFragment = fragment;
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
    }

    @Override
    public void changeFragment ( Fragment fragment, String companyName ) {
        mFragment = fragment;
        Bundle bundle = new Bundle();
        bundle.putString("companyName", companyName);
        mFragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
    }

    @Override
    public void changeFragment(Class fragment, String fragmentParam) {

//        try {
//
//            if(mFragment instanceof DayMenuFragmentSearch)
//            {
//                mFragment = DayMenuFragmentSearch.newInstance(fragmentParam);
//                fragmentManager = getSupportFragmentManager();
//                Log.d("BASE FRAGMENT", "Instance of LoginForm");
//                fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//            }
//            else {
//                mFragment = (Fragment) fragment.newInstance();
//                Bundle b = new Bundle();
//                b.putString("param", fragmentParam);
//                mFragment.setArguments(b);
//                fragmentManager = getSupportFragmentManager();
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
//        try {
//            mFragment = (Fragment) fragment.newInstance("l", "k");
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void changeFragment ( Fragment fragment , RelativeLayout container ) {
//        if(container.getVisibility () == View.VISIBLE)
//        {
//            mFragment = fragment;
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//        }else{
//            mFragment = fragment;
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.container_normal, mFragment).commit();
//        }
    }

    @Override
    public void setUnbinder ( Unbinder unbinder ) {
        this.mUnbinder = unbinder;
    }

    @Override
    public boolean isNetworkAvailable ( ) {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onConnectionError ( ) {
        showLongToast(getContext(), getString(R.string.connexion_available));
    }

    @Override
    public ApplicationComponent getComponent ( ) {
        return ((App)getApplication()).getmComponent();
    }

    public Context getContext(){
        return this.context;
    }


    public interface OnSendUserOrder{
        void onSendUserOrder(Context ctn, String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealSelected);
    }

    public interface OnGoToUserOrder{
        void onGoToUserOrder(int fragmentState);
    }

    public interface OnSaveDeliveryState{
        void onSaveDeliveryState(boolean deliveryState);
    }

}
