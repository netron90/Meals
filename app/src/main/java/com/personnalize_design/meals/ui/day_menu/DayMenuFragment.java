package com.personnalize_design.meals.ui.day_menu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllCompanyModel;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.OtherDayMenuModel;
import com.personnalize_design.meals.data.model.TodayDateModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.day_menu.adapters.MainMealsAdapter;
import com.personnalize_design.meals.ui.day_menu.adapters.OtherMealsAdapter;
import com.personnalize_design.meals.ui.day_menu.interfaces.DayMenuMvpView;
import com.personnalize_design.meals.ui.day_menu.presenter.DayMenuPresenter;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.user_order.UserOrderFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //DayMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayMenuFragment extends BaseFragment implements DayMenuMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.todayDate)
    public TextView todayDate;

    @BindView(R.id.companyName)
    public TextView companyName;

    @BindView(R.id.mainMeal)
    public ImageView mainMealImg;

    @BindView(R.id.mainMealName)
    public TextView mainMealName;

    @BindView(R.id.mainMealPrice)
    public TextView mainMealPrice;

    @BindView(R.id.otherMenuToday)
    public TextView otherMenuToday;

    @BindView(R.id.backgroundMainMeal)
    public RelativeLayout backgroundMainMeal;

    @BindView(R.id.floatingButton)
    public FloatingActionButton floatingActionButton;

    @BindView(R.id.discrete_scroll_view)
    public DiscreteScrollView discreteScrollView;

    @BindView(R.id.swipeRefresh)
    public SwipeRefreshLayout swipeRefreshLayout;

    private MainMealSelectedModel mainMealSelectedModel;

    @Inject
    public DayMenuPresenter<DayMenuFragment> mPresenter;

    private OnFloatingActionListener mListener;

    public DayMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DayMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DayMenuFragment newInstance(String param1, String param2) {
        DayMenuFragment fragment = new DayMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_day_menu, container, false);
        setUp(view);


        return view;
    }

    private void setUp(View view) {
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().inject(this);
        mPresenter.onAttachView(this);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorBlue));


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO: MAKE REQUEST TO GET DATA FROM SERVER
                mPresenter.menuDayRequest();
            }
        });
        mainMealSelectedModel = new MainMealSelectedModel();

        //Check Connection State
        if(isNetworkAvailable())
        {
            if(mPresenter.getDataManager().isSearchFragmentEnable()){
                //TODO: MAKE REQUEST TO GET ONE COMPANY DATA FROM SERVER
                Bundle bundle = new Bundle();
                String companyName = bundle.getString("companyName");
                mPresenter.getOneCompany(companyName);
            }else{
                //TODO: MAKE REQUEST TO GET DATA FROM SERVER
                mPresenter.menuDayRequest();
            }

        }else{
            //TODO: TELL USER TO TURN ON CONNECTION AND REFRESH PAGE
            changeFragment(new ErrorFragment());
        }
//        MainMealsAdapter otherMealsAdapter = new MainMealsAdapter()


//        discreteScrollView.s
    }

    @OnClick(R.id.floatingButton)
    public void fabBtnPressed(){
        Log.d("FAB", "Floating Btn Pressed: " +mainMealSelectedModel.getCompanyCoverImage());
        onButtonPressed(mainMealSelectedModel);
    }

    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(MainMealSelectedModel mealSelectedModels) {
        if (mListener != null) {
            mListener.onFloatingActionListener(mealSelectedModels);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFloatingActionListener) {
            mListener = (OnFloatingActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setTodayDateMenu(TodayDateModel todayDateMenu) {
        swipeRefreshLayout.setRefreshing(false);
        todayDate.setText(todayDateMenu.getTodayDate());
    }

    @Override
    public void getAllCompanyData(List<AllCompanyModel.DataBean> allCompanyList, int tyOfFragmentTOShow) {


        if(tyOfFragmentTOShow == 0){
            //IF LIST IS EMPTY, SHOW NO INFORMATION FRAGMENT
            changeFragment(new NoFactoryErrorFragment());

        }else if(tyOfFragmentTOShow == 1){
            //IF ALL LIST HAS ONE ITEM SHOW ALL OTHER COMPANY MENU



            companyName.setText(allCompanyList.get(0).getUsername());
            GlideApp.with(getContext())
                    .load(BASE_URL+allCompanyList.get(0).getMainMealImg())
                    .placeholder(R.drawable.logo)
                    .centerCrop()
                    .into(mainMealImg);
            mainMealName.setText(allCompanyList.get(0).getMainMealName());
            mainMealPrice.setText(allCompanyList.get(0).getMainMealPrice());
            floatingActionButton.setVisibility(View.VISIBLE);

            mPresenter.getOtherMenuCompany(allCompanyList.get(0).getUsername(), allCompanyList.get(0).getCoverImagePath(), allCompanyList.get(0).getLivraisonPrice());

            setUpMainMealSelectedModel(allCompanyList);

        }else{
            //IF LIST CONTAINS MORE THAN ONE ELEMENT, SHOW THIS MAIN MEAL

            setUpMainMealBlock(allCompanyList);

        }

    }

    private void setUpMainMealSelectedModel(List<AllCompanyModel.DataBean> allCompanyList) {
        mainMealSelectedModel.setCompanyNameService(allCompanyList.get(0).getUsername());
        mainMealSelectedModel.setMainMealImage(allCompanyList.get(0).getMainMealImg());
        mainMealSelectedModel.setMainMealName(allCompanyList.get(0).getMainMealName());
        mainMealSelectedModel.setMainMealPrice(allCompanyList.get(0).getMainMealPrice());
        mainMealSelectedModel.setCompanyCoverImage(allCompanyList.get(0).getCoverImagePath());
        mainMealSelectedModel.setCompanyDeliveryPrice(allCompanyList.get(0).getLivraisonPrice());
        Log.d("COVER IMG PATH", "LivraisonPrice: " + allCompanyList.get(0).getLivraisonPrice());
    }

    @NotNull
    private void setUpMainMealBlock(List<AllCompanyModel.DataBean> allCompanyList) {

        companyName.setText(allCompanyList.get(0).getUsername());
        GlideApp.with(getContext())
                .load(BASE_URL+allCompanyList.get(0).getMainMealImg())
                .placeholder(R.drawable.logo)
                .centerCrop().into(mainMealImg);
        mainMealName.setText(allCompanyList.get(0).getMainMealName());
        mainMealPrice.setText(allCompanyList.get(0).getMainMealPrice());

       setUpMainMealSelectedModel(allCompanyList);

        List<AllCompanyModel.DataBean> tempRandomList;
        tempRandomList = allCompanyList;
        tempRandomList.remove(0);
        Collections.shuffle(tempRandomList);


        setUpDiscreteScrollView(tempRandomList);





    }

    @Override
    public void getAllOtherMenuCompany(List<OtherDayMenuModel.DataBean.OtherMenuBean> otherDayMenuList, String companyCoverImage, boolean isOtherMenuSet, String deliveryPrice) {

        if(isOtherMenuSet)
        {
            otherMenuToday.setText(getString(R.string.no_other_menu));
        }else{
            otherMenuToday.setText(getString(R.string.other_menu));
//                Log.d("OTHER MENU FRAGMENT", "Other Menu Today Text: " + otherMenuToday.getText());
//            List<OtherDayMenuModel.DataBean.OtherMenuBean> tempRandomList;
//            Collections.shuffle(otherDayMenuList);
//            tempRandomList = otherDayMenuList;
            //Log.d("OTHER MENU FRAGMENT", "Other Menu Listt: " + tempRandomList.size());
            discreteScrollView.setAdapter(new OtherMealsAdapter(otherDayMenuList, getContext(), companyCoverImage, deliveryPrice));
            discreteScrollView.setSlideOnFling(true);
            discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                    .setMaxScale(1.05f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                    .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                    .build());
        }


    }

    private void setUpDiscreteScrollView(List<AllCompanyModel.DataBean> allCompanyList) {



            otherMenuToday.setText(getString(R.string.other_menu));
            Log.d("OTHER MENU FRAGMENT", "Other Main Meal Listt: " + allCompanyList.size());
            discreteScrollView.setAdapter(new MainMealsAdapter(allCompanyList, getContext()));
            discreteScrollView.setSlideOnFling(true);
            discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                    .setMaxScale(1.05f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                    .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                    .build());



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFloatingActionListener {
        // TODO: Update argument type and name
        void onFloatingActionListener(MainMealSelectedModel mealSelectedModels);
    }


}
