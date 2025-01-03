package com.example.eventmanagementkel11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    private LinearLayout linearLayoutSchedules;
    private DatabaseReference scheduleRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        linearLayoutSchedules = findViewById(R.id.linearLayoutSchedules);
        scheduleRef = FirebaseDatabase.getInstance().getReference("schedules");

        // Menggunakan ValueEventListener untuk mengambil data dari Firebase
        scheduleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    linearLayoutSchedules.removeAllViews(); // Menghapus tampilan sebelumnya

                    // Proses setiap event di dalam dataSnapshot
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                        try {
                            // Debug log untuk melihat nama setiap event
                            System.out.println("Processing event: " + eventSnapshot.getKey());

                            // Mengambil data sebagai Map
                            Map<String, Object> eventDetails = (Map<String, Object>) eventSnapshot.getValue();

                            // Pastikan data memiliki key yang diperlukan
                            if (eventDetails != null && eventDetails.containsKey("day") && eventDetails.containsKey("startTime") && eventDetails.containsKey("endTime")) {
                                // Membuat objek Schedule dengan data yang ada
                                String eventName = eventSnapshot.getKey(); // Event name adalah key dari node
                                String day = eventDetails.get("day").toString();
                                String startTime = eventDetails.get("startTime").toString();
                                String endTime = eventDetails.get("endTime").toString();

                                Schedule schedule = new Schedule(eventName, day, startTime, endTime);
                                displaySchedule(schedule);
                            } else {
                                // Memberi tahu jika ada data yang kurang
                                System.out.println("Missing data for event: " + eventSnapshot.getKey());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ScheduleActivity.this, "Failed to parse event data", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(ScheduleActivity.this, "No schedules found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ScheduleActivity.this, "Failed to load schedules: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displaySchedule(Schedule schedule) {
        // Mengatur tampilan untuk setiap jadwal
        View scheduleView = LayoutInflater.from(this).inflate(R.layout.item_schedule, linearLayoutSchedules, false);

        TextView tvEvent = scheduleView.findViewById(R.id.tvScheduleEvent);
        TextView tvDay = scheduleView.findViewById(R.id.tvScheduleDay);
        TextView tvTime = scheduleView.findViewById(R.id.tvScheduleTime);

        // Menampilkan informasi event
        tvEvent.setText(schedule.getEventName());
        tvDay.setText(schedule.getDay());
        tvTime.setText(schedule.getStartTime() + " - " + schedule.getEndTime());

        // Menambahkan tampilan jadwal ke layout
        linearLayoutSchedules.addView(scheduleView);
}
}
