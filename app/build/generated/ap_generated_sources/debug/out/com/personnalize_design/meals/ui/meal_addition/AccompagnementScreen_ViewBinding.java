// Generated code from Butter Knife. Do not modify!
package com.personnalize_design.meals.ui.meal_addition;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class AccompagnementScreen_ViewBinding implements Unbinder {
  private AccompagnementScreen target;

  private View view7f08006b;

  @UiThread
  public AccompagnementScreen_ViewBinding(AccompagnementScreen target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccompagnementScreen_ViewBinding(final AccompagnementScreen target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.companyCoverPicture = Utils.findRequiredViewAsType(source, R.id.companyImageCover, "field 'companyCoverPicture'", ImageView.class);
    target.companyName = Utils.findRequiredViewAsType(source, R.id.companyName, "field 'companyName'", TextView.class);
    target.mealSelectedRv = Utils.findRequiredViewAsType(source, R.id.recyclerViewMeal, "field 'mealSelectedRv'", RecyclerView.class);
    target.addOnRecView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'addOnRecView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.chooseMenuQuantity, "field 'chooseMenuQuantity' and method 'goToMenuQuantityChosen'");
    target.chooseMenuQuantity = Utils.castView(view, R.id.chooseMenuQuantity, "field 'chooseMenuQuantity'", Button.class);
    view7f08006b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToMenuQuantityChosen();
      }
    });
    target.accompagnementText = Utils.findRequiredViewAsType(source, R.id.textAccompagnement, "field 'accompagnementText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AccompagnementScreen target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.companyCoverPicture = null;
    target.companyName = null;
    target.mealSelectedRv = null;
    target.addOnRecView = null;
    target.chooseMenuQuantity = null;
    target.accompagnementText = null;

    view7f08006b.setOnClickListener(null);
    view7f08006b = null;
  }
}
