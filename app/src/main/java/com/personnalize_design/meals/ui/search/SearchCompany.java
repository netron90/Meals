package com.personnalize_design.meals.ui.search;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.search.adapters.OneCompanySearchAdapter;
import com.personnalize_design.meals.ui.search.interfaces.SearchCompanyMvpView;
import com.personnalize_design.meals.ui.search.presenter.SearchCompanyPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchCompany extends BaseActivity implements SearchCompanyMvpView,
        OneCompanySearchAdapter.OnOneCompanySelected, ErrorFragment.OnFragmentInteractionListener,
        OneCompanySearchAdapter.OnNoDataFound
{

    @Inject
    SearchCompanyPresenter<SearchCompany> mPresenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.container)
    RelativeLayout relativeLayout;

    @BindView(R.id.searchNothingToShwo)
    TextView searchNothingToShwo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    OneCompanySearchAdapter oneCompanySearchAdapter;

    List<OneCompanySearchModel> oneCompanySearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {

        setContentView(R.layout.activity_searchable);
        getComponent ().inject (this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.search_toolbar_title);
        if(isNetworkAvailable()){
            searchNothingToShwo.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            oneCompanySearchList = new ArrayList<>();
            oneCompanySearchAdapter = new OneCompanySearchAdapter(oneCompanySearchList, this);
            recyclerView.setAdapter(oneCompanySearchAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            Intent intent = getIntent();
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                String query = intent.getStringExtra(SearchManager.QUERY);
                mPresenter.getOneCompanySearch(query);
            }else{
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                searchNothingToShwo.setVisibility(View.VISIBLE);
                searchNothingToShwo.setText(R.string.nothing_to_show);
            }

        }else {
            recyclerView.setVisibility(View.GONE);
            searchNothingToShwo.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            changeFragment(new ErrorFragment());
        }

    }


    @Override
    public void onCompanySelected(String companyName) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
        mPresenter.getDataManager().setFragmentStateToShow(0);
        mPresenter.getDataManager().setSearchFragmentEnable(true);
        Intent intentDayFragment = new Intent(this, MainScreenActivity.class);
        intentDayFragment.putExtra("arg_fragment_day_menu", companyName);
        startActivity(intentDayFragment);
        finish();
//        changeFragment(new DayMenuFragment(), companyName);
    }

    @Override
    public void onErrorOccured() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        changeFragment(new ErrorFragment());
    }

    @Override
    public void onFoundOneCOmpanySuccess(OneCompanySearchModel oneCompanySearchModel) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.GONE);
        searchNothingToShwo.setVisibility(View.GONE);
        oneCompanySearchList.add(oneCompanySearchModel);
        oneCompanySearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoCompanyFound() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.GONE);
        searchNothingToShwo.setVisibility(View.VISIBLE);
        searchNothingToShwo.setText(R.string.nothing_to_show);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onNoDataFound() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.GONE);
        searchNothingToShwo.setVisibility(View.VISIBLE);
        searchNothingToShwo.setText(R.string.nothing_to_show);
    }
}
