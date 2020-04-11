package com.personnalize_design.meals.ui.meal_addition.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.meal_addition.AccompagnementScreen;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class MealSelectedAdapter extends RecyclerView.Adapter<MealSelectedAdapter.ViewHolder> {

    private Context context;
    private List<MainMealSelectedModel> mainMealList;
    private AccompagnementScreen mListener;

    public MealSelectedAdapter(Context context, List<MainMealSelectedModel> mainMealList) {
        this.context = context;
        this.mainMealList = mainMealList;
        if(context instanceof AccompagnementScreen)
        {
            mListener = (AccompagnementScreen) context;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_selected_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        if(position == 0){
//            holder.deleteMealSelect.setVisibility(View.GONE);
//        }
        GlideApp.with(this.context)
                .load(mainMealList.get(position).getMainMealImage())
                .placeholder(R.drawable.meals_logo_v2)
                .centerCrop()
                .into(holder.imgMealSelected);

        holder.mealPrice.setText(mainMealList.get(position).getMainMealPrice());


    }

    @Override
    public int getItemCount() {
        if(mainMealList.size() == 0 || mainMealList.isEmpty())
        {
            return 0;
        }else{
            return mainMealList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.imgMealSelected)
        private ImageView imgMealSelected;

//        @BindView(R.id.deleteMealSelect)
//        public RelativeLayout deleteMealSelect;

//        @BindView(R.id.mealPrice)
        private TextView mealPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMealSelected = (ImageView) itemView.findViewById(R.id.imgMealSelected);
            mealPrice        = (TextView) itemView.findViewById(R.id.mealPrice);

//            deleteMealSelect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int layoutPosition = getLayoutPosition();
//                    if(mListener != null)
//                    {
//                        mainMealList.remove(layoutPosition);
//                        mListener.onRemoveMealSelected();
//                    }
//                }
//            });

        }
    }

    public interface OnRemoveMealSelected{
        void onRemoveMealSelected();
    }
}
