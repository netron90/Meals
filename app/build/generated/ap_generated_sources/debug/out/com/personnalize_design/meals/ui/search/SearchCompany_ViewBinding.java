// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.search;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchCompany_ViewBinding implements Unbinder {
  private SearchCompany target;

  @UiThread
  public SearchCompany_ViewBinding(SearchCompany target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchCompany_ViewBinding(SearchCompany target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.container, "field 'relativeLayout'", RelativeLayout.class);
    target.searchNothingToShwo = Utils.findRequiredViewAsType(source, R.id.searchNothingToShwo, "field 'searchNothingToShwo'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchCompany target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.recyclerView = null;
    target.relativeLayout = null;
    target.searchNothingToShwo = null;
    target.toolbar = null;
  }
}
