package com.example.eventmanagementkel11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManageEvents extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView ivEventImage;
    private Uri imageUri;
    private EditText etEventName, etEventDate;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ivEventImage = findViewById(R.id.ivEventImage);
        etEventName = findViewById(R.id.etEventName);
        etEventDate = findViewById(R.id.etEventDate);
        Button btnChooseImage = findViewById(R.id.btnChooseImage);
        Button btnSaveEvent = findViewById(R.id.btnSaveEvent);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        // Tombol untuk memilih gambar dari galeri
        btnChooseImage.setOnClickListener(v -> openGallery());

        // Tombol untuk menyimpan event yang telah ditambahkan
        btnSaveEvent.setOnClickListener(v -> saveEvent());
    }

    // Membuka galeri untuk memilih gambar
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Menangani hasil setelah memilih gambar dari galeri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ivEventImage.setImageURI(imageUri);  // Menampilkan gambar yang dipilih di ImageView
        } else {
            // Jika tidak ada gambar yang dipilih, gunakan gambar placeholder
            ivEventImage.setImageResource(R.drawable.placeholder_image);  // Ganti dengan gambar placeholder yang sesuai
        }
    }

    // Menyimpan data event ke Firebase
    private void saveEvent() {
        String eventName = etEventName.getText().toString();
        String eventDate = etEventDate.getText().toString();

        // Jika tidak ada gambar, gunakan gambar placeholder
        if (eventName.isEmpty() || eventDate.isEmpty()) {
            Toast.makeText(this, "Harap lengkapi semua field!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Jika gambar tidak dipilih, set imageUri ke placeholder
        String imageUriString = (imageUri != null) ? imageUri.toString() : "android.resource://com.example.eventmanagementkel11/drawable/ic_placeholder";  // Ganti dengan URL placeholder

        // Menyimpan data event ke Firebase
        String eventId = databaseReference.push().getKey();  // Mendapatkan ID unik untuk event
        Event event = new Event(eventId, eventName, eventDate, imageUriString);  // Membuat objek event

        if (eventId != null) {
            databaseReference.child(eventId).setValue(event)  // Menyimpan data event di Firebase
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Event berhasil disimpan!", Toast.LENGTH_SHORT).show();
                            // Kembali ke EvenListActivity setelah berhasil menyimpan event
                            Intent intent = new Intent(ManageEvents.this, EvenListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Gagal menyimpan event", Toast.LENGTH_SHORT).show();
                        }
                    });
}
}
}
