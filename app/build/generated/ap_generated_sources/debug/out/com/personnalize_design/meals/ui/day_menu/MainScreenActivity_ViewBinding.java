// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.day_menu;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainScreenActivity_ViewBinding implements Unbinder {
  private MainScreenActivity target;

  @UiThread
  public MainScreenActivity_ViewBinding(MainScreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainScreenActivity_ViewBinding(MainScreenActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.bottomNavigationView = Utils.findRequiredViewAsType(source, R.id.bottom_navigation_view, "field 'bottomNavigationView'", BottomNavigationView.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.rootContainer, "field 'relativeLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.bottomNavigationView = null;
    target.relativeLayout = null;
  }
}
