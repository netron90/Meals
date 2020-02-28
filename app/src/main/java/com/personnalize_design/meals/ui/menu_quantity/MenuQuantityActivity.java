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
            mPresenter.getTotalMealPrice(totalPrice);
        }
    }

    @Override
    public void onSetMealsTotalPrice(String priceString) {

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
    public void onSuccessOrderSent() {
        progressBar.setVisibility(View.GONE);
        showDiaologBox(this, getString(R.string.order_success_sent), getString(R.string.order_successfully_sent));
    }

    @Override
    public void onSendUserOrder(Context ctn, String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealSelected) {
        Log.d("INTERFACE DIALOG BOX", "Interface Diaolog Box Work");
        if(isNetworkAvailable())
        {
            progressBar.setVisibility(View.VISIBLE);
            BillClientInformation billClientInformation = new BillClientInformation(totalMealsPrices, companyName, clientName, clientContact, clientLocalisation, listMealSelected);

            mPresenter.sendUserOrder(billClientInformation);
        }else{
            changeFragment(new ErrorFragment());
            //showLongToast(this, getString(R.string.no_internet_connecion));
        }
    }

    @Override
    public void onGoToUserOrder(int fragmentState) {
        mPresenter.getDataManager().setFragmentStateToShow(fragmentState);
        Log.d("MENU QUANTITY", "Fragment State: " + fragmentState);
//        mPresenter.getDataManager().setUserOrderFromServer(true);
        Intent intent = new Intent(this, MainScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
