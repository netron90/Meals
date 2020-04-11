// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.promotion;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoPromotionFragment_ViewBinding implements Unbinder {
  private NoPromotionFragment target;

  @UiThread
  public NoPromotionFragment_ViewBinding(NoPromotionFragment target, View source) {
    this.target = target;

    target.noPromotionText = Utils.findRequiredViewAsType(source, R.id.noPromotionText, "field 'noPromotionText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NoPromotionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.noPromotionText = null;
  }
}
