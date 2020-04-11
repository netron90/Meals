package com.personnalize_design.meals.ui.promotion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.CompanyPromotion;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.promotion.adapters.PromotionAdapter;
import com.personnalize_design.meals.ui.promotion.interfaces.PromotionMvpView;
import com.personnalize_design.meals.ui.promotion.presenter.PromotionPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PromotionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PromotionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PromotionFragment extends BaseFragment implements PromotionMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    public PromotionPresenter<PromotionFragment> mPresenter;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    private PromotionAdapter promotionAdapter;
    private String companyPhoneNumber;

    List<CompanyPromotion.CompanyEventBean> listPromotion;


    private OnFragmentInteractionListener mListener;

    public PromotionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PromotionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PromotionFragment newInstance(String param1, String param2) {
        PromotionFragment fragment = new PromotionFragment();
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
        View v= inflater.inflate(R.layout.fragment_promotion, container, false);
        setUp(v);
        return v;
    }

    private void setUp(View view) {
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().inject(this);
        mPresenter.onAttachView(this);
        progressBar.setVisibility(View.VISIBLE);
        listPromotion = new ArrayList<>();
        companyPhoneNumber = mPresenter.getDataManager().getCopanyPhoneNumber();
        promotionAdapter = new PromotionAdapter(listPromotion, getContext(), companyPhoneNumber);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(promotionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter.checkPromotion(mPresenter.getDataManager().getCopanyUserName());
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
    public void OnErrorOccured() {
        progressBar.setVisibility(View.GONE);
        changeFragment(new ErrorFragment());
    }

    @Override
    public void OnGetCompanyPromotion(CompanyPromotion companyPromotion) {
        progressBar.setVisibility(View.GONE);
        for(int i = 0; i < companyPromotion.getCompanyEvent().size(); i++){
            listPromotion.add(companyPromotion.getCompanyEvent().get(i));
            promotionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnGetNoCompanyPromotion() {
        changeFragment(new NoPromotionFragment());
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
