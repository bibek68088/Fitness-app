package com.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page_layout);

        Button getStartedButton = findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
        ImageView showimage = findViewById(R.id.showImage);
//        Picasso.get()
//                .load("https://www.onlinekhabar.com/wp-content/uploads/2020/12/Mount-Everest-Sagarmatha.jpg")
//                .into(showimage);
        Glide.with(this).load("https://www.onlinekhabar.com/wp-content/uploads/2020/12/Mount-Everest-Sagarmatha.jpg")
                .into(showimage);


    }
}
