package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_confirmation);

        // Mendapatkan nama peserta dari intent
        String participantName = getIntent().getStringExtra("participant_name");

        // Menampilkan nama peserta di TextView
        TextView tvConfirmationMessage = findViewById(R.id.tvConfirmationMessage);
        if (participantName != null) {
            tvConfirmationMessage.setText("Thank you, Admin For Add " + participantName + ", Continue Your Work!");
        }

        // Tombol kembali ke halaman utama
        Button btnGoToMain = findViewById(R.id.btnGoToMain);
        btnGoToMain.setOnClickListener(v -> {
            Intent intent = new Intent(AdminConfirmation.this,EvenListActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
