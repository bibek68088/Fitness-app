package com.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class WorkoutsFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkoutAdapter workoutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workout_layout, container, false);

        recyclerView = view.findViewById(R.id.workoutRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchWorkouts();

        return view;
    }

    private void fetchWorkouts() {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        String url = "http://10.0.2.2/fitness/fitness_app_api/api.php?action=get_workouts";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Gson gson = new Gson();
                    Type workoutListType = new TypeToken<List<Workout>>(){}.getType();
                    List<Workout> workouts = gson.fromJson(response.toString(), workoutListType);

                    workoutAdapter = new WorkoutAdapter(requireContext(), workouts, workout -> {
                        // Navigate to ExercisesFragment
                        ExercisesFragment exercisesFragment = ExercisesFragment.newInstance(workout.getId());
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, exercisesFragment) // Replace with your fragment container ID
                                .addToBackStack(null)
                                .commit();
                    });
                    recyclerView.setAdapter(workoutAdapter);
                },
                error -> {
                    Toast.makeText(getContext(), "Error fetching workouts", Toast.LENGTH_SHORT).show();
                });

        requestQueue.add(request);
    }
}