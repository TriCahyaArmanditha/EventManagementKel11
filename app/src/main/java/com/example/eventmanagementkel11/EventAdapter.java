package com.example.eventmanagementkel11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private ArrayList<Event> eventList;

    public EventAdapter(Context context, ArrayList<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Meninflate layout item_event untuk setiap event
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.tvEventName.setText(event.getEventName());
        holder.tvEventDate.setText(event.getEventDate());

        // Menetapkan gambar dari folder drawable
        int imageResource = context.getResources().getIdentifier(event.getImageName(), "drawable", context.getPackageName());
        if (imageResource != 0) {
            holder.ivEventImage.setImageResource(imageResource);
        } else {
            holder.ivEventImage.setImageResource(R.drawable.placeholder_image); // Gambar placeholder jika tidak ada gambar
        }

        // Menangani klik pada tombol Register
        holder.btnRegister.setOnClickListener(v -> {
            // Logika untuk tombol Register
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();  // Mengembalikan jumlah item dalam daftar
    }

    // ViewHolder untuk item-event
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventName, tvEventDate;
        ImageView ivEventImage;
        Button btnRegister;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inisialisasi komponen UI berdasarkan ID dari item_event.xml
            tvEventName = itemView.findViewById(R.id.tvEventTitle);
            tvEventDate = itemView.findViewById(R.id.eventDate);
            ivEventImage = itemView.findViewById(R.id.ivEventImage);
            btnRegister = itemView.findViewById(R.id.btnRegisterEvent);
}
}
}
