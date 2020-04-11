package com.personnalize_design.meals.ui.main;

import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.ui.base.BaseActivity;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.main.interfaces.MainActivityMvpView;
import com.personnalize_design.meals.ui.main.presenter.MainActivityPresenter;
import com.personnalize_design.meals.ui.security.SecurityActivity;

import javax.inject.Inject;

import static com.personnalize_design.meals.constants.Mutils.ANIM_DURATION;

public class MainActivity extends BaseActivity implements MainActivityMvpView {

    @BindView(R.id.getStartBtn)
    public RelativeLayout getStartedBtn;

    @BindView(R.id.getStartCard)
    public CardView getStartCard;

    @BindView(R.id.mealsLogoBloc)
    public RelativeLayout mealsLogoBloc;

    ObjectAnimator objectAnimator;

    float[] tabAlpha = {0.4f, 0.90f};

    @Inject
    public MainActivityPresenter<MainActivity> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();

    }

    private void setUp() {
        setContentView(R.layout.activity_main);
        getComponent ().inject (this);
        mPresenter.onAttachView (this);
        setUnbinder (ButterKnife.bind (this));

        objectAnimator = ObjectAnimator.ofFloat(mealsLogoBloc, "alpha", tabAlpha);
        objectAnimator.setDuration(ANIM_DURATION);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                getStartCard.setVisibility(View.VISIBLE);
                super.onAnimationEnd(animation);
            }
        });
        objectAnimator.start();

    }

    @OnClick(R.id.getStartBtn)
    public void seeDayMenu(){
        startActivity(this, SecurityActivity.class);
        finish();
    }
}
