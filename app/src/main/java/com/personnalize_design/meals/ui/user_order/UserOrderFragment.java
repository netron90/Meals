package com.personnalize_design.meals.ui.user_order;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.internal.queue.MpscLinkedQueue;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.services.CheckMealBillTimeService;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.user_order.adapter.UserOrderAdapter;
import com.personnalize_design.meals.ui.user_order.interfaces.UserOrderMvpView;
import com.personnalize_design.meals.ui.user_order.presenter.UserOrderPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserOrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserOrderFragment extends BaseFragment implements UserOrderMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    public UserOrderPresenter<UserOrderFragment> mPresenter;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @BindView(R.id.todayDate)
    public TextView todayDate;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.explanationUserOrder)
    public TextView explanationUserOrder;

    @BindView(R.id.companyName)
    public TextView companyName;

    UserOrderAdapter userOrderAdapter;

    List<UserOrderModel.MealListBean> listBeans;

    public UserOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserOrderFragment newInstance(String param1, String param2) {
        UserOrderFragment fragment = new UserOrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_order, container, false);
        setUp(view);
        return view;
    }

    private void setUp(View view) {
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().inject(this);
        mPresenter.onAttachView(this);
        progressBar.setVisibility(View.VISIBLE);
        Log.d("USER ORDER SETUP", "User Order Setup");
        mPresenter.getUserMealOrderFromDb();

        listBeans = new ArrayList<>();
        userOrderAdapter = new UserOrderAdapter(getContext(), listBeans);
        recyclerView.setAdapter(userOrderAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.d("USER ORDER", "USER order fragment");






    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void OnNoUserOrder() {
        changeFragment(new NoOrderFragment());
    }

    @Override
    public void onUserOrderInfoSucces(UserOrderModel.UserOrder userOrder) {
        progressBar.setVisibility(View.GONE);
        companyName.setText(userOrder.companyName);
        todayDate.setText(userOrder.todayDate);
        mPresenter.getMealOrderList(userOrder);
    }

    @Override
    public void onGetListMealOrderSuccess(List<UserOrderModel.MealListBean> mealListBeans, UserOrderModel.UserOrder userOrder) {
        for(int i = 0; i < mealListBeans.size(); i++){
            listBeans.add(mealListBeans.get(i));
            userOrderAdapter.notifyDataSetChanged();
        }
        if(!mPresenter.getDataManager().isDeliveryStateEnable()){
            explanationUserOrder.setVisibility(View.VISIBLE);
            explanationUserOrder.setText(getContext().getString(R.string.explanation_text1) + " " + userOrder.getCompanyName() + " " +getContext().getString(R.string.explanation_text2) + " "
                    + userOrder.getCompanyContact() +getActivity().getString(R.string.explanation_text3) +" "+ userOrder.getCompanyName() + " " +getActivity().getString(R.string.explanation_text4) + " " + userOrder.getCompanyLocalisation());

            //TODO: START SERVICE TO DELETE BILL ORDER AFTER 1 DAY
            Intent intent = new Intent(getContext(), CheckMealBillTimeService.class);
            if(getActivity() != null){
                getActivity().startService(intent);
            }
        }else{
            explanationUserOrder.setVisibility(View.VISIBLE);
            explanationUserOrder.setText(getActivity().getString(R.string.id_confirm_text) + "\n " + getActivity().getString( R.string.explanation_text5) + " " + userOrder.getCompanyContact());

            //TODO: START SERVICE TO DELETE BILL ORDER AFTER 1 DAY
            Intent intent = new Intent(getContext(), CheckMealBillTimeService.class);
            if(getContext() != null){
                getActivity().startService(intent);
            }
        }



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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
