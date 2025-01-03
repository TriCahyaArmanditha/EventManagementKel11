package com.example.eventmanagementkel11;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    private RecyclerView aboutUsRecyclerView;
    private AboutUsAdapter aboutUsAdapter;
    private List<AboutUsItem> aboutUsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up Toolbar for navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back button

        // Initialize RecyclerView
        aboutUsRecyclerView = findViewById(R.id.about_us_list);
        aboutUsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create data
        aboutUsList = new ArrayList<>();
        aboutUsList.add(new AboutUsItem(R.drawable.fotoicha, "Tri Cahya Armanditha", "Developer"));
        aboutUsList.add(new AboutUsItem(R.drawable.fotoluluk, "Mamluatul hasanah", "Partner Design"));
        aboutUsList.add(new AboutUsItem(R.drawable.fotosabrina, "Sabrina Nur Alifaa", "Documentation"));
        aboutUsList.add(new AboutUsItem(R.drawable.fotoratu, "Fadhilah Qurratul Aini ", "Reporting Spesialist"));
        aboutUsList.add(new AboutUsItem(R.drawable.fotomizen, "Ahmat Mizen", "Documentation"));
        aboutUsList.add(new AboutUsItem(R.drawable.fotowildan, "Achmad Wildan S", "Documentation"));
        aboutUsList.add(new AboutUsItem(R.drawable.fototemen, "Gita mawaddatil pujiyanti", "Documentation"));
        aboutUsList.add(new AboutUsItem(R.drawable.uniba, "Universitas Bahaudin Mudhary", "Our Campus"));



        // Set adapter
        aboutUsAdapter = new AboutUsAdapter(aboutUsList);
        aboutUsRecyclerView.setAdapter(aboutUsAdapter);
    }

    // Handle toolbar item clicks (navigation)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle back navigation
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
}
}
}
