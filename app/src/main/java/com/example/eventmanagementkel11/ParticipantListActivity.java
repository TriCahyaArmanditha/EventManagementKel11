package com.example.eventmanagementkel11;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ParticipantListActivity extends AppCompatActivity {

    private ListView lvParticipants;
    private ArrayList<String> participantDisplayList;
    private HashMap<String, String> participantIdMap; // Map ID unik -> Data peserta
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private String eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_list);

        lvParticipants = findViewById(R.id.lvParticipants);
        participantDisplayList = new ArrayList<>();
        participantIdMap = new HashMap<>();

        // Adapter custom untuk menampilkan data dan tombol hapus
        adapter = new ArrayAdapter<String>(this, R.layout.participant_item, R.id.participantName, participantDisplayList) {
            @Override
            public android.view.View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
                android.view.View view = super.getView(position, convertView, parent);

                // Atur warna teks dan tambahkan tombol hapus
                TextView textView = view.findViewById(R.id.participantName);
                textView.setTextColor(Color.BLACK);

                ImageButton btnDelete = view.findViewById(R.id.btnDelete);
                btnDelete.setOnClickListener(v -> showDeleteConfirmationDialog(position));

                return view;
            }
        };

        lvParticipants.setAdapter(adapter);

        // Ambil nama event dari Intent
        eventName = getIntent().getStringExtra("event_name");
        if (eventName == null || eventName.isEmpty()) {
            Log.e("ParticipantListActivity", "Event name is missing");
            Toast.makeText(this, "Event name is missing. Using default event.", Toast.LENGTH_SHORT).show();
            eventName = "DefaultEvent"; // Nama event default jika event_name kosong
        }

        Log.d("ParticipantListActivity", "Received event_name: " + eventName);

        // Referensi ke database Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("participants").child(eventName);

        fetchParticipants();
    }

    private void fetchParticipants() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                participantDisplayList.clear();
                participantIdMap.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot participantSnapshot : snapshot.getChildren()) {
                        String id = participantSnapshot.getKey();
                        String name = participantSnapshot.child("name").getValue(String.class);
                        String email = participantSnapshot.child("email").getValue(String.class);

                        if (id != null && name != null && email != null) {
                            String displayData = name + " (" + email + ")";
                            participantDisplayList.add(displayData);
                            participantIdMap.put(id, displayData); // Simpan ID dan data peserta
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ParticipantListActivity.this, "Tidak ada peserta untuk event ini.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ParticipantListActivity.this, "Gagal mengambil data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDeleteConfirmationDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Peserta")
                .setMessage("Apakah Anda yakin ingin menghapus peserta ini?")
                .setPositiveButton("Ya", (dialog, which) -> deleteParticipant(position))
                .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void deleteParticipant(int position) {
        // Cari ID berdasarkan posisi
        String displayData = participantDisplayList.get(position);
        String idToDelete = null;

        for (String id : participantIdMap.keySet()) {
            if (participantIdMap.get(id).equals(displayData)) {
                idToDelete = id;
                break;
            }
        }

        if (idToDelete != null) {
            String finalIdToDelete = idToDelete;
            databaseReference.child(idToDelete).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    participantDisplayList.remove(position);
                    participantIdMap.remove(finalIdToDelete);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ParticipantListActivity.this, "Peserta dihapus.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ParticipantListActivity.this, "Gagal menghapus peserta.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Peserta tidak ditemukan.", Toast.LENGTH_SHORT).show();
}
}
}
