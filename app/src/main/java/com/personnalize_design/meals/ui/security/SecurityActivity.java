package com.personnalize_design.meals.ui.security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.security.interfaces.SecurityMvpView;
import com.personnalize_design.meals.ui.security.presenter.SecurityPresenter;

import javax.inject.Inject;

public class SecurityActivity extends BaseActivity implements SecurityMvpView, ErrorFragment.OnFragmentInteractionListener {

    @Inject
    public SecurityPresenter<SecurityActivity> mPresenter;

    @BindView(R.id.accessCode)
    public EditText accessCode;

    @BindView(R.id.accessCodeValidation)
    public RelativeLayout accessCodeValidation;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @BindView(R.id.rootContainer)
    public LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        setContentView(R.layout.activity_security);
        getComponent ().inject (this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));
        accessCodeValidation.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        if(mPresenter.getDataManager().getUserAccessCode().equals("")){
            progressBar.setVisibility(View.GONE);
            accessCodeValidation.setVisibility(View.VISIBLE);
        }else{
            if(isNetworkAvailable()){
                mPresenter.checkAccessCode(mPresenter.getDataManager().getUserAccessCode());
            }else{
                progressBar.setVisibility(View.GONE);
                showSnackBar(linearLayout, getString(R.string.internet_acces_code), Snackbar.LENGTH_LONG);
                showToast(this, getString(R.string.internet_acces_code));
                accessCodeValidation.setVisibility(View.VISIBLE);
            }

        }
    }

    @OnClick(R.id.accessCodeValidation)
    public void accessCodeValidation(){
        String accessCodeText = accessCode.getText().toString();

        if(TextUtils.isEmpty(accessCodeText)){
            showToast(this, getString(R.string.field_empty));
        }else{
            if(isNetworkAvailable()){
                progressBar.setVisibility(View.VISIBLE);
                mPresenter.checkAccessCode(accessCodeText);
            }else{
                changeFragment(new ErrorFragment());
            }

        }
    }

    @Override
    public void OnErrorAccessCodeValidation() {
        progressBar.setVisibility(View.GONE);
        changeFragment(new ErrorFragment());
//        showToast(this, getResources().getString(R.string.error_occured_v2));
    }

    @Override
    public void OnSuccessAccessCodeValidation() {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnAccessCodeValidationIncorrect() {
        progressBar.setVisibility(View.GONE);
        showDiaologBox(this, getString(R.string.no_matching_result), getResources().getString(R.string.incorrect_access_code));
        //showToast(this, getResources().getString(R.string.incorrect_access_code));
    }

    @Override
    public void OnSubscriptionEnd() {
        progressBar.setVisibility(View.GONE);
        showDiaologBox(this, getString(R.string.subscription_end_title), getResources().getString(R.string.subscription_end));
        //showToast(this, getResources().getString(R.string.incorrect_access_code));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
