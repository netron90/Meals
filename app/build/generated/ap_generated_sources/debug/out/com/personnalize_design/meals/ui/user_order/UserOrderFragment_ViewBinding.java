// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.user_order;

import android.view.View;
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

public class UserOrderFragment_ViewBinding implements Unbinder {
  private UserOrderFragment target;

  @UiThread
  public UserOrderFragment_ViewBinding(UserOrderFragment target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.todayDate = Utils.findRequiredViewAsType(source, R.id.todayDate, "field 'todayDate'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.explanationUserOrder = Utils.findRequiredViewAsType(source, R.id.explanationUserOrder, "field 'explanationUserOrder'", TextView.class);
    target.companyName = Utils.findRequiredViewAsType(source, R.id.companyName, "field 'companyName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserOrderFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.todayDate = null;
    target.recyclerView = null;
    target.explanationUserOrder = null;
    target.companyName = null;
  }
}
