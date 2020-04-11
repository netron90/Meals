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
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class MainMealsAdapter extends RecyclerView.Adapter<MainMealsAdapter.ViewHolder> {

    private List<AllCompanyModel.DataBean> otherDayMenuModelList;
    private Context context;
    private MainScreenActivity mListener;

    public MainMealsAdapter(List<AllCompanyModel.DataBean> otherDayMenuModelList, Context context) {
        this.otherDayMenuModelList = otherDayMenuModelList;
        this.context = context;
        if(context instanceof MainScreenActivity)
        {
            this.mListener = (MainScreenActivity) context;
        }

    }

    @NonNull
    @Override
    public MainMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_menu_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMealsAdapter.ViewHolder holder, int position) {

        holder.otherMenuCompanyName.setText(otherDayMenuModelList.get(position).getUsername() + " - Menu");
        GlideApp.with(this.context)
                .load(otherDayMenuModelList.get(position).getMainMealImg())
                .placeholder(R.drawable.meals_logo_v2)
                .centerCrop()
                .into(holder.otherMealImg);
        holder.otherMealName.setText(otherDayMenuModelList.get(position).getMainMealName());
        holder.otherMealPrice.setText(otherDayMenuModelList.get(position).getMainMealPrice());
    }

    @Override
    public int getItemCount() {
        if(otherDayMenuModelList.size() == 0 || otherDayMenuModelList == null)
        {
            return 0;
        }else {
            //og.d("OTHER MENU ADAPTER", "ADAPTER SIZE OTHER MENU: " + otherDayMenuModelList.size());
            return otherDayMenuModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.otherMenu)
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
                        mListener.onFloatingActionListener(setUpMainMealSelectedModel(mainMealSelectedModel, otherDayMenuModelList, layoutPosition));
                    }
                }
            });
        }
    }

    private MainMealSelectedModel setUpMainMealSelectedModel(MainMealSelectedModel mainMealSelectedModel, List<AllCompanyModel.DataBean> allCompanyList, int layoutPosition) {
        mainMealSelectedModel.setCompanyNameService(allCompanyList.get(layoutPosition).getUsername());
        mainMealSelectedModel.setMainMealImage(allCompanyList.get(layoutPosition).getMainMealImg());
        mainMealSelectedModel.setMainMealName(allCompanyList.get(layoutPosition).getMainMealName());
        mainMealSelectedModel.setMainMealPrice(allCompanyList.get(layoutPosition).getMainMealPrice());
        mainMealSelectedModel.setCompanyCoverImage(allCompanyList.get(layoutPosition).getCoverImagePath());
        return mainMealSelectedModel;
    }

    public interface SendUserToNextStep{
        void onSendUserToNextStep();
    }
}
