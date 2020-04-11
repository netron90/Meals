package com.personnalize_design.meals.ui.catalog;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.catalog.adapters.MainCatalogAdapter;
import com.personnalize_design.meals.ui.catalog.interfaces.CatalogMvpView;
import com.personnalize_design.meals.ui.catalog.presenters.CatalogPresenter;
import com.personnalize_design.meals.ui.error.ErrorFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatalogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatalogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogFragment extends BaseFragment implements CatalogMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @Inject
    public CatalogPresenter<CatalogFragment> mPresenter;

    @BindView(R.id.catalogMainRecyclerView)
    public RecyclerView catalogMainRecyclerView;

    private MainCatalogAdapter mainCatalogAdapter;

//    @BindView(R.id.catalogMealListRecyclerView)
//    public RecyclerView catalogMealListRecyclerView;

    @BindView(R.id.textNoCatalog)
    public TextView textNoCatalog;

    @BindView(R.id.catalog_empty)
    public ImageView catalogEmpty;

    public CatalogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogFragment newInstance(String param1, String param2) {
        CatalogFragment fragment = new CatalogFragment();
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
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        setUp(view);
        return view;
    }

    private void setUp(View view){
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().inject(this);
        mPresenter.onAttachView(this);
        progressBar.setVisibility(View.VISIBLE);
        mPresenter.getCompanyCatalog(mPresenter.getDataManager().getCopanyUserName());

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
    public void OnNoUserFound() {
        progressBar.setVisibility(View.GONE);
        catalogMainRecyclerView.setVisibility(View.GONE);
//        catalogMealListRecyclerView.setVisibility(View.GONE);
        catalogEmpty.setVisibility(View.VISIBLE);
        textNoCatalog.setVisibility(View.VISIBLE);
        textNoCatalog.setText("Désolé. Cette compagnie n'a pas souscrit à l'option de proposition de catalogue");
    }

    @Override
    public void OnCatalogFound(CompanyCatalog.UserDataBean userData) {
        progressBar.setVisibility(View.GONE);
        catalogEmpty.setVisibility(View.GONE);
        textNoCatalog.setVisibility(View.GONE);
        mainCatalogAdapter = new MainCatalogAdapter(getContext(), userData.getCatalog());
        catalogMainRecyclerView.setAdapter(mainCatalogAdapter);
        catalogMainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        catalogMainRecyclerView.setHasFixedSize(true);
        catalogMainRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
