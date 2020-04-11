// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.suggestion;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.personnalize_design.meals.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SuggestionFragment_ViewBinding implements Unbinder {
  private SuggestionFragment target;

  private View view7f08014f;

  @UiThread
  public SuggestionFragment_ViewBinding(final SuggestionFragment target, View source) {
    this.target = target;

    View view;
    target.noSubscription = Utils.findRequiredViewAsType(source, R.id.noSubscription, "field 'noSubscription'", TextView.class);
    target.suggestionBlock = Utils.findRequiredViewAsType(source, R.id.suggestionBlock, "field 'suggestionBlock'", RelativeLayout.class);
    target.suggestionText = Utils.findRequiredViewAsType(source, R.id.suggestionText, "field 'suggestionText'", EditText.class);
    target.iconSend = Utils.findRequiredViewAsType(source, R.id.icon_send, "field 'iconSend'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.sendSuggestionBtn, "field 'sendSuggestionBtn' and method 'sendSuggestionBtn'");
    target.sendSuggestionBtn = Utils.castView(view, R.id.sendSuggestionBtn, "field 'sendSuggestionBtn'", RelativeLayout.class);
    view7f08014f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendSuggestionBtn();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.mRoot = Utils.findRequiredViewAsType(source, R.id.rootContainer, "field 'mRoot'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SuggestionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.noSubscription = null;
    target.suggestionBlock = null;
    target.suggestionText = null;
    target.iconSend = null;
    target.sendSuggestionBtn = null;
    target.progressBar = null;
    target.mRoot = null;

    view7f08014f.setOnClickListener(null);
    view7f08014f = null;
  }
}
