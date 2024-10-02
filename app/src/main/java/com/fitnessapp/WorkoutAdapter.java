package com.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<Workout> workouts;
    private OnWorkoutClickListener listener;
    private ProgressTracker progressTracker;

    public interface OnWorkoutClickListener {
        void onWorkoutClick(Workout workout);
    }

    public WorkoutAdapter(Context context, List<Workout> workouts, OnWorkoutClickListener listener) {
        this.workouts = workouts;
        this.listener = listener;
        this.progressTracker = new ProgressTracker(context);
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.bind(workout, listener, progressTracker);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageWorkout;
        TextView textWorkoutName, textExerciseCount, textDuration, textDifficulty;
        ProgressBar progressBar;
        TextView textProgress;

        WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            imageWorkout = itemView.findViewById(R.id.imageWorkout);
            textWorkoutName = itemView.findViewById(R.id.textWorkoutName);
            textExerciseCount = itemView.findViewById(R.id.textExerciseCount);
            textDuration = itemView.findViewById(R.id.textDuration);
            textDifficulty = itemView.findViewById(R.id.textDifficulty);
            progressBar = itemView.findViewById(R.id.progressBar);
            textProgress = itemView.findViewById(R.id.textProgress);
        }

        void bind(Workout workout, OnWorkoutClickListener listener, ProgressTracker progressTracker) {
            textWorkoutName.setText(workout.getCategory());
            textExerciseCount.setText(workout.getExercisesCount() + " Exercises");
            textDuration.setText(workout.getDuration() + " Minutes");
            textDifficulty.setText(workout.getDifficulty());

            int completedExercises = progressTracker.getCompletedExercises(workout.getId());
            progressBar.setMax(workout.getExercisesCount());
            progressBar.setProgress(completedExercises);
            textProgress.setText(completedExercises + "/" + workout.getExercisesCount());

            if (workout.getImageUrl() != null && !workout.getImageUrl().isEmpty()) {
                Picasso.get()
                        .load(workout.getImageUrl())
                        .into(imageWorkout);
            } else {
                // Set a default image or skip loading
                imageWorkout.setImageResource(R.mipmap.panda1); // Example with a default image
            }

            itemView.setOnClickListener(v -> listener.onWorkoutClick(workout));
        }
    }

    // Method to update progress
    public void updateProgress(int workoutId, int completedExercises, int totalExercises) {
        progressTracker.updateProgress(workoutId, completedExercises, totalExercises);
        notifyDataSetChanged(); // Or use notifyItemChanged(position) for better performance
    }
}