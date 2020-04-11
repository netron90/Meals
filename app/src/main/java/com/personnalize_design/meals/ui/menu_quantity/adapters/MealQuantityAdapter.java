package com.personnalize_design.meals.ui.menu_quantity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.base.OnGetMealsQuantity;
import com.personnalize_design.meals.ui.menu_quantity.MenuQuantityActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class MealQuantityAdapter extends RecyclerView.Adapter<MealQuantityAdapter.ViewHolder> {

    private List<MainMealSelectedModel> list;
    private Context context;
    private OnAddMealQuantity mListenerAdd;
    private OnRemoveMealQuantity mListenerRemove;
    private ViewHolder instance;


    public MealQuantityAdapter(List<MainMealSelectedModel> list, Context context) {
        this.list = list;
        this.context = context;
        if(context instanceof MenuQuantityActivity){
            this.mListenerAdd = (OnAddMealQuantity) context;
        }

        if(context instanceof MenuQuantityActivity){
            this.mListenerRemove = (OnRemoveMealQuantity) context;
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_quantity_item, null, false);
        instance = new ViewHolder(view);
        return instance;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(list.get(position).getMainMealImage())
                .placeholder(R.drawable.meals_logo_v2)
                .centerCrop().into(holder.mealSelectedImg);
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




    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mealSelectedImg;
        private TextView mealQuantityNbr;
        private RelativeLayout addQuantity;
        private RelativeLayout removeQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealSelectedImg = (ImageView) itemView.findViewById(R.id.mealSelectedImg);
            mealQuantityNbr = (TextView) itemView.findViewById(R.id.mealQuantityNbr);
            addQuantity = (RelativeLayout) itemView.findViewById(R.id.addQuantity);
            removeQuantity = (RelativeLayout) itemView.findViewById(R.id.removeQuantity);

            addQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = getLayoutPosition();
                    mListenerAdd.onAddMealQuantity(mealQuantityNbr, layoutPosition);
                }
            });

            removeQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = getLayoutPosition();
                    mListenerRemove.onRemoveMealQuantity(mealQuantityNbr, layoutPosition);
                }
            });
        }
    }

    public interface OnAddMealQuantity{
        void onAddMealQuantity(TextView mealQuantityNbr, int layoutPosition);
    }

    public interface OnRemoveMealQuantity{
        void onRemoveMealQuantity(TextView mealQuantityNbr, int layoutPosition);
    }




}
