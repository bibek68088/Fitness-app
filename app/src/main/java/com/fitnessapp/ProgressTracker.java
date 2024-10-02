package com.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;

public class ProgressTracker {
    private static final String PREFS_NAME = "WorkoutProgress";
    private static final String PROGRESS_PREFIX = "workout_";
    private static final String COMPLETED_EXERCISES_PREFIX = "completed_exercises_";

    private SharedPreferences sharedPreferences;

    public ProgressTracker(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void updateProgress(int workoutId, int completedExercises, int totalExercises) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        float progress = (float) completedExercises / totalExercises;
        editor.putFloat(PROGRESS_PREFIX + workoutId, progress);
        editor.putInt(COMPLETED_EXERCISES_PREFIX + workoutId, completedExercises);
        editor.apply();
    }

    public float getProgress(int workoutId) {
        return sharedPreferences.getFloat(PROGRESS_PREFIX + workoutId, 0f);
    }

    public int getCompletedExercises(int workoutId) {
        return sharedPreferences.getInt(COMPLETED_EXERCISES_PREFIX + workoutId, 0);
    }

    public void resetProgress(int workoutId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PROGRESS_PREFIX + workoutId);
        editor.remove(COMPLETED_EXERCISES_PREFIX + workoutId);
        editor.apply();
    }

    public void resetAllProgress() {
        sharedPreferences.edit().clear().apply();
    }
}