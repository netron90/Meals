package com.personnalize_design.meals.ui.day_menu;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.search.SearchFragment;
import com.personnalize_design.meals.ui.search.adapters.SearchAdapter;
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
        NoOrderFragment.OnFragmentInteractionListener {

    @Inject
    MainScreenPresenter<MainScreenActivity> mPresenter;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.bottom_navigation_view)
    public BottomNavigationView bottomNavigationView;



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

        Log.d("USER FRAGMENT", "USER Fragment: " +mPresenter.getDataManager().getFragmentStateToShow());

        if(mPresenter.getDataManager().getFragmentStateToShow() == 0){
            if(mPresenter.getDataManager().isSearchFragmentEnable()){
                mPresenter.getDataManager().setSearchFragmentEnable(false);
                Intent intentOneCompany = getIntent();

                getSupportActionBar().setTitle(R.string.app_name);
                changeFragment(new DayMenuFragment(), intentOneCompany.getStringExtra("arg_fragment_day_menu"));
                mPresenter.getDataManager().setFragmentStateToShow(0);
            }else{
                getSupportActionBar().setTitle(R.string.app_name);
                changeFragment(new DayMenuFragment());
                mPresenter.getDataManager().setFragmentStateToShow(0);
            }

        }else if(mPresenter.getDataManager().getFragmentStateToShow() == 1){
            //TODO CHANGE FRAGMENT TO USER ORDER
            getSupportActionBar().setTitle(R.string.order_toolbar_title);
            changeFragment(new UserOrderFragment());
            mPresenter.getDataManager().setFragmentStateToShow(0);
        }else if(mPresenter.getDataManager().getFragmentStateToShow() == 2){
            //TODO CHANGE FRAGMENT TO OTHER COMPANY
            getSupportActionBar().setTitle(R.string.search_toolbar_title);
            mPresenter.getDataManager().setFragmentStateToShow(0);
        }else{
            changeFragment(new DayMenuFragment());
            mPresenter.getDataManager().setFragmentStateToShow(0);
        }


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
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    if(mPresenter.getDataManager().isSearchFragmentEnable()){
                        getSupportActionBar().setTitle(R.string.search_toolbar_title);
                    }else{
                        mPresenter.getDataManager().setSearchFragmentEnable(false);
                        getSupportActionBar().setTitle(R.string.app_name);
                    }
                    showToast(getContext(), "Home Fragment Clicked");
                    changeFragment(new DayMenuFragment());
                    break;
                case R.id.restaurant:
                    getSupportActionBar().setTitle(R.string.order_toolbar_title);
                    showToast(getContext(), "Restaurant Fragment Clicked");
                    changeFragment(new UserOrderFragment());
                    break;
                case R.id.search:
                    getSupportActionBar().setTitle(R.string.search_toolbar_title);
                    onSearchRequested();
                    mPresenter.getDataManager().setSearchFragmentEnable(true);
                    showToast(getContext(), "Search Fragment Clicked");
                    changeFragment(new SearchFragment());
                    break;
            }
            return true;
        });

    }

//    @Override
//    public boolean onSearchRequested() {
//        return super.onSearchRequested();
//    }

    @Override
    public void onCompanySelected(String companyName) {
//        changeFragment(DayMenuFragmentSearch.class, companyName);
        changeFragment(new DayMenuFragment(), companyName);
    }
}
