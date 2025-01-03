package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterParticipantActivity extends AppCompatActivity {

    private EditText etName, etEmail;
    private Button btnRegister;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnRegister = findViewById(R.id.btnRegister);

        // Mendapatkan nama event dari intent
        String eventName = getIntent().getStringExtra("event_name");

        // Referensi ke Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("participants").child(eventName);

        // Menangani tombol Register
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();

            if (!name.isEmpty() && !email.isEmpty()) {
                // Menyimpan data peserta ke Firebase
                String participantId = databaseReference.push().getKey();
                Map<String, Object> participant = new HashMap<>();
                participant.put("id", participantId);
                participant.put("name", name);
                participant.put("email", email);

                if (participantId != null) {
                    databaseReference.child(participantId).setValue(participant)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterParticipantActivity.this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show();

                                    // Berpindah ke halaman RegistrationConfirmationActivity
                                    Intent intent = new Intent(RegisterParticipantActivity.this, RegistrationConfirmationActivity.class);
                                    intent.putExtra("participant_name", name);  // Mengirim nama peserta
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterParticipantActivity.this, "Pendaftaran gagal.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else {
                Toast.makeText(RegisterParticipantActivity.this, "Silakan isi semua field.", Toast.LENGTH_SHORT).show();
            }
});
}
}
