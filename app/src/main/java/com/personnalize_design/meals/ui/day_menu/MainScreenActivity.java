package com.personnalize_design.meals.ui.day_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import hotchemi.android.rate.AppRate;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.services.CheckMealBillTimeService;
import com.personnalize_design.meals.services.CheckSuggestionTime;
import com.personnalize_design.meals.services.DeleteBillService;
import com.personnalize_design.meals.services.NotifyUserService;
import com.personnalize_design.meals.ui.catalog.CatalogFragment;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.meal_addition.interfaces.CatalogMealOrder;
import com.personnalize_design.meals.ui.promotion.NoPromotionFragment;
import com.personnalize_design.meals.ui.promotion.PromotionFragment;
import com.personnalize_design.meals.ui.search.SearchFragment;
import com.personnalize_design.meals.ui.search.adapters.SearchAdapter;
import com.personnalize_design.meals.ui.suggestion.SuggestionFragment;
import com.personnalize_design.meals.ui.user_order.NoOrderFragment;
import com.personnalize_design.meals.ui.user_order.UserOrderFragment;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.day_menu.interfaces.MainScreenMvpView;
import com.personnalize_design.meals.ui.day_menu.presenter.MainScreenPresenter;
import com.personnalize_design.meals.ui.meal_addition.AccompagnementScreen;

import javax.inject.Inject;

import static com.personnalize_design.meals.constants.Mutils.MAIN_MEAL_SELECTED_ITEM;

public class MainScreenActivity extends BaseActivity implements MainScreenMvpView,
        DayMenuFragment.OnFloatingActionListener,
        UserOrderFragment.OnFragmentInteractionListener,
        NoFactoryErrorFragment.OnFragmentInteractionListener, ErrorFragment.OnFragmentInteractionListener,
        SearchAdapter.OnCompanySelected, SearchFragment.OnFragmentInteractionListener,
        NoOrderFragment.OnFragmentInteractionListener, SuggestionFragment.OnFragmentInteractionListener,
        CatalogFragment.OnFragmentInteractionListener, CatalogMealOrder,
        PromotionFragment.OnFragmentInteractionListener, NoPromotionFragment.OnFragmentInteractionListener {

    @Inject
    MainScreenPresenter<MainScreenActivity> mPresenter;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.bottom_navigation_view)
    public BottomNavigationView bottomNavigationView;

    @BindView(R.id.rootContainer)
    public RelativeLayout relativeLayout;

    private String intentOneCompany = "";

    private String companyUserName = "";

    private String companyPhoneNumber = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    public void setUp(){
        setContentView(R.layout.activity_main_screen);

        getComponent ().inject (this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        intentOneCompany = getIntent().getStringExtra("arg_fragment_day_menu");
//        companyPhoneNumber = getIntent().getStringExtra("companyPhoneNumber");

//        mPresenter.getDataManager().setCompanyUserName(companyUserName);
//        mPresenter.getDataManager().setCompanyPhoneNumber(companyPhoneNumber);
        //rateMeDialogSetup();
        Log.d("USER FRAGMENT", "USER Fragment: " +mPresenter.getDataManager().getFragmentStateToShow());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    //showProgressBar(progressBar);
                    if(mPresenter.getDataManager().isSearchFragmentEnable()){
                        getSupportActionBar().setTitle(R.string.search_toolbar_title);
//                        changeFragment(new DayMenuFragment(), mPresenter.getDataManager().getCopanyUserName());
                        changeFragment(new DayMenuFragment(), intentOneCompany);

                    }else{
                        mPresenter.getDataManager().setSearchFragmentEnable(false);
                        getSupportActionBar().setTitle(R.string.app_name);
                        changeFragment(new DayMenuFragment());

                    }
                    //showToast(getContext(), "Home Fragment Clicked");
                    //getSupportActionBar().setTitle(R.string.home_toolbar_title);
                    //changeFragment(new DayMenuFragment(), mPresenter.getDataManager().getCopanyUserName());
                    break;
                case R.id.restaurant:
                    getSupportActionBar().setTitle(R.string.order_toolbar_title);
                    //showToast(getContext(), "Restaurant Fragment Clicked");
                    changeFragment(new UserOrderFragment());
                    break;
                case R.id.suppliers:
                    getSupportActionBar().setTitle(R.string.all_supplier);
                    //onSearchRequested();
                    // mPresenter.getDataManager().setSearchFragmentEnable(true);
                    //showToast(getContext(), "Search Fragment Clicked");
                    changeFragment(new SearchFragment());
                    break;
//                case R.id.catalog:
//                    getSupportActionBar().setTitle(R.string.catalog_toolbar_title);
//                    //showToast(getContext(), "Restaurant Fragment Clicked");
//                    changeFragment(new CatalogFragment());
//
//                    break;
                case R.id.suggestion:
                    getSupportActionBar().setTitle(R.string.suggestion_toolbar_title);
                    //showToast(getContext(), "Restaurant Fragment Clicked");
                    changeFragment(new SuggestionFragment());

                    break;
                case R.id.promotion:
                    getSupportActionBar().setTitle(R.string.promotion_toolbar_title);
                    //showToast(getContext(), "Restaurant Fragment Clicked");
                    changeFragment(new PromotionFragment());

                    break;
            }
            return true;
        });

    }

    private void rateMeDialogSetup() {
        AppRate.with(this)
                .setInstallDays(2) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false

                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onFloatingActionListener(MainMealSelectedModel mealSelectedModels) {
        Log.d("LIVRAISON", "Livraison Price FAB: " + mealSelectedModels.getCompanyDeliveryPrice());
        Intent intent = new Intent(this, AccompagnementScreen.class);
        intent.putExtra(MAIN_MEAL_SELECTED_ITEM, mealSelectedModels);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mPresenter.getDataManager().getFragmentStateToShow() == 1){
            //TODO CHANGE FRAGMENT TO USER ORDER
            getSupportActionBar().setTitle(R.string.order_toolbar_title);
            changeFragment(new UserOrderFragment());
            mPresenter.getDataManager().setFragmentStateToShow(0);
        }else if(mPresenter.getDataManager().getFragmentStateToShow() == 4){
            getSupportActionBar().setTitle(R.string.suggestion_toolbar_title);
            changeFragment(new SuggestionFragment());
            mPresenter.getDataManager().setFragmentStateToShow(0);
        }else if(mPresenter.getDataManager().getFragmentStateToShow() == 3){
            getSupportActionBar().setTitle(R.string.all_supplier);
            changeFragment(new SearchFragment());
            mPresenter.getDataManager().setFragmentStateToShow(0);
        }
        else{
            if(mPresenter.getDataManager().getFragmentStateToShow() == 0){
                getSupportActionBar().setTitle(R.string.home_toolbar_title);
                if(mPresenter.getDataManager().isSearchFragmentEnable()){

                    getSupportActionBar().setTitle(R.string.app_name);

                    changeFragment(new DayMenuFragment(), intentOneCompany);
                    mPresenter.getDataManager().setFragmentStateToShow(0);
                }else{
                    Log.d("MAIN SCREE ACTIVITY", "Search Fragment Not Enable: " + mPresenter.getDataManager().isSearchFragmentEnable());
                    getSupportActionBar().setTitle(R.string.app_name);
                    changeFragment(new DayMenuFragment());
                    mPresenter.getDataManager().setFragmentStateToShow(0);
                }

                //changeFragment(new DayMenuFragment(), mPresenter.getDataManager().getCopanyUserName());
            }else if(mPresenter.getDataManager().getFragmentStateToShow() == 1){
                //TODO CHANGE FRAGMENT TO USER ORDER
                getSupportActionBar().setTitle(R.string.order_toolbar_title);
                changeFragment(new UserOrderFragment());
                mPresenter.getDataManager().setFragmentStateToShow(0);
            }else if(mPresenter.getDataManager().getFragmentStateToShow() == 2){
                //TODO CHANGE FRAGMENT TO OTHER COMPANY
                getSupportActionBar().setTitle(R.string.all_supplier);
                mPresenter.getDataManager().setFragmentStateToShow(0);
            }else{
                changeFragment(new DayMenuFragment());
                mPresenter.getDataManager().setFragmentStateToShow(0);
            }
        }

        Intent intentDel = new Intent(this, CheckMealBillTimeService.class);
        startService(intentDel);

        if(!mPresenter.getDataManager().isServiceCheckOrderTime()){
            Intent checkTimeOrder = new Intent(this, NotifyUserService.class);
            startService(checkTimeOrder);
            mPresenter.getDataManager().setServiceCheckOrderTime(true);
            Intent checkSuggestionService = new Intent(this, CheckSuggestionTime.class);
            startService(checkSuggestionService);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.getDataManager().setCompanyUserName("");
//        mPresenter.getDataManager().setCompanyPhoneNumber("");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intentOneCompany = intent.getStringExtra("arg_fragment_day_menu");
        Log.d("SEARCH COMPANY ACTIVITY", "Main Activity Screen. Check search intent: " + intentOneCompany);
    }

    //    @Override
//    public boolean onSearchRequested() {
//        return super.onSearchRequested();
//    }



    @Override
    public void onCompanySelected(String companyName) {
//        changeFragment(DayMenuFragmentSearch.class, companyName);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        mPresenter.getDataManager().setSearchFragmentEnable(true);
        changeFragment(new DayMenuFragment(), companyName);
    }

    @Override
    public void onCanNotPassOrder() {
        showSnackBar(relativeLayout, getString(R.string.company_not_work), Snackbar.LENGTH_LONG);
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getResources ().getString ( R.string.channel_id ), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onCatalogMealOrder() {
        showConfirmDiaologBox(MainScreenActivity.this, "Contact",
                getString(R.string.contact_company) + " \n" + getString(R.string.company_username)
                        + " " + mPresenter.getDataManager().getCopanyUserName() + "\nContact: " +
                        mPresenter.getDataManager().getCopanyPhoneNumber());
    }
}
