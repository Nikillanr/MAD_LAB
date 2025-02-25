package com.example.sakshibday;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding for the main view based on system bars (already in your code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the Button and ImageViews by their IDs
        Button buttonShowImages = findViewById(R.id.button3);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        // Set initial visibility of the images to GONE (they won't be visible initially)
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);

        // Set the button click listener
        buttonShowImages.setOnClickListener(v -> {
            // Toggle visibility of the images
            if (imageView2.getVisibility() == View.GONE && imageView3.getVisibility() == View.GONE) {
                // If images are gone, show them
                imageView2.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
            } else {
                // If images are visible, hide them
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
            }
        });
    }


}