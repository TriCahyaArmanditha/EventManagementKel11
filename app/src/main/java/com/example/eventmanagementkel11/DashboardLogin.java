package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_login2);

        ImageButton btnLoginUser = findViewById(R.id.btnLoginUser);
        TextView loginUserText = findViewById(R.id.loginUserText);

        ImageButton btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        TextView loginAdminText = findViewById(R.id.loginAdminText);

        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardLogin.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardLogin.this, AdminLoginActivity.class);
                startActivity(intent);
            }
});
}
}
