package com.fitnessapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exercises;

    public ExerciseAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView textExerciseDescription, textCalories;
        VideoView videoExercise;

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            textExerciseDescription = itemView.findViewById(R.id.textExerciseDescription);
            textCalories = itemView.findViewById(R.id.textCalories);
            videoExercise = itemView.findViewById(R.id.videoExercise);
        }

        void bind(Exercise exercise) {
            textExerciseDescription.setText(exercise.getDescription());
            textCalories.setText("Calories: " + exercise.getCalories());

            if (!exercise.getVideoUrl().isEmpty()) {
                Uri videoUri = Uri.parse(exercise.getVideoUrl());
                videoExercise.setVideoURI(videoUri);
                videoExercise.setOnPreparedListener(mp -> {
                    mp.setLooping(true);
                    videoExercise.start();
                });
            }
        }
    }
}