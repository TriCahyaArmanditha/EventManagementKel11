package com.example.eventmanagementkel11;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditEventActivity extends AppCompatActivity {

    private EditText etEditEventName, etEditEventDate;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        etEditEventName = findViewById(R.id.etEventName);
        etEditEventDate = findViewById(R.id.etEventDate);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("events");

        String eventId = getIntent().getStringExtra("eventId");
        String eventName = getIntent().getStringExtra("eventName");
        String eventDate = getIntent().getStringExtra("eventDate");

        etEditEventName.setText(eventName);
        etEditEventDate.setText(eventDate);

        findViewById(R.id.btnSaveEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedEventName = etEditEventName.getText().toString();
                String updatedEventDate = etEditEventDate.getText().toString();

                if (!updatedEventName.isEmpty() && !updatedEventDate.isEmpty()) {
                    mDatabase.child(eventId).child("name").setValue(updatedEventName);
                    mDatabase.child(eventId).child("date").setValue(updatedEventDate);
                    Toast.makeText(EditEventActivity.this, "Event updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditEventActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
});
}
}
