package com.personnalize_design.meals.ui.search.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.AllCompanySearch;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<AllCompanySearch.CompanyData> companyDataList;

    private OnCompanySelected mListener;

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
                .load(BASE_URL+companyDataList.get(position).getCompanyCoverImage())
                .placeholder(R.drawable.logo)
                .centerCrop()
                .into(holder.companyCoverImage);
        holder.companyName.setText(companyDataList.get(position).getCompanyName());

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
        private ImageView companyCoverImage;
        private RelativeLayout companyDataBlock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = (TextView) itemView.findViewById(R.id.companyName);
            companyCoverImage = (ImageView) itemView.findViewById(R.id.companyCoverImage);
            companyDataBlock = (RelativeLayout) itemView.findViewById(R.id.companyDataBlock);

            companyDataBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = getLayoutPosition();

                    if(mListener != null){
                        mListener.onCompanySelected(companyDataList.get(layoutPosition).getCompanyName());
                    }
                }
            });

         }
    }

    public interface OnCompanySelected{
        void onCompanySelected(String companyName);
    }
}
