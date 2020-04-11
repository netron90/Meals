package com.personnalize_design.meals.ui.promotion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.CompanyPromotion;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {

    private List<CompanyPromotion.CompanyEventBean> companyPromotionList;
    private Context context;
    private String companyPhoneNumber;

    public PromotionAdapter(List<CompanyPromotion.CompanyEventBean> companyPromotionList, Context context,
                            String companyPhoneNumber) {
        this.companyPromotionList = companyPromotionList;
        this.context = context;
        this.companyPhoneNumber = companyPhoneNumber;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company_promotion, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.companyNamePromotion.setText(companyPromotionList.get(position).getCompanyName() + " - Promotion");
        holder.eventTitle.setText(companyPromotionList.get(position).getEventTitle());
        holder.eventDescription.setText(companyPromotionList.get(position).getEventDescription());
        holder.eventDate.setText(context.getString(R.string.event_date_textstart) + " " +
                companyPromotionList.get(position).getDateEventStart() + context.getString(R.string.event_date_textend) + " " +
                companyPromotionList.get(position).getDateEventEnd());
        holder.eventDetails.setText(context.getResources().getString(R.string.event_detail) + ": " + companyPhoneNumber);
    }

    @Override
    public int getItemCount() {
        if(companyPromotionList.size() == 0){
            return 0;
        }else{
            return companyPromotionList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView companyNamePromotion;
        private TextView eventDate;
        private TextView eventTitle;
        private TextView eventDescription;
        private TextView eventDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyNamePromotion = (TextView) itemView.findViewById(R.id.companyNamePromotion);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            eventTitle = (TextView) itemView.findViewById(R.id.eventTitle);
            eventDescription = (TextView) itemView.findViewById(R.id.eventDescription);
            eventDetails = (TextView) itemView.findViewById(R.id.eventDetail);
        }
    }
}
