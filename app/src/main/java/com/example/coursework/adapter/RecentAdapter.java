package com.example.coursework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.DetailofTrip;
import com.example.coursework.R;
import com.example.coursework.model.RecentData;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecycleViewHolder>{

    Context context;
    List<RecentData> recentDataList;

    public RecentAdapter(Context context, List<RecentData> recentDataList) {
        this.context = context;
        this.recentDataList = recentDataList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recent_row_item, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        holder.placeName.setText(recentDataList.get(position).getPlaceName());
        holder.tripName.setText(recentDataList.get(position).getTripName());
        holder.riskAssessment.setText(recentDataList.get(position).getRiskAssessment());
        holder.price.setText(recentDataList.get(position).getPrice());
        holder.placeImage.setImageResource(recentDataList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, DetailofTrip.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentDataList.size();
    }

    public static final class RecycleViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, tripName, price, riskAssessment;


        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            tripName = itemView.findViewById(R.id.trip_name);
            price = itemView.findViewById(R.id.price);
            riskAssessment = itemView.findViewById(R.id.risk_assessment);
        }
    }
}
