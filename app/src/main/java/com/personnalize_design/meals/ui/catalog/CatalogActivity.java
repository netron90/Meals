package com.personnalize_design.meals.ui.catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.catalog.adapters.MainCatalogAdapter;
import com.personnalize_design.meals.ui.catalog.interfaces.CatalogMvpView;
import com.personnalize_design.meals.ui.catalog.presenters.CatalogPresenter;
import com.personnalize_design.meals.ui.error.ErrorFragment;

import javax.inject.Inject;

public class CatalogActivity extends BaseActivity implements CatalogMvpView, ErrorFragment.OnFragmentInteractionListener {

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @Inject
    public CatalogPresenter<CatalogActivity> mPresenter;

    @BindView(R.id.catalogMainRecyclerView)
    public RecyclerView catalogMainRecyclerView;

    private MainCatalogAdapter mainCatalogAdapter;

    @BindView(R.id.textNoCatalog)
    public TextView textNoCatalog;

    @BindView(R.id.catalog_empty)
    public ImageView catalogEmpty;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    private String intentCompanyCatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();

    }

    private void setUp(){
        setContentView(R.layout.activity_catalog);
        getComponent().inject(this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.Catalogue));
        setUnbinder(ButterKnife.bind(this));

        progressBar.setVisibility(View.VISIBLE);
        intentCompanyCatalog = getIntent().getExtras().getString("companyCatalog");
        mPresenter.getCompanyCatalog(intentCompanyCatalog);

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
        textNoCatalog.setText(R.string.company_catalog_not_defined);
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

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.getDataManager().setFragmentStateToShow(3);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
