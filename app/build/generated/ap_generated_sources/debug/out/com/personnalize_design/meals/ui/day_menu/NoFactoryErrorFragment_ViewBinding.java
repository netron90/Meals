// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.day_menu;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoFactoryErrorFragment_ViewBinding implements Unbinder {
  private NoFactoryErrorFragment target;

  private View view7f0800fc;

  @UiThread
  public NoFactoryErrorFragment_ViewBinding(final NoFactoryErrorFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ok_btn, "field 'okBtn' and method 'noFactoryFindOkBtn'");
    target.okBtn = Utils.castView(view, R.id.ok_btn, "field 'okBtn'", Button.class);
    view7f0800fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.noFactoryFindOkBtn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    NoFactoryErrorFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.okBtn = null;

    view7f0800fc.setOnClickListener(null);
    view7f0800fc = null;
  }
}
