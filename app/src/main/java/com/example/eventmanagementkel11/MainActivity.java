package com.example.eventmanagementkel11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnAddEvent, btnViewEvents, btnEditSchedule;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pastikan ID yang digunakan sesuai dengan yang ada di XML
        btnAddEvent = findViewById(R.id.btnAddEvents);
        btnViewEvents = findViewById(R.id.btnViewEvent);
        btnEditSchedule = findViewById(R.id.btnEditSchedule);

        // Menambahkan OnClickListener untuk tombol Add Event
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ManageEvents.class));
            }
        });

        // Menambahkan OnClickListener untuk tombol View Events
        btnViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EvenListActivity.class));
            }
        });

        // Menambahkan OnClickListener untuk tombol Edit Schedule
        btnEditSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingSchedule.class));
            }
});
}
}
