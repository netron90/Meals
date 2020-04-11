// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.security;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SecurityActivity_ViewBinding implements Unbinder {
  private SecurityActivity target;

  private View view7f08000b;

  @UiThread
  public SecurityActivity_ViewBinding(SecurityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SecurityActivity_ViewBinding(final SecurityActivity target, View source) {
    this.target = target;

    View view;
    target.accessCode = Utils.findRequiredViewAsType(source, R.id.accessCode, "field 'accessCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.accessCodeValidation, "field 'accessCodeValidation' and method 'accessCodeValidation'");
    target.accessCodeValidation = Utils.castView(view, R.id.accessCodeValidation, "field 'accessCodeValidation'", RelativeLayout.class);
    view7f08000b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.accessCodeValidation();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.linearLayout = Utils.findRequiredViewAsType(source, R.id.rootContainer, "field 'linearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SecurityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.accessCode = null;
    target.accessCodeValidation = null;
    target.progressBar = null;
    target.linearLayout = null;

    view7f08000b.setOnClickListener(null);
    view7f08000b = null;
  }
}
