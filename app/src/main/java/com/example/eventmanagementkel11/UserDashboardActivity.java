package com.example.eventmanagementkel11;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;

public class UserDashboardActivity extends AppCompatActivity {

    private static final String TAG = "UserDashboardActivity";  // Log tag untuk debugging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        try {
            // Set up the toolbar as ActionBar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Mengubah warna teks toolbar menjadi putih
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("User Dashboard"); // Mengubah judul toolbar
                getSupportActionBar().setDisplayShowTitleEnabled(true);
            }
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

            // Handle button click for viewing events
            findViewById(R.id.btnViewUserEvent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent to navigate to UserListEvent Activity
                    Log.d(TAG, "View User Event button clicked");
                    Intent intent = new Intent(UserDashboardActivity.this, UserEventListActivity.class);
                    startActivity(intent);
                }
            });

            // Handle button click for viewing schedule
            findViewById(R.id.btnViewSchedule).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent to navigate to Schedule Activity
                    Log.d(TAG, "View Schedule button clicked");
                    Intent intent = new Intent(UserDashboardActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu with options
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks using if-else
        Log.d(TAG, "Menu item selected: " + item.getItemId());
        if (item.getItemId() == R.id.mainInformation) {
            // Handle FAQ action
            Log.d(TAG, "FAQ selected");
            Intent intent = new Intent(UserDashboardActivity.this, InformationActivity.class); // Pindah ke FAQActivity
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.mainabout) {
            // Handle About action
            Log.d(TAG, "About selected");
            Intent intent = new Intent(UserDashboardActivity.this, AboutActivity.class); // Pindah ke FAQActivity
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.mainpenggunaan) {
            // Handle Penggunaan action
            Log.d(TAG, "Penggunaan selected");
            Intent intent = new Intent(UserDashboardActivity.this, PenggunaanActivity.class); // Pindah ke FAQActivity
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.mainlogout) {
            // Handle Logout action
            Log.d(TAG, "Logout selected");
            showLogoutConfirmationDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showLogoutConfirmationDialog() {
        try {
            // Create an AlertDialog to confirm logout
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Jangan pergi dulu🥹 Apakah Anda yakin ingin pergi?")
                    .setCancelable(false) // Prevent dismissing the dialog when clicking outside
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // If user confirms, exit the app
                            Log.d(TAG, "User confirmed logout");
                            finishAffinity();  // Close all activities and exit the app
                            System.exit(0);     // Optionally call this to force exit the app
                        }
                    })
                    .setNegativeButton("Tidak", null) // If user cancels, do nothing
                    .show();
        } catch (Exception e) {
            Log.e(TAG, "Error in logout dialog", e);
}
}
}
