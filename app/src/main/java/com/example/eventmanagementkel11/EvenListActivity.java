package com.example.eventmanagementkel11;

import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso; // Pastikan untuk menambahkan Picasso di build.gradle

import java.util.ArrayList;

public class EvenListActivity extends AppCompatActivity {

    private LinearLayout linearLayoutEvents;
    private ArrayList<Event> eventList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_list);

        linearLayoutEvents = findViewById(R.id.linearLayoutEvents);
        eventList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        // Mengambil data event dari Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    if (event != null) {
                        eventList.add(event);
                    }
                }
                displayEvents();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EvenListActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayEvents() {
        linearLayoutEvents.removeAllViews();

        for (Event event : eventList) {
            View eventView = LayoutInflater.from(this).inflate(R.layout.item_event, linearLayoutEvents, false);

            TextView tvEventName = eventView.findViewById(R.id.tvEventTitle);
            TextView tvEventDate = eventView.findViewById(R.id.eventDate);
            ImageView ivEventImage = eventView.findViewById(R.id.ivEventImage);
            Button btnRegister = eventView.findViewById(R.id.btnRegisterEvent);
            Button btnViewParticipants = eventView.findViewById(R.id.btnViewParticipants);
            ImageView btnDeleteEvent = eventView.findViewById(R.id.btnDeleteEvent);

            tvEventName.setText(event.getEventName());
            tvEventDate.setText(event.getEventDate());

            String imageName = event.getImageName();

            // Jika imageName null atau kosong, tampilkan gambar placeholder
            if (imageName == null || imageName.isEmpty()) {
                ivEventImage.setImageResource(R.drawable.placeholder_image);
            } else {
                // Cek apakah imageName adalah URL
                if (imageName.startsWith("http://") || imageName.startsWith("https://")) {
                    // Jika imageName adalah URL, gunakan Picasso untuk mengambil gambar
                    Picasso.get().load(imageName).into(ivEventImage);
                } else if (imageName.startsWith("content://")) {
                    // Jika imageName adalah URI
                    ivEventImage.setImageURI(Uri.parse(imageName));
                } else {
                    // Jika imageName adalah nama gambar di drawable
                    int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    if (imageResource != 0) {
                        ivEventImage.setImageResource(imageResource);  // Menampilkan gambar dari drawable
                    } else {
                        ivEventImage.setImageResource(R.drawable.placeholder_image);  // Menampilkan gambar placeholder jika tidak ada gambar
                    }
                }
            }

            btnRegister.setOnClickListener(v -> {
                Intent intent1 = new Intent(EvenListActivity.this, AdminRegistrationParticipant.class);
                intent1.putExtra("event_name", event.getEventName());
                startActivity(intent1);
            });

            btnViewParticipants.setOnClickListener(v -> {
                Intent intent = new Intent(EvenListActivity.this, ParticipantListActivity.class);
                intent.putExtra("event_name", event.getEventName());
                startActivity(intent);
            });

            // Menghapus event ketika tombol hapus ditekan
            btnDeleteEvent.setOnClickListener(v -> {
                new AlertDialog.Builder(EvenListActivity.this)
                        .setMessage("Are you sure you want to delete this event?")
                        .setPositiveButton("Yes", (dialog, which) -> deleteEvent(event.getEventId()))
                        .setNegativeButton("No", null)
                        .show();
            });

            linearLayoutEvents.addView(eventView);
        }
    }

    private void deleteEvent(String eventId) {
        // Menghapus event dari Firebase berdasarkan eventId
        databaseReference.child(eventId).removeValue()
                .addOnSuccessListener(aVoid -> Toast.makeText(EvenListActivity.this, "Event deleted successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(EvenListActivity.this, "Failed to delete event", Toast.LENGTH_SHORT).show());
}
}
