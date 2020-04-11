package com.personnalize_design.meals.ui.catalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.CompanyCatalog;
import com.personnalize_design.meals.data.model.UserOrderModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainCatalogAdapter extends RecyclerView.Adapter<MainCatalogAdapter.ViewHolder> {

    private Context context;
    private List<CompanyCatalog.UserDataBean.CatalogBean> mListCatalog;

    public MainCatalogAdapter(Context context, List<CompanyCatalog.UserDataBean.CatalogBean> mListCatalog) {
        this.context = context;
        this.mListCatalog = mListCatalog;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_main_recyclerview_model, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.catalogCategoryName.setText(mListCatalog.get(position).getCategory());
        MealListAdapter mealListAdapter = new MealListAdapter(context, mListCatalog.get(position).getMealsList());
        holder.mealList.setAdapter(mealListAdapter);
        holder.mealList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.mealList.setHasFixedSize(true);
        holder.mealList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public int getItemCount() {
        if(mListCatalog.size() == 0){
            return 0;
        }else{
            return mListCatalog.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView catalogCategoryName;
        private RecyclerView mealList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catalogCategoryName = itemView.findViewById(R.id.catalogCategory);
            mealList = itemView.findViewById(R.id.catalogMealListRecyclerView);
        }
    }
}
