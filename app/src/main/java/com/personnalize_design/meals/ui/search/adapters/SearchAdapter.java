package com.personnalize_design.meals.ui.search.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllCompanySearch;
//import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.catalog.CatalogActivity;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;
import com.personnalize_design.meals.ui.search.interfaces.OnCompanyWorkinDay;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<AllCompanySearch.CompanyData> companyDataList;
    private DayWorkinAdapter dayWorkinAdapter;

    private OnCompanySelected mListener;
    private OnCompanyWorkinDay mListener2;
    private List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> mList;


    public SearchAdapter(Context context, List<AllCompanySearch.CompanyData> companyDataList) {
        this.context = context;
        this.companyDataList = companyDataList;
        if(context instanceof MainScreenActivity){
            mListener = (MainScreenActivity) context;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company_search, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GlideApp.with(this.context)
                .load(companyDataList.get(position).getCompanyCoverImage())
                .placeholder(R.drawable.logo)
                .centerCrop()
                .into(holder.companyCoverImage);
        holder.companyName.setText(companyDataList.get(position).getCompanyName());
        holder.companyPhoneNumber.setText(companyDataList.get(position).getCompanyContact());
        holder.companyEmail.setText(companyDataList.get(position).getCompanyMail());
        dayWorkinAdapter = new DayWorkinAdapter(context, companyDataList.get(position).getCompanyWorkinDay());
        mListener2 = dayWorkinAdapter;
        holder.dayWorkin.setAdapter(dayWorkinAdapter);
        holder.dayWorkin.setHasFixedSize(true);
        holder.dayWorkin.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.dayWorkin.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public int getItemCount() {
        if(companyDataList.size() == 0 || companyDataList == null)
        {
            return 0;
        }else{
            return companyDataList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView companyName;
        private TextView companyPhoneNumber;
        private TextView companyEmail;
        private ImageView companyCoverImage;
        private RelativeLayout companyDataBlock;
        private RecyclerView dayWorkin;
        private RelativeLayout catalogBtn;
        private RelativeLayout orderBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = (TextView) itemView.findViewById(R.id.companyName);
            companyPhoneNumber = (TextView) itemView.findViewById(R.id.companyPhoneNumber);
            companyEmail = (TextView) itemView.findViewById(R.id.companyEmail);
            companyCoverImage = (ImageView) itemView.findViewById(R.id.companyImageCover);
            catalogBtn = (RelativeLayout) itemView.findViewById(R.id.catalogBtn);
            orderBtn = (RelativeLayout) itemView.findViewById(R.id.orderBtn);
            dayWorkin = (RecyclerView) itemView.findViewById(R.id.recyclerView);

            catalogBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CatalogActivity.class);
                    intent.putExtra("companyCatalog", companyDataList.get(getLayoutPosition()).getCompanyName());
                    context.startActivity(intent);
                }
            });

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener2 != null){
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_WEEK);

                        String todayDay = "";

                        if(day == Calendar.SUNDAY){
                            todayDay = "Dimanche";
                        }else if(day == Calendar.MONDAY){
                            todayDay = "Lundi";
                        }else if(day == Calendar.TUESDAY){
                            todayDay = "Mardi";
                        }else if(day == Calendar.WEDNESDAY){
                            todayDay = "Mercredi";
                        }else if(day == Calendar.THURSDAY){
                            todayDay = "Jeudi";
                        }else if(day == Calendar.FRIDAY){
                            todayDay = "Vendredi";
                        }else{
                            todayDay = "Samedi";
                        }

                        mList = mListener2.onGetWorkinDay();
                        for(int i = 0; i < mList.size(); i++){
                            if(todayDay.equals(mList.get(i).getDayName())){

                                Log.d("TODAY DAY", "TODAY DAY: " + todayDay + "\nDay List: " + mList.get(i).getDayName());
                                mListener.onCompanySelected(companyDataList.get(getLayoutPosition()).getCompanyName());
                                break;
                            }else{
                                Log.d("NOT TODAY DAY", "You can't pass order: " + todayDay + "\nDay List: " + mList.get(i).getDayName());
                                if(i == mList.size() - 1){
                                    mListener.onCanNotPassOrder();
                                }

                            }
                        }
                    }


                }
            });

//
//            companyDataBlock.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int layoutPosition = getLayoutPosition();
//
//                    if(mListener != null){
//                        Log.d("SEARCH ADAPTER", "Search adapter company name:" + companyDataList.get(layoutPosition).getCompanyName());
//                        mListener.onCompanySelected(companyDataList.get(layoutPosition).getCompanyName());
//                    }
//                }
//            });

         }
    }

    public interface OnCompanySelected{
        void onCompanySelected(String companyName);
        void onCanNotPassOrder();
    }


}
