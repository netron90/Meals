package com.personnalize_design.meals.ui.catalog.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.meal_addition.interfaces.CatalogMealOrder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder>{

    private Context context;
    private List<CompanyCatalog.UserDataBean.CatalogBean.MealsListBean> mealsListBeans;
    private CatalogMealOrder mListener;

    public MealListAdapter(Context context, List<CompanyCatalog.UserDataBean.CatalogBean.MealsListBean> mealsListBeans) {
        this.context = context;
        this.mealsListBeans = mealsListBeans;
        if(context instanceof MainScreenActivity){
            mListener = (MainScreenActivity) context;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_meal_list, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(this.context)
                .load(mealsListBeans.get(position).getMealImg())
                .placeholder(R.drawable.meals_logo_v2)
                .centerCrop()
                .into(holder.catalogMealImage);
        holder.catalogMealName.setText(mealsListBeans.get(position).getMealName());
        holder.catalogMealPrice.setText(mealsListBeans.get(position).getMealPrice());
    }

    @Override
    public int getItemCount() {
        if(mealsListBeans.size() == 0){
            return 0;
        }else{
            return mealsListBeans.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView catalogMealImage;
        private TextView  catalogMealName;
        private TextView  catalogMealPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catalogMealImage = itemView.findViewById(R.id.catalogMealImage);
            catalogMealName = itemView.findViewById(R.id.catalogMealName);
            catalogMealPrice = itemView.findViewById(R.id.catalogMealPrice);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getLayoutPosition();
//                    mListener.onCatalogMealOrder();
//                }
//            });

        }
    }
}
