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
import com.personnalize_design.meals.data.model.OneCompanySearchModel;
//import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.catalog.CatalogActivity;
import com.personnalize_design.meals.ui.search.SearchCompany;
import com.personnalize_design.meals.ui.search.interfaces.OnCompanyWorkinDay;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OneCompanySearchAdapter extends RecyclerView.Adapter<OneCompanySearchAdapter.ViewHolder> {

    private List<OneCompanySearchModel.CompanyData> list;
    private Context context;
    private DayWorkinAdapter dayWorkinAdapter;
    private OnOneCompanySelected mListener;
    private OnNoDataFound mListener2;
    private OnCompanyWorkinDay mListener3;
    private List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> mList;


    public OneCompanySearchAdapter(List<OneCompanySearchModel.CompanyData> list, Context context) {
        this.list = list;
        this.context = context;

        if(context instanceof SearchCompany){
            mListener = (SearchCompany) context;
            mListener2 = (SearchCompany) context;
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

        if(list.get(position).getCompanyName().equals("")){
            mListener2.onNoDataFound();
        }else{

            GlideApp.with(this.context)
                    .load(list.get(position).getCompanyCoverImage())
                    .placeholder(R.drawable.logo)
                    .centerCrop()
                    .into(holder.companyCoverImage);

            holder.companyName.setText(list.get(position).getCompanyName());
            holder.companyPhoneNumber.setText(list.get(position).getCompanyContact());
            holder.companyEmail.setText(list.get(position).getCompanyMail());
            dayWorkinAdapter = new DayWorkinAdapter(context, list.get(position).getCompanyWorkinDay());
            mListener3 = dayWorkinAdapter;
            holder.dayWorkin.setAdapter(dayWorkinAdapter);
            holder.dayWorkin.setHasFixedSize(true);
            holder.dayWorkin.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.dayWorkin.setItemAnimator(new DefaultItemAnimator());

        }
    }

    @Override
    public int getItemCount() {
        if(list.size() == 0 || list == null){
            return 0;
        }else{
            return list.size();
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
                    intent.putExtra("companyCatalog", list.get(getLayoutPosition()).getCompanyName());
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

                        mList = mListener3.onGetWorkinDay();
                        for(int i = 0; i < mList.size(); i++){
                            if(todayDay.equals(mList.get(i).getDayName())){
                                Log.d("TODAY DAY", "TODAY DAY: " + todayDay + "\nDay List: " + mList.get(i).getDayName());
                                mListener.onCompanySelected(list.get(getLayoutPosition()).getCompanyName());
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

//            companyDataBlock.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int layoutPosition = getLayoutPosition();
//
//                    if(mListener != null){
//                        mListener.onCompanySelected(list.get(layoutPosition).getCompanyName());
//                    }
//                }
//            });

        }
    }

    public interface OnOneCompanySelected{
        void onCompanySelected(String companyName);
        void onCanNotPassOrder();
    }

    public interface OnNoDataFound{
        void onNoDataFound();
    }
}
