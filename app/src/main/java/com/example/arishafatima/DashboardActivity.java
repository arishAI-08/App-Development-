package com.example.arishafatima;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        UserStore store = new UserStore(this);
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(getString(R.string.welcome, store.getSessionName()));

        ImageButton btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        int[] cardIds = {R.id.cardCourses, R.id.cardSchedule, R.id.cardGallery,
                R.id.cardMessages, R.id.cardSettings, R.id.cardAbout};
        int[] labels = {R.string.module_courses, R.string.module_schedule,
                R.string.module_gallery, R.string.module_messages,
                R.string.module_settings, R.string.module_about};

        for (int i = 0; i < cardIds.length; i++) {
            View card = findViewById(cardIds[i]);
            final int label = labels[i];
            card.setOnClickListener(v ->
                    Toast.makeText(this, getString(label) + " opened",
                            Toast.LENGTH_SHORT).show());
        }
    }
}