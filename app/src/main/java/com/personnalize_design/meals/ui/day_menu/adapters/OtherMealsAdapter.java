package com.personnalize_design.meals.ui.day_menu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllCompanyModel;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.data.model.OtherDayMenuModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class OtherMealsAdapter extends RecyclerView.Adapter<OtherMealsAdapter.ViewHolder> {

    private List<OtherDayMenuModel.DataBean.OtherMenuBean> otherDayMenuModelList;
    private Context context;
    private String companyCoverImage;
    private String deliveryPrice;
    private MainScreenActivity mListener;

    public OtherMealsAdapter(List<OtherDayMenuModel.DataBean.OtherMenuBean> otherDayMenuModelList, Context context, String companyCoverImage, String deliveryPrice) {
        this.otherDayMenuModelList = otherDayMenuModelList;
        this.context = context;
        this.companyCoverImage = companyCoverImage;
        this.deliveryPrice = deliveryPrice;
        if(context instanceof MainScreenActivity)
        {
            this.mListener = (MainScreenActivity) context;
        }
    }

    @NonNull
    @Override
    public OtherMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_menu_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherMealsAdapter.ViewHolder holder, int position) {

        holder.otherMenuCompanyName.setText(otherDayMenuModelList.get(position).getCompanyName() + " - Menu");
        GlideApp.with(this.context)
                .load(BASE_URL+otherDayMenuModelList.get(position).getMealImg())
                .placeholder(R.drawable.logo)
                .centerCrop()
                .into(holder.otherMealImg);
        holder.otherMealName.setText(otherDayMenuModelList.get(position).getMealName());
        holder.otherMealPrice.setText(otherDayMenuModelList.get(position).getMealPrice());
    }

    @Override
    public int getItemCount() {
        if(otherDayMenuModelList.size() == 0 || otherDayMenuModelList == null)
        {
            return 0;
        }else {
            //Log.d("OTHER MENU ADAPTER", "ADAPTER SIZE OTHER MENU: " + otherDayMenuModelList.size());
            return otherDayMenuModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.otherMenu)
        private ImageView otherMealImg;

        //@BindView(R.id.otherMealName)
        private TextView otherMealName;

        //@BindView(R.id.otherMealPrice)
        private TextView otherMealPrice;

        //@BindView(R.id.backgroundMainMeal)
        private RelativeLayout backgroundMainMeal;

        //@BindView(R.id.otherMenuCompanyName)
        private TextView otherMenuCompanyName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            otherMealImg = (ImageView) itemView.findViewById(R.id.otherMenu);
            otherMealName = (TextView) itemView.findViewById(R.id.otherMealName);
            otherMealPrice = (TextView) itemView.findViewById(R.id.otherMealPrice);
            otherMenuCompanyName = (TextView) itemView.findViewById(R.id.otherMenuCompanyName);
            backgroundMainMeal = (RelativeLayout) itemView.findViewById(R.id.backgroundMainMeal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int layoutPosition = getLayoutPosition();
                    if(mListener != null){
                        MainMealSelectedModel mainMealSelectedModel = new MainMealSelectedModel();
                        mListener.onFloatingActionListener(setUpMainMealSelectedModel(mainMealSelectedModel, otherDayMenuModelList, layoutPosition, deliveryPrice));
                    }
                }
            });
        }
    }

    private MainMealSelectedModel setUpMainMealSelectedModel(MainMealSelectedModel mainMealSelectedModel, List<OtherDayMenuModel.DataBean.OtherMenuBean> allCompanyList, int layoutPosition, String deliveryPrices) {
        mainMealSelectedModel.setCompanyNameService(allCompanyList.get(layoutPosition).getCompanyName());
        mainMealSelectedModel.setMainMealImage(allCompanyList.get(layoutPosition).getMealImg());
        mainMealSelectedModel.setMainMealName(allCompanyList.get(layoutPosition).getMealName());
        mainMealSelectedModel.setMainMealPrice(allCompanyList.get(layoutPosition).getMealPrice());
        mainMealSelectedModel.setCompanyCoverImage(companyCoverImage);
        mainMealSelectedModel.setCompanyDeliveryPrice(deliveryPrice);
        return mainMealSelectedModel;
    }



}
