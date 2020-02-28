// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.day_menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personnalize_design.meals.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DayMenuFragment_ViewBinding implements Unbinder {
  private DayMenuFragment target;

  private View view7f08009e;

  @UiThread
  public DayMenuFragment_ViewBinding(final DayMenuFragment target, View source) {
    this.target = target;

    View view;
    target.todayDate = Utils.findRequiredViewAsType(source, R.id.todayDate, "field 'todayDate'", TextView.class);
    target.companyName = Utils.findRequiredViewAsType(source, R.id.companyName, "field 'companyName'", TextView.class);
    target.mainMealImg = Utils.findRequiredViewAsType(source, R.id.mainMeal, "field 'mainMealImg'", ImageView.class);
    target.mainMealName = Utils.findRequiredViewAsType(source, R.id.mainMealName, "field 'mainMealName'", TextView.class);
    target.mainMealPrice = Utils.findRequiredViewAsType(source, R.id.mainMealPrice, "field 'mainMealPrice'", TextView.class);
    target.otherMenuToday = Utils.findRequiredViewAsType(source, R.id.otherMenuToday, "field 'otherMenuToday'", TextView.class);
    target.backgroundMainMeal = Utils.findRequiredViewAsType(source, R.id.backgroundMainMeal, "field 'backgroundMainMeal'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.floatingButton, "field 'floatingActionButton' and method 'fabBtnPressed'");
    target.floatingActionButton = Utils.castView(view, R.id.floatingButton, "field 'floatingActionButton'", FloatingActionButton.class);
    view7f08009e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.fabBtnPressed();
      }
    });
    target.discreteScrollView = Utils.findRequiredViewAsType(source, R.id.discrete_scroll_view, "field 'discreteScrollView'", DiscreteScrollView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefresh, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DayMenuFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.todayDate = null;
    target.companyName = null;
    target.mainMealImg = null;
    target.mainMealName = null;
    target.mainMealPrice = null;
    target.otherMenuToday = null;
    target.backgroundMainMeal = null;
    target.floatingActionButton = null;
    target.discreteScrollView = null;
    target.swipeRefreshLayout = null;

    view7f08009e.setOnClickListener(null);
    view7f08009e = null;
  }
}
