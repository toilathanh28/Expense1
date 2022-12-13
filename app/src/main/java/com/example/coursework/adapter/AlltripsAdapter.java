package com.example.coursework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.R;
import com.example.coursework.model.AlltripsData;
import com.example.coursework.model.RecentData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlltripsAdapter extends RecyclerView.Adapter<AlltripsAdapter.AlltripsViewHolder>{

    Context context;
    List<AlltripsData> alltripsDataList;
    List<AlltripsData> filteredTripDataList;

    public AlltripsAdapter(Context context, List<AlltripsData> alltripsDataList) {
        this.context = context;
        this.alltripsDataList = alltripsDataList;
        this.filteredTripDataList = alltripsDataList ;
    }

    @NonNull
    @Override
    public AlltripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.alltrips_row_item, parent, false);
        return new AlltripsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlltripsViewHolder holder, int position) {

        holder.placeName.setText(alltripsDataList.get(position).getPlaceName());
        holder.tripName.setText(alltripsDataList.get(position).getTripName());
        holder.riskAssessment.setText(alltripsDataList.get(position).getRiskAssessment());
        holder.price.setText(alltripsDataList.get(position).getPrice());
        holder.placeImage.setImageResource(alltripsDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return filteredTripDataList.size();
    }

    public static final class AlltripsViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, tripName, price, riskAssessment;


        public AlltripsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            tripName = itemView.findViewById(R.id.trip_name);
            price = itemView.findViewById(R.id.price);
            riskAssessment = itemView.findViewById(R.id.risk_assessment);
        }
    }
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredTripDataList = alltripsDataList;
                }
                else{
                    List<AlltripsData> lstfiltered = new ArrayList<>();
                    for(AlltripsData row: alltripsDataList){
                        if(row.getPlaceName().toLowerCase( ).contains(Key.toLowerCase()) ){
                            lstfiltered.add(row);
                        }
                    }
                    filteredTripDataList = lstfiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredTripDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredTripDataList = (List<AlltripsData>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
