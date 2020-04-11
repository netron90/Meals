package com.personnalize_design.meals.ui.meal_addition;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AddOnMenuModel;
import com.personnalize_design.meals.data.model.AllMealSelected;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.meal_addition.adapters.AddOnAdapter;
import com.personnalize_design.meals.ui.meal_addition.adapters.MealSelectedAdapter;
import com.personnalize_design.meals.ui.meal_addition.interfaces.AccompagnementScreenMvpView;
import com.personnalize_design.meals.ui.meal_addition.presenter.AccompagnementScreenPresenter;
import com.personnalize_design.meals.ui.menu_quantity.MenuQuantityActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;
import static com.personnalize_design.meals.constants.Mutils.MAIN_MEAL_SELECTED_ITEM;

public class AccompagnementScreen extends BaseActivity implements
        AccompagnementScreenMvpView,
        AddOnAdapter.OnDeleteNewItem,
        AddOnAdapter.OnAddNewItem {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.companyImageCover)
    public ImageView companyCoverPicture;

    @BindView(R.id.companyName)
    public TextView companyName;

    @BindView(R.id.recyclerViewMeal)
    public RecyclerView mealSelectedRv;

    @BindView(R.id.recyclerView)
    public RecyclerView addOnRecView;

    @BindView(R.id.chooseMenuQuantity)
    public Button chooseMenuQuantity;

    @BindView(R.id.textAccompagnement)
    public TextView accompagnementText;

    private MainMealSelectedModel mainMealSelectedModel;

    @Inject
    public AccompagnementScreenPresenter<AccompagnementScreen> mPresenter;

    private List<MainMealSelectedModel> mainMealSelectedModelList;
    private MealSelectedAdapter mealSelectedAdapter;

    private AddOnAdapter addOnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    public void setUp(){
        setContentView(R.layout.activity_accompagnement_screen);
        getComponent ().inject (this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_accompagnement_title);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mainMealSelectedModelList = new ArrayList<>();
        mainMealSelectedModel = getIntent().getParcelableExtra(MAIN_MEAL_SELECTED_ITEM);

        mainMealSelectedModelList.add(mainMealSelectedModel);
        Log.d("COVER IMAGE", "Cover Image Value: " + mainMealSelectedModel.getCompanyCoverImage());
        Log.d("DELIVERY PRICE", "Company Delivery Price GO To Quantity" + mainMealSelectedModel.getCompanyDeliveryPrice());
        if(mainMealSelectedModel.getCompanyCoverImage() == null ){
            GlideApp.with(getContext())
                    .load(R.drawable.meals_logo_v2)
                    .placeholder(R.drawable.meals_logo_v2)
                    .centerCrop().into(companyCoverPicture);
        }else{
            GlideApp.with(getContext())
                    .load(mainMealSelectedModel.getCompanyCoverImage())
                    .placeholder(R.drawable.meals_logo_v2)
                    .centerCrop().into(companyCoverPicture);
        }


        companyName.setText(mainMealSelectedModel.getCompanyNameService() +" - MENU");

        mPresenter.getAddOnMenu(mainMealSelectedModel.getCompanyNameService());
        mealSelectedAdapter = new MealSelectedAdapter(getContext(), mainMealSelectedModelList);
        mealSelectedRv.setAdapter(mealSelectedAdapter);
        mealSelectedRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mealSelectedRv.setHasFixedSize(true);
        mealSelectedRv.setItemAnimator(new DefaultItemAnimator());
    }

//    @Override
//    public void onRemoveMealSelected() {
//        mealSelectedAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onAdditionMenuReceive(List<AddOnMenuModel.DataBean.AccompagnementBean> accompagnementList, boolean isListEmpty) {
        if(isListEmpty == true){
            accompagnementText.setText(getString(R.string.no_add_on));

        }else{
            accompagnementText.setText(getString(R.string.accompagnements));
            addOnAdapter = new AddOnAdapter(accompagnementList, this, mainMealSelectedModelList.get(0).getCompanyDeliveryPrice());
            addOnRecView.setAdapter(addOnAdapter);
            addOnRecView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            addOnRecView.setHasFixedSize(true);
            addOnRecView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @OnClick(R.id.chooseMenuQuantity)
    public void goToMenuQuantityChosen(){
        AllMealSelected.getInstance().setList(mainMealSelectedModelList);
        AllMealSelected.getInstance().setCompanyName(mainMealSelectedModel.getCompanyNameService());
        for(int i = 0; i < mainMealSelectedModelList.size(); i++){
            Log.d("LIST MEAL SELECTED", "Element " + (i+1) + ": " + mainMealSelectedModelList.get(i).getMainMealName());
            Log.d("DELIVERY PRICE", "Company Delivery Price GO To Quantity" + mainMealSelectedModelList.get(0).getCompanyDeliveryPrice());
        }
        startActivity(this, MenuQuantityActivity.class);
    }

    @Override
    public void onAddNewItem(MainMealSelectedModel mainMealSelectedModel) {
        mainMealSelectedModelList.add(mainMealSelectedModel);
        mealSelectedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteNewItem(MainMealSelectedModel obj) {
        mainMealSelectedModelList.remove(obj);
        mealSelectedAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MODEL ON START", "mainMealSelectedModel.getCompanyCoverImage(): " + mainMealSelectedModel.getCompanyCoverImage());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MODEL ON RESTART", "mainMealSelectedModel.getCompanyCoverImage(): " + mainMealSelectedModel.getCompanyCoverImage());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MODEL ON ON STOP", "mainMealSelectedModel.getCompanyCoverImage(): " + mainMealSelectedModel.getCompanyCoverImage());

    }}


