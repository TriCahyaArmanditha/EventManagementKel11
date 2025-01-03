package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etAdminUsername, etAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etAdminUsername = findViewById(R.id.etAdminUsername);
        etAdminPassword = findViewById(R.id.etAdminPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etAdminUsername.getText().toString().trim();
                String password = etAdminPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    // Validate if fields are empty
                    Toast.makeText(AdminLoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (username.equals("admin") && password.equals("admin123")) {
                    // Admin login successful
                    Toast.makeText(AdminLoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // Navigate to MainActivity
                    Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close current activity
                } else {
                    // Invalid login credentials
                    Toast.makeText(AdminLoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
});
}
}
