package com.personnalize_design.meals.ui.search.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.model.OneCompanySearchModel;
import com.personnalize_design.meals.di.GlideApp;
import com.personnalize_design.meals.ui.search.SearchCompany;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.personnalize_design.meals.constants.Mutils.BASE_URL;

public class OneCompanySearchAdapter extends RecyclerView.Adapter<OneCompanySearchAdapter.ViewHolder> {

    private List<OneCompanySearchModel> list;
    private Context context;
    private OnOneCompanySelected mListener;
    private OnNoDataFound mListener2;


    public OneCompanySearchAdapter(List<OneCompanySearchModel> list, Context context) {
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
        GlideApp.with(this.context)
                .load(BASE_URL+list.get(position).getData().getCompanyCoverImage())
                .placeholder(R.drawable.logo)
                .centerCrop()
                .into(holder.companyCoverImage);
        if(list.get(position).getData().getCompanyName().equals("")){
            mListener2.onNoDataFound();
        }else{
            holder.companyName.setText(list.get(position).getData().getCompanyName());
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
                        mListener.onCompanySelected(list.get(layoutPosition).getData().getCompanyName());
                    }
                }
            });

        }
    }

    public interface OnOneCompanySelected{
        void onCompanySelected(String companyName);
    }

    public interface OnNoDataFound{
        void onNoDataFound();
    }
}
