package com.example.eventmanagementkel11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserEventListActivity extends AppCompatActivity {

    private LinearLayout linearLayoutEvents;
    private ArrayList<Event> eventList;
    private DatabaseReference databaseReference;  // Firebase reference untuk mengambil data event

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_event_user);

        // Menemukan LinearLayout untuk menampilkan event
        linearLayoutEvents = findViewById(R.id.linearLayoutEvents);

        // Inisialisasi daftar event
        eventList = new ArrayList<>();

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        // Mendapatkan data event dari Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear(); // Clear daftar event lama

                // Mengambil data event dari Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class); // Mengambil objek Event
                    if (event != null) {
                        eventList.add(event); // Menambahkan event ke list
                    }
                }

                // Menampilkan data event
                displayEvents();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserEventListActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayEvents() {
        linearLayoutEvents.removeAllViews(); // Menghapus tampilan event lama

        for (Event event : eventList) {
            // Meminflate layout item_event untuk setiap event
            View eventView = LayoutInflater.from(this).inflate(R.layout.item_event_user, linearLayoutEvents, false);

            // Menghubungkan elemen dari layout item_event.xml dengan ID yang sesuai
            TextView tvEventName = eventView.findViewById(R.id.tvUserEventTitle);
            TextView tvEventDate = eventView.findViewById(R.id.userEventDate);
            ImageView ivEventImage = eventView.findViewById(R.id.ivUserEventImage);
            Button btnRegister = eventView.findViewById(R.id.btnRegisterUserEvent);

            // Mengatur nilai untuk setiap event
            tvEventName.setText(event.getEventName());
            tvEventDate.setText(event.getEventDate());

            // Menampilkan gambar berdasarkan URI atau placeholder
            if (event.getImageName().startsWith("content://")) {
                ivEventImage.setImageURI(Uri.parse(event.getImageName())); // URI dari ManageEventActivity
            } else {
                int imageResource = getResources().getIdentifier(event.getImageName(), "drawable", getPackageName());
                if (imageResource != 0) {
                    ivEventImage.setImageResource(imageResource); // Gambar dari drawable
                } else {
                    ivEventImage.setImageResource(R.drawable.placeholder_image); // Placeholder jika tidak ada gambar
                }
            }

            // Menangani klik pada tombol Register untuk mengarah ke RegisterParticipantActivity
            btnRegister.setOnClickListener(v -> {
                Intent intent1 = new Intent(UserEventListActivity.this, RegisterParticipantActivity.class);
                intent1.putExtra("event_name", event.getEventName());
                startActivity(intent1);
            });

            // Menambahkan item event ke LinearLayout
            linearLayoutEvents.addView(eventView);
        }
    }
}
