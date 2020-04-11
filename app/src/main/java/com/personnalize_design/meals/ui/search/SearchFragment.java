package com.personnalize_design.meals.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.search.adapters.SearchAdapter;
import com.personnalize_design.meals.ui.search.interfaces.SearchFragmentMvpView;
import com.personnalize_design.meals.ui.search.presenter.SearchFragmentMvpPresenter;
import com.personnalize_design.meals.ui.search.presenter.SearchFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends BaseFragment implements SearchFragmentMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    public SearchFragmentPresenter<SearchFragment> mPresenter;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @BindView(R.id.searchOneCompany)
    public FloatingActionButton fabSearch;

    SearchAdapter searchAdapter;

    List<AllCompanySearch.CompanyData> allCompanySearchList;


    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        setUp(view);
        return view;
    }

    private void setUp(View view) {
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().injext(this);
        mPresenter.onAttachView(this);

        if(isNetworkAvailable()){
            progressBar.setVisibility(View.VISIBLE);
            allCompanySearchList = new ArrayList<>();
            searchAdapter = new SearchAdapter(getContext(), allCompanySearchList);
            recyclerView.setAdapter(searchAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            mPresenter.getAllCompanyList();
            }else {
                changeFragment(new ErrorFragment());
            }


    }

    @OnClick(R.id.searchOneCompany)
    public void searchCompanyFunction(){
        getActivity().onSearchRequested();
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
    public void onGetAllCOmpanySearch(List<AllCompanySearch.CompanyData> list) {
        progressBar.setVisibility(View.GONE);
        for(int i = 0; i < list.size(); i++){
            allCompanySearchList.add(list.get(i));
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onErrorOccured() {
        progressBar.setVisibility(View.GONE);
        changeFragment(new ErrorFragment());
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
