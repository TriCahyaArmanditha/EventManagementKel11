package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SettingSchedule extends AppCompatActivity {

    private ImageButton btnAddSchedule, btnDeleteSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_schedule);

        // Inisialisasi ImageButton
        btnAddSchedule = findViewById(R.id.btnAddSchedule);
        btnDeleteSchedule = findViewById(R.id.btnDeleteSchedule);

        // OnClickListener untuk tombol "Tambah Jadwal"
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke halaman AdminSchedule
                Intent intent = new Intent(SettingSchedule.this, AdminSchedule.class);
                startActivity(intent);
            }
        });

        // OnClickListener untuk tombol "Hapus Jadwal"
        btnDeleteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke halaman ScheduleListSetting
                Intent intent = new Intent(SettingSchedule.this, ListScheduleSetting.class);
                startActivity(intent);
            }
});
}
}
