package com.alyssajinnellibed.spinner.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alyssajinnellibed.spinner.R;
import com.alyssajinnellibed.spinner.model.Diners;

import java.util.ArrayList;

public class DinerRecyclerAdapter extends RecyclerView.Adapter<DinerRecyclerAdapter.BeneficiaryViewHolder>  {

    private ArrayList<Diners> listDiners;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Diners> mFilteredList;


    public DinerRecyclerAdapter(ArrayList<Diners> listDiners, Context mContext) {
        this.listDiners = listDiners;
        this.mContext = mContext;
        this.mFilteredList = listDiners;


    }

    public class BeneficiaryViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewLocation;
        public AppCompatTextView textViewType;
        public AppCompatTextView textViewDinerName;
        public AppCompatTextView textViewPrice;
        //public  ImageView overflow;

        public BeneficiaryViewHolder(View view) {
            super(view);
            textViewLocation = (AppCompatTextView) view.findViewById(R.id.textViewLocation);
            textViewType = (AppCompatTextView) view.findViewById(R.id.textViewType);
            textViewDinerName = (AppCompatTextView) view.findViewById(R.id.textViewDinerName);
            textViewPrice = (AppCompatTextView) view.findViewById(R.id.textViewPrice);

        }


    }




    @Override
    public BeneficiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_beneficiary_recycler, parent, false);

        return new BeneficiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BeneficiaryViewHolder holder, int position) {
        holder.textViewLocation.setText(listDiners.get(position).getLocation());
        holder.textViewType.setText(listDiners.get(position).getCategory());
        holder.textViewDinerName.setText(listDiners.get(position).getDinerName());
        holder.textViewPrice.setText(listDiners.get(position).getPriceRange());


    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }



}
