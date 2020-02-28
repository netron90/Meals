package com.personnalize_design.meals.ui.menu_quantity.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.MealBilanModel;
import com.personnalize_design.meals.ui.base.OnGetMealsQuantity;
import com.personnalize_design.meals.ui.menu_quantity.MenuQuantityActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BilanItemAdapter extends RecyclerView.Adapter<BilanItemAdapter.ViewHolder> implements
        MenuQuantityActivity.OnSetMealQuantity, MenuQuantityActivity.OnGetTotalMealPrice, OnGetMealsQuantity {

    private List<MealBilanModel> list;
    private Context context;
    private ViewHolder instance;
    private ArrayList<String> mealBasePrice;

    public BilanItemAdapter(List<MainMealSelectedModel> lists, Context context) {

        this.list = new ArrayList<>();
        mealBasePrice = new ArrayList();
        for(int i = 0; i < lists.size(); i++){
            this.list.add(new MealBilanModel(lists.get(i).getMainMealName(), "1", lists.get(i).getMainMealPrice()));
            mealBasePrice.add(this.list.get(i).getMealPrice().split("f")[0]);
        }
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bilan_item_quantity, null, false);
        instance = new ViewHolder(view);
        return instance;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mealName.setText(list.get(position).getMealName());
        holder.mealPrice.setText(list.get(position).getMealPrice());
        holder.mealQuantity.setText(list.get(position).getMealQuantity());
    }

    @Override
    public int getItemCount() {
        if(list == null || list.size() == 0)
        {
            return 0;
        }else{
            return list.size();
        }
    }

    @Override
    public void onSetMealQuantity(int mealQuantity, int layoutPosition) {
        if(instance instanceof ViewHolder){
            Log.d("VIEW HOLDER INSTANCE", "instance is Instance of View Holder");
            if(instance != null){
                //String[] mealPriceTab = this.list.get(layoutPosition).getMealPrice().split("f");
                int newMealPrice = Integer.valueOf(this.mealBasePrice.get(layoutPosition)) * mealQuantity;
                this.list.get(layoutPosition).setMealQuantity(String.valueOf(mealQuantity));
                this.list.get(layoutPosition).setMealPrice(newMealPrice + "f CFA");
                //instance.mealQuantity.setText(String.valueOf(mealQuantity));
            }
        }else {
            Log.d("VIEW HOLDER INSTANCE", "instance is Instance of View Holder");
        }
    }

    @Override
    public String onGetTotalMealPrice() {
        int mealTotalPrice = 0;
        for(int i = 0; i < list.size(); i++){
            mealTotalPrice = mealTotalPrice + Integer.valueOf(list.get(i).getMealPrice().split("f")[0]);

        }
        return String.valueOf(mealTotalPrice);
    }

    @Override
    public ArrayList<String> onGetMealsQuantity() {

        ArrayList<String> mealsQuantity = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            mealsQuantity.add(list.get(i).getMealQuantity());
        }


        return mealsQuantity;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mealName;
        private TextView mealQuantity;
        private TextView mealPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealName = (TextView) itemView.findViewById(R.id.mealName);
            mealPrice = (TextView) itemView.findViewById(R.id.mealPrice);
            mealQuantity = (TextView) itemView.findViewById(R.id.mealQuantity);
        }
    }


}
