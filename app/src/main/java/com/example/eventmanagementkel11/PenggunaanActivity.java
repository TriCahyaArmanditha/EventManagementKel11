package com.example.eventmanagementkel11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PenggunaanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penggunaan);

        // Set up Toolbar for navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back button

        // Find the button and set an OnClickListener
        Button openYouTubeButton = findViewById(R.id.open_youtube_button);
        openYouTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open a YouTube link
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/@initricahya?si=b9Gmx5k24FL1Tzzn"));
                startActivity(intent);
            }
        });
    }

    // Handle toolbar item clicks (navigation)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle back navigation
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
}
}
}
