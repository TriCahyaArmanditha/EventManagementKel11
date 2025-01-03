package com.example.eventmanagementkel11;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EventRepository {

    private DatabaseReference databaseReference;
    private Context context; // Menambahkan konteks untuk Toast

    // Menambahkan konstruktor untuk menerima context
    public EventRepository(Context context) {
        // Inisialisasi Firebase Database
        this.context = context;  // Menyimpan context
        databaseReference = FirebaseDatabase.getInstance().getReference("events");
    }

    private void onSuccess(Void aVoid) {
        // Berikan feedback jika berhasil
        Toast.makeText(context, "Event added successfully", Toast.LENGTH_SHORT).show();
    }

    // Menambahkan event baru ke Firebase
    public void addEvent(Event event) {
        // Buat ID event unik untuk setiap event
        String eventId = databaseReference.push().getKey();
        if (eventId != null) {
            // Simpan event baru ke Firebase
            databaseReference.child(eventId).setValue(event)
                    .addOnSuccessListener(this::onSuccess)
                    .addOnFailureListener(e -> {
                        // Feedback jika gagal
                        Toast.makeText(context, "Failed to add event", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    // Mendapatkan semua event dari Firebase
    public void getAllEvents(final EventListener eventListener) {
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Event> events = task.getResult().getValue(List.class); // Ambil data events
                eventListener.onEventsRetrieved(events);
            } else {
                eventListener.onError(task.getException());
            }
        });
    }

    public interface EventListener {
        void onEventsRetrieved(List<Event> events);
        void onError(Exception exception);
}
}
