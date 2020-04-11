// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.main;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f0800b2;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.getStartBtn, "field 'getStartedBtn' and method 'seeDayMenu'");
    target.getStartedBtn = Utils.castView(view, R.id.getStartBtn, "field 'getStartedBtn'", RelativeLayout.class);
    view7f0800b2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.seeDayMenu();
      }
    });
    target.getStartCard = Utils.findRequiredViewAsType(source, R.id.getStartCard, "field 'getStartCard'", CardView.class);
    target.mealsLogoBloc = Utils.findRequiredViewAsType(source, R.id.mealsLogoBloc, "field 'mealsLogoBloc'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.getStartedBtn = null;
    target.getStartCard = null;
    target.mealsLogoBloc = null;

    view7f0800b2.setOnClickListener(null);
    view7f0800b2 = null;
  }
}
