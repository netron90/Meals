package com.personnalize_design.meals.ui.user_order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.di.GlideApp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.ViewHolder> {

    private Context context;
    private List<UserOrderModel.MealListBean> listBeans;

    public UserOrderAdapter(Context context, List<UserOrderModel.MealListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_order, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(BASE_URL+listBeans.get(position).getImageDeLaBouf())
                .placeholder(R.drawable.logo)
                .centerCrop().into(holder.mealImage);

        holder.mealName.setText(listBeans.get(position).nomDeLaBouf);
        holder.mealQuantity.setText("Qte: " + listBeans.get(position).quantiteDeLaBouf);
        holder.mealPrice.setText(String.valueOf((Integer.valueOf(listBeans.get(position).prixUnitaireDeLaBouf.split("f")[0]) * Integer.valueOf(listBeans.get(position).quantiteDeLaBouf))));
    }

    @Override
    public int getItemCount() {
        if(listBeans == null || listBeans.isEmpty())
        {
            return 0;
        }else{
            return listBeans.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mealName;
        private TextView mealPrice;
        private TextView mealQuantity;
        private ImageView mealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealName = (TextView) itemView.findViewById(R.id.mealName);
            mealPrice = (TextView) itemView.findViewById(R.id.mealPrice);
            mealQuantity = (TextView) itemView.findViewById(R.id.mealQuantity);
            mealImage = (ImageView) itemView.findViewById(R.id.mealImage);
        }
    }
}
