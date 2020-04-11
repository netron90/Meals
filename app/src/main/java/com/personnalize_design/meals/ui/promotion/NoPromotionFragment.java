package com.personnalize_design.meals.ui.promotion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.promotion.adapters.PromotionAdapter;
import com.personnalize_design.meals.ui.promotion.interfaces.NoPromotionMvpView;
import com.personnalize_design.meals.ui.promotion.presenter.NoPromotionMvpPresenter;
import com.personnalize_design.meals.ui.promotion.presenter.NoPromotionPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoPromotionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoPromotionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoPromotionFragment extends BaseFragment implements NoPromotionMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    public NoPromotionPresenter<NoPromotionFragment> mPresenter;

    @BindView(R.id.noPromotionText)
    public TextView noPromotionText;

    private OnFragmentInteractionListener mListener;

    public NoPromotionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoPromotionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoPromotionFragment newInstance(String param1, String param2) {
        NoPromotionFragment fragment = new NoPromotionFragment();
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
        View view =  inflater.inflate(R.layout.fragment_no_promotion, container, false);
        setUp(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void setUp(View view) {
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().inject(this);
        mPresenter.onAttachView(this);
        noPromotionText.setText(getString(R.string.no_promotion_text));
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
