package com.fitnessapp;

import android.os.Bundle;
import android.view.View; // Import View class

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fitnessapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set BottomNavigationView visibility
        binding.bottomNavigationView.setVisibility(View.VISIBLE);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.menu_workouts) {
                replaceFragment(new WorkoutsFragment());
            } else if (item.getItemId() == R.id.menu_diet) {
                replaceFragment(new DietFragment());
            } else if (item.getItemId() == R.id.menu_profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });

        // Load default fragment
        replaceFragment(new HomeFragment());
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}