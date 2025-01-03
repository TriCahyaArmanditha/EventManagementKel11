package com.example.eventmanagementkel11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class AdminSchedule extends AppCompatActivity {

    private EditText etEventName, etScheduleDay, etScheduleStartTime, etScheduleEndTime;
    private Button btnSaveSchedule;
    private DatabaseReference scheduleRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_admin);

        // Inisialisasi komponen
        etEventName = findViewById(R.id.etEventName);
        etScheduleDay = findViewById(R.id.etScheduleDay);
        etScheduleStartTime = findViewById(R.id.etScheduleStartTime);
        etScheduleEndTime = findViewById(R.id.etScheduleEndTime);
        btnSaveSchedule = findViewById(R.id.btnSaveSchedule);

        // Referensi ke node 'schedules' di Firebase
        scheduleRef = FirebaseDatabase.getInstance().getReference("schedules");

        // Menangani klik tombol Save
        btnSaveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSchedule();
            }
        });
    }

    private void saveSchedule() {
        String eventName = etEventName.getText().toString().trim();
        String day = etScheduleDay.getText().toString().trim();
        String startTime = etScheduleStartTime.getText().toString().trim();
        String endTime = etScheduleEndTime.getText().toString().trim();

        // Cek apakah semua field terisi
        if (eventName.isEmpty() || day.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            Toast.makeText(AdminSchedule.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mengecek apakah eventName sudah ada di Firebase
        scheduleRef.child(eventName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(AdminSchedule.this, "Event with this name already exists", Toast.LENGTH_SHORT).show();
                } else {
                    // Membuat objek Schedule dengan data dari input
                    // Menyimpan data ke Firebase dengan 'eventName' sebagai key
                    scheduleRef.child(eventName).child("day").setValue(day);
                    scheduleRef.child(eventName).child("startTime").setValue(startTime);
                    scheduleRef.child(eventName).child("endTime").setValue(endTime)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AdminSchedule.this, "Schedule saved successfully", Toast.LENGTH_SHORT).show();
                                    clearFields(); // Clear input fields
                                } else {
                                    Toast.makeText(AdminSchedule.this, "Failed to save schedule", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminSchedule.this, "Failed to check event existence", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearFields() {
        etEventName.setText("");
        etScheduleDay.setText("");
        etScheduleStartTime.setText("");
        etScheduleEndTime.setText("");
}
}
