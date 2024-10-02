package com.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ExercisesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private int workoutId;

    public static ExercisesFragment newInstance(int workoutId) {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        args.putInt("workout_id", workoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workoutId = getArguments().getInt("workout_id");
        }
    }
    private TextView textTotalCalories;
    private int totalCalories = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        recyclerView = view.findViewById(R.id.exercisesRecyclerView);
        textTotalCalories = view.findViewById(R.id.textTotalCalories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchExercises();
        return view;
    }

    private void fetchExercises() {
        String url = "http://10.0.2.2/fitness/fitness_app_api/api.php?action=get_exercises_by_workout&workout_id=" + workoutId;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Gson gson = new Gson();
                    Type exerciseListType = new TypeToken<List<Exercise>>(){}.getType();
                    List<Exercise> exercises = gson.fromJson(response.toString(), exerciseListType);

                    totalCalories = exercises.stream().mapToInt(Exercise::getCalories).sum();
                    textTotalCalories.setText("Total Calories: " + totalCalories);

                    exerciseAdapter = new ExerciseAdapter(exercises);
                    recyclerView.setAdapter(exerciseAdapter);
                },
                error -> Toast.makeText(getContext(), "Error fetching exercises", Toast.LENGTH_SHORT).show());

        Volley.newRequestQueue(requireContext()).add(request);;
    }
}