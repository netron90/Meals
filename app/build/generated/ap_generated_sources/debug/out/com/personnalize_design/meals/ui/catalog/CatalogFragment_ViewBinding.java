// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.catalog;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CatalogFragment_ViewBinding implements Unbinder {
  private CatalogFragment target;

  @UiThread
  public CatalogFragment_ViewBinding(CatalogFragment target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.catalogMainRecyclerView = Utils.findRequiredViewAsType(source, R.id.catalogMainRecyclerView, "field 'catalogMainRecyclerView'", RecyclerView.class);
    target.textNoCatalog = Utils.findRequiredViewAsType(source, R.id.textNoCatalog, "field 'textNoCatalog'", TextView.class);
    target.catalogEmpty = Utils.findRequiredViewAsType(source, R.id.catalog_empty, "field 'catalogEmpty'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CatalogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.catalogMainRecyclerView = null;
    target.textNoCatalog = null;
    target.catalogEmpty = null;
  }
}
