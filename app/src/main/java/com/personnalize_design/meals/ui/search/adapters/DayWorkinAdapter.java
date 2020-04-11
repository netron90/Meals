package com.personnalize_design.meals.ui.search.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.ui.search.interfaces.OnCompanyWorkinDay;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DayWorkinAdapter extends RecyclerView.Adapter<DayWorkinAdapter.ViewHolder> implements OnCompanyWorkinDay {

    private Context context;
    private List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> dayBeanList;

    public DayWorkinAdapter(Context context, List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> dayBeanList) {
        this.context = context;
        this.dayBeanList = dayBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workin_day_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dayName.setText(dayBeanList.get(position).getDayName() + ", ");
    }

    @Override
    public int getItemCount() {
        if(dayBeanList.size() == 0 || dayBeanList == null)
        {
            return 0;
        }else{
            return dayBeanList.size();
        }
    }

    @Override
    public List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> onGetWorkinDay() {
        return dayBeanList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = (TextView) itemView.findViewById(R.id.dayName);
        }
    }
}
