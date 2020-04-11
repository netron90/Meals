package com.personnalize_design.meals.ui.menu_quantity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllMealSelected;
import com.personnalize_design.meals.data.model.BillClientInformation;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.MealFacture;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.base.OnGetMealsQuantity;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.menu_quantity.adapters.BilanItemAdapter;
import com.personnalize_design.meals.ui.menu_quantity.adapters.MealQuantityAdapter;
import com.personnalize_design.meals.ui.menu_quantity.interfaces.MenuQuantityMvpView;
import com.personnalize_design.meals.ui.menu_quantity.presenter.MenuQuantityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MenuQuantityActivity extends BaseActivity implements MenuQuantityMvpView,
        MealQuantityAdapter.OnAddMealQuantity, MealQuantityAdapter.OnRemoveMealQuantity,
        BaseActivity.OnSendUserOrder, BaseActivity.OnGoToUserOrder, BaseActivity.OnSaveDeliveryState {

    @Inject
    public MenuQuantityPresenter<MenuQuantityActivity> mPresenter;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.recyclerViewQuantity)
    public RecyclerView recyclerViewQuantity;

    @BindView(R.id.recyclerViewBilan)
    public RecyclerView recyclerViewBilan;

    @BindView(R.id.btnOrderNow)
    public Button startOrder;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    private List<MainMealSelectedModel> listMealSelected;

    private MealQuantityAdapter mealQuantityAdapter;

    private BilanItemAdapter bilanItemAdapter;

    private OnSetMealQuantity mListener;

    private OnGetTotalMealPrice mListener2;

    private OnGetMealsQuantity mListenerMealQuantity;

    private String companyDeliveryPrice;

    private String companyName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        setContentView(R.layout.activity_menu_quantity);
        getComponent ().inject (this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.menu_bilan);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listMealSelected = AllMealSelected.getInstance().getList();
        companyName = AllMealSelected.getInstance().getCompanyName();

        companyDeliveryPrice = listMealSelected.get(0).getCompanyDeliveryPrice();

        mealQuantityAdapter = new MealQuantityAdapter(listMealSelected, this);
        bilanItemAdapter = new BilanItemAdapter(listMealSelected, this);
        mListener = bilanItemAdapter;
        mListener2 = bilanItemAdapter;
        mListenerMealQuantity = bilanItemAdapter;

        recyclerViewQuantity.setAdapter(mealQuantityAdapter);
        recyclerViewQuantity.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewQuantity.setHasFixedSize(true);
        recyclerViewQuantity.setItemAnimator(new DefaultItemAnimator());

        recyclerViewBilan.setAdapter(bilanItemAdapter);
        recyclerViewBilan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewBilan.setHasFixedSize(true);
        recyclerViewBilan.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onAddMealQuantity(TextView mealQuantityNbr, int position) {
        int mealNbr = Integer.valueOf(mealQuantityNbr.getText().toString());
        mealNbr++;
        mealQuantityNbr.setText(String.valueOf(mealNbr));
        mealQuantityAdapter.notifyDataSetChanged();
        if(mListener != null){
            mListener.onSetMealQuantity(mealNbr, position);
            bilanItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRemoveMealQuantity(TextView mealQuantityNbr, int position) {
        int mealNbr = Integer.valueOf(mealQuantityNbr.getText().toString());
        if(mealNbr > 1){
            mealNbr--;
            mealQuantityNbr.setText(String.valueOf(mealNbr));
            mealQuantityAdapter.notifyDataSetChanged();
            if(mListener != null){
                mListener.onSetMealQuantity(mealNbr, position);
                bilanItemAdapter.notifyDataSetChanged();
            }
        }

    }

    @OnClick(R.id.btnOrderNow)
    public void showBilanMealDialogBox(){
        if(mListener2 != null && mListenerMealQuantity != null)
        {
            String totalPrice = mListener2.onGetTotalMealPrice();
            ArrayList<String> mealsQuantity = mListenerMealQuantity.onGetMealsQuantity();
            showBialnMealCustomDialogBox(this, companyDeliveryPrice, totalPrice, companyName, listMealSelected, mealsQuantity);
            //mPresenter.getTotalMealPrice(totalPrice);
        }
    }


    @Override
    public void onFaillureOrderSent() {
        progressBar.setVisibility(View.GONE);
        showDiaologBox(this, getString(R.string.order_failure_sent), getString(R.string.order_faillure_sent));
    }

    @Override
    public void onErrorOccured(int i) {
        progressBar.setVisibility(View.GONE);
        showDiaologBox(this, getString(R.string.order_failure_sent), getString(R.string.order_faillure_sent));
    }

    @Override
    public void onCheckEndOrderMeal(boolean userCanPassOrder, String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealSelected) {
        if(userCanPassOrder){
            if(isNetworkAvailable())
            {

                BillClientInformation billClientInformation = new BillClientInformation(totalMealsPrices, companyName, clientName, clientContact, clientLocalisation, listMealSelected);
                Log.d("SEND ORDER", "mPresenter Send Order");
                mPresenter.sendUserOrder(billClientInformation);
            }else{
                //changeFragment(new ErrorFragment());
                progressBar.setVisibility(View.GONE);
                showLongToast(this, getString(R.string.no_internet_connecion));
            }
        }else{
            progressBar.setVisibility(View.GONE);
            showLongToast(this, getString(R.string.end_meal_order_message));
        }
    }


    @Override
    public void onSuccessOrderSent() {
        progressBar.setVisibility(View.GONE);
        Log.d("SUCCESS SEND ORDER", "Oreder has sent succesfully. Show Dialog Box");
        if(mPresenter.getDataManager().isDeliveryStateEnable()){
            Log.d("SUCCESS SEND ORDER", "Delivery State Enable");
            showDiaologBox(this, getString(R.string.order_success_sent), getString(R.string.order_successfully_sent));
        }else{
            Log.d("SUCCESS SEND ORDER", "Delivery State Disable");
            showDiaologBox(this, getString(R.string.your_order), getString(R.string.finalize_your_order_text));
        }

    }

    @Override
    public void onSendUserOrder(Context ctn, String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealSelected) {
        Log.d("INTERFACE DIALOG BOX", "Interface Diaolog Box Work");
        progressBar.setVisibility(View.VISIBLE);
        if(mPresenter.getDataManager().isMealOrderExist()){
            //TODO: CHECK IF USER HAS ALREADY ASK FOR MEAL ORDER
            progressBar.setVisibility(View.GONE);
            showLongToast(this, getString(R.string.user_meal_order_exist));
        }else{
            //TODO: BEFORE ASK FOR BILL USER INFO, CHECK IF COMPANY HOUR ORDER IS NOT PASS
            mPresenter.checkEndHourMealOrder(totalMealsPrices, companyName, clientName, clientContact, clientLocalisation, listMealSelected);
        }

    }

    @Override
    public void onGoToUserOrder(int fragmentState) {
        mPresenter.getDataManager().setFragmentStateToShow(fragmentState);
        mPresenter.getDataManager().setMealOrderExist(true);
        Log.d("MENU QUANTITY", "Show User Order Screen. Fragment State: " + mPresenter.getDataManager().isDeliveryStateEnable());
//        mPresenter.getDataManager().setUserOrderFromServer(true);
        Intent intentHomeScreen = new Intent(getContext(), MainScreenActivity.class);
        intentHomeScreen.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //intentHomeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP) ;
        startActivity(intentHomeScreen);
        finish();
    }

    @Override
    public void onSaveDeliveryState(boolean deliveryState) {
        mPresenter.getDataManager().setDeliveryOrderState(deliveryState);
    }

    public interface OnSetMealQuantity{
        void onSetMealQuantity(int mealQuantity, int layoutPosition);
    }

    public interface OnGetTotalMealPrice{
        String onGetTotalMealPrice();
    }


}
