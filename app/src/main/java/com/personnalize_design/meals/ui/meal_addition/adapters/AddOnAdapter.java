package com.personnalize_design.meals.ui.meal_addition.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AddOnMenuModel;
import com.personnalize_design.meals.data.model.MainMealSelectedModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.meal_addition.AccompagnementScreen;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class AddOnAdapter extends RecyclerView.Adapter<AddOnAdapter.ViewHolder> {

    private Context context;
    private List<AddOnMenuModel.DataBean.AccompagnementBean> addOnMenuList;
    private OnAddNewItem mListener;
    private OnDeleteNewItem mListenerDelete;
    private List<MainMealSelectedModel> tempArray;
    private String companyDeliveryPrices;

    public AddOnAdapter(List<AddOnMenuModel.DataBean.AccompagnementBean> addOnMenuList, Context context, String companyDeliveryPrice) {
        this.context = context;

        Log.d("CONTEXT VALUE", "Context Value: " + context);
        this.addOnMenuList = addOnMenuList;
        this.companyDeliveryPrices = companyDeliveryPrice;
        Log.d("CONTEXT INSTANCE", "Company Delivery Price AddOnAdapter: " + this.companyDeliveryPrices);

        if(context instanceof AccompagnementScreen)
        {
            this.mListener = (OnAddNewItem) context;
            this.mListenerDelete = (OnDeleteNewItem) context;
        }else{
            Log.d("CONTEXT INSTANCE", "Company Delivery Price AddOnAdapter: " + this.companyDeliveryPrices);
        }
        tempArray = new ArrayList<>();
        for(int i = 0; i < addOnMenuList.size(); i++){
            tempArray.add(new MainMealSelectedModel(addOnMenuList.get(i).getAddOnName(), addOnMenuList.get(i).getAddOnImg(), addOnMenuList.get(i).getAddOnPrice(), companyDeliveryPrices));
            Log.d("CONTEXT INSTANCE", "Company Delivery Price AddOnAdapter: " + tempArray.get(i).getCompanyDeliveryPrice());

        }
        //else
//        {
//            Log.d("CONTEXT INSTANCE", "mListener is not instance of Accompagnement: " + mListenerDelete);
//        }

//        if(context instanceof AccompagnementScreen){
//            Log.d("CONTEXT INSTANCE", "mListenerDelete instance of Accompagnement: " + mListener);
//            this.mListenerDelete = (AccompagnementScreen) context;
//        }else{
//            Log.d("CONTEXT INSTANCE", "mListenerDelete is not instance of Accompagnement: " + mListenerDelete);
//        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addon_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(BASE_URL+addOnMenuList.get(position).getAddOnImg())
                .placeholder(R.drawable.logo)
                .centerCrop().into(holder.addOnImg);

        holder.addOnName.setText(addOnMenuList.get(position).getAddOnName());
        holder.addOnPrice.setText(addOnMenuList.get(position).getAddOnPrice());
    }

    @Override
    public int getItemCount() {
        if(addOnMenuList.size() == 0 || addOnMenuList.isEmpty()){
            return 0;
        }else{
            return addOnMenuList.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.otherMenu)
        private ImageView addOnImg;

//        @BindView(R.id.mealSelected)
        private RelativeLayout addOnSelected;

//        @BindView(R.id.addOnName)
        private TextView addOnName;

//        @BindView(R.id.addOnPrice)
        private TextView addOnPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addOnImg = (ImageView) itemView.findViewById(R.id.otherMenu);
            addOnSelected = (RelativeLayout) itemView.findViewById(R.id.mealSelected);
            addOnName = (TextView) itemView.findViewById(R.id.addOnName);
            addOnPrice = (TextView) itemView.findViewById(R.id.addOnPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = getLayoutPosition();

                    if(addOnSelected.getVisibility() == View.GONE){
                        addOnSelected.setVisibility(View.VISIBLE);

                        if(mListener != null){
                            Log.d("ADD_ON ADAPTER", "mLister is not null. Item Selected");
                            mListener.onAddNewItem(tempArray.get(layoutPosition));
                        }
//                        else{
//                            Log.d("ADD_ON ADAPTER", "mLister is null. Item Selected");
//                        }
                    }else{
                        addOnSelected.setVisibility(View.GONE);
                        if(mListenerDelete != null){
                            Log.d("ADD_ON ADAPTER", "mListerDelete is not null. Item Selected");

                            //MainMealSelectedModel mainMealSelectedModel = tempArray.get(layoutPosition);
                            //tempArray.remove(layoutPosition);

                            mListenerDelete.onDeleteNewItem(tempArray.get(layoutPosition));

                            Log.d("ADD_ON ADAPTER", "Nouvelle Taille du tableau temp: " + tempArray.size());
                        }
//                        else{
//                            Log.d("ADD_ON ADAPTER", "mListerDelete is null. Item Selected");
//                        }
                    }
                }
            });
        }
    }

    public interface OnAddNewItem{
        void onAddNewItem(MainMealSelectedModel mainMealSelectedModel);
    }

    public interface OnDeleteNewItem{
        void onDeleteNewItem(MainMealSelectedModel obj);
    }
}
