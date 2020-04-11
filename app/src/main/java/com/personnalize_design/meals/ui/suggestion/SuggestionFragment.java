package com.personnalize_design.meals.ui.suggestion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.services.CheckSuggestionTime;
import com.personnalize_design.meals.ui.base.BaseFragment;
import com.personnalize_design.meals.ui.error.ErrorFragment;
import com.personnalize_design.meals.ui.suggestion.interfaces.SuggestionMvpView;
import com.personnalize_design.meals.ui.suggestion.presenters.SuggestionPresenter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SuggestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SuggestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuggestionFragment extends BaseFragment implements SuggestionMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.noSubscription)
    public TextView noSubscription;

    @BindView(R.id.suggestionBlock)
    public RelativeLayout suggestionBlock;

    @BindView(R.id.suggestionText)
    public EditText suggestionText;

    @BindView(R.id.icon_send)
    public ImageView iconSend;

    @BindView(R.id.sendSuggestionBtn)
    public RelativeLayout sendSuggestionBtn;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @BindView(R.id.rootContainer)
    public RelativeLayout mRoot;

    @Inject
    public SuggestionPresenter<SuggestionFragment> mPresenter;

    private OnFragmentInteractionListener mListener;

    public SuggestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuggestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuggestionFragment newInstance(String param1, String param2) {
        SuggestionFragment fragment = new SuggestionFragment();
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
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);
        setUp(view);
        return view;
    }

    private void setUp(View view){
        setUnbinder(ButterKnife.bind(this, view));
        getComponent().inject(this);
        mPresenter.onAttachView(this);
//        suggestionBlock.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
//        mPresenter.checkCompanySuggestionEnable(mPresenter.getDataManager().getCopanyUserName());
        mPresenter.checkSuggestionTime();

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
    public void OnErrorAccessCodeValidation() {
        progressBar.setVisibility(View.GONE);
        changeFragment(new ErrorFragment());
    }

//    @Override
//    public void OnSuggestionNotEnable() {
//        progressBar.setVisibility(View.GONE);
//        noSubscription.setText(R.string.plan_name_text);
//        suggestionBlock.setVisibility(View.GONE);
//    }

//    @Override
//    public void OnSuggestionEnable(String planName) {
//        progressBar.setVisibility(View.GONE);
//        noSubscription.setText(R.string.suggestion_text_indication);
//        suggestionBlock.setVisibility(View.VISIBLE);
//        suggestionText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
//                if(after > 0){
//                    noSubscription.setText(getString(R.string.suggestion_text_indication));
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                if(noSubscription.getText().equals(getString(R.string.suggestion_request_send_successfully))){
////                    noSubscription.setText(getString(R.string.suggestion_text_indication));
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if(TextUtils.isEmpty(editable.toString())){
//                    sendSuggestionBtn.setEnabled(false);
//                    DrawableCompat.setTint(iconSend.getDrawable(), ContextCompat.getColor(getContext(), R.color.sendClearBtn));
//                }else{
//                    sendSuggestionBtn.setEnabled(true);
//                    DrawableCompat.setTint(iconSend.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorAccent));
//                }
//            }
//        });
//    }

    @Override
    public void onErrorOccured() {
        progressBar.setVisibility(View.GONE);
        showLongToast(getContext(), getString(R.string.error_occured_suggestion));
    }

    @Override
    public void SuggestionSendSuccessfully() {

        Log.d("MEAL SEND SUCCES", "Meqal send successfully END");
        progressBar.setVisibility(View.GONE);
        noSubscription.setVisibility(View.VISIBLE);
        noSubscription.setText(getString(R.string.suggestion_request_send_successfully));
        suggestionText.setText("");
        sendSuggestionBtn.setEnabled(false);
        DrawableCompat.setTint(iconSend.getDrawable(), ContextCompat.getColor(getContext(), R.color.sendClearBtn));
        mPresenter.getDataManager().setSendSuggestion(true);
        //start service to check if it is tomorow
        getActivity().startService(new Intent(getContext(), CheckSuggestionTime.class));
        //showLongToast(getContext(), getString(R.string.suggestion_request_send_successfully));
    }

    @Override
    public void isNotTimeSuggestion() {
        progressBar.setVisibility(View.GONE);
        noSubscription.setText(R.string.not_time_suggestion);
        suggestionBlock.setVisibility(View.GONE);
    }

    @Override
    public void isTimeSuggestion() {
        progressBar.setVisibility(View.GONE);
        noSubscription.setText(R.string.suggestion_text_indication);
        suggestionBlock.setVisibility(View.VISIBLE);
        suggestionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                if(after > 0){
                    noSubscription.setText(getString(R.string.suggestion_text_indication));
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(noSubscription.getText().equals(getString(R.string.suggestion_request_send_successfully))){
//                    noSubscription.setText(getString(R.string.suggestion_text_indication));
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(editable.toString())){
                    sendSuggestionBtn.setEnabled(false);
                    DrawableCompat.setTint(iconSend.getDrawable(), ContextCompat.getColor(getContext(), R.color.sendClearBtn));
                }else{
                    sendSuggestionBtn.setEnabled(true);
                    DrawableCompat.setTint(iconSend.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorAccent));
                }
            }
        });
    }

    @OnClick(R.id.sendSuggestionBtn)
    public void sendSuggestionBtn(){
        if(TextUtils.isEmpty(suggestionText.getText().toString())){
            showToast(getContext(), getString(R.string.suggest_edit_text_empty));
        }else{
            if(mPresenter.getDataManager().isSendSuggestionSet()){
                showSnackBar(mRoot, getString(R.string.suggestion_not_possible), Snackbar.LENGTH_LONG);
            }else{
                progressBar.setVisibility(View.VISIBLE);
                noSubscription.setVisibility(View.GONE);
                mPresenter.sendSuggestionMeal(suggestionText.getText().toString());


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
