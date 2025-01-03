package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        tvWelcome = findViewById(R.id.tvWelcome);
        progressBar = findViewById(R.id.progressBar);


        // Tampilkan progress bar setelah 2 detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWelcome.setVisibility(View.GONE); // Sembunyikan teks "Welcome"
                progressBar.setVisibility(View.VISIBLE); // Tampilkan progress bar

                // Setelah beberapa detik lanjut ke screen berikutnya
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Beralih ke Introduction Screen
                        setContentView(R.layout.introduction_screen);

                        // Menunggu lagi sebelum beralih ke login screen
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Beralih ke Login Screen
                                Intent intent = new Intent(SplashScreenActivity.this, DashboardLogin.class);
                                startActivity(intent);
                                finish(); // Hapus SplashScreenActivity dari stack
                            }
                        }, 2000); // Waktu tampilkan Introduction Screen (2 detik)
                    }
                }, 2000); // Waktu tampilkan progress bar setelah "Welcome" (2 detik)
            }
        }, 2000); // Waktu tampilkan layar Welcome (2 detik)
}
}
