// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.search;

import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFragment_ViewBinding implements Unbinder {
  private SearchFragment target;

  private View view7f080140;

  @UiThread
  public SearchFragment_ViewBinding(final SearchFragment target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.searchOneCompany, "field 'fabSearch' and method 'searchCompanyFunction'");
    target.fabSearch = Utils.castView(view, R.id.searchOneCompany, "field 'fabSearch'", FloatingActionButton.class);
    view7f080140 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.searchCompanyFunction();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.progressBar = null;
    target.fabSearch = null;

    view7f080140.setOnClickListener(null);
    view7f080140 = null;
  }
}
