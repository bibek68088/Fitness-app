package com.fitnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {
    private List<DietItem> dietItems;

    public DietAdapter(List<DietItem> dietItems) {
        this.dietItems = dietItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DietItem item = dietItems.get(position);

        holder.foodNameTextView.setText(item.getFoodName());
        holder.categoryTextView.setText(item.getCategory());
        holder.caloriesTextView.setText(String.format("%d calories", item.getCalories()));
        holder.greenPercentageProgressBar.setProgress(item.getGreenPercentage());

        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .centerCrop()
                .into(holder.foodImageView);
    }

    @Override
    public int getItemCount() {
        return dietItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImageView;
        TextView foodNameTextView;
        TextView categoryTextView;
        TextView caloriesTextView;
        ProgressBar greenPercentageProgressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            caloriesTextView = itemView.findViewById(R.id.caloriesTextView);
            greenPercentageProgressBar = itemView.findViewById(R.id.greenPercentageProgressBar);
        }
    }
}