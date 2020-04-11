// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.menu_quantity;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MenuQuantityActivity_ViewBinding implements Unbinder {
  private MenuQuantityActivity target;

  private View view7f080052;

  @UiThread
  public MenuQuantityActivity_ViewBinding(MenuQuantityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MenuQuantityActivity_ViewBinding(final MenuQuantityActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerViewQuantity = Utils.findRequiredViewAsType(source, R.id.recyclerViewQuantity, "field 'recyclerViewQuantity'", RecyclerView.class);
    target.recyclerViewBilan = Utils.findRequiredViewAsType(source, R.id.recyclerViewBilan, "field 'recyclerViewBilan'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btnOrderNow, "field 'startOrder' and method 'showBilanMealDialogBox'");
    target.startOrder = Utils.castView(view, R.id.btnOrderNow, "field 'startOrder'", Button.class);
    view7f080052 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showBilanMealDialogBox();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MenuQuantityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerViewQuantity = null;
    target.recyclerViewBilan = null;
    target.startOrder = null;
    target.progressBar = null;

    view7f080052.setOnClickListener(null);
    view7f080052 = null;
  }
}
