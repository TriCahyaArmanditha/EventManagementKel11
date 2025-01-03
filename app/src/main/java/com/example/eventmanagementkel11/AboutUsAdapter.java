package com.example.eventmanagementkel11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.AboutUsViewHolder> {

    private List<AboutUsItem> aboutUsList;

    public AboutUsAdapter(List<AboutUsItem> aboutUsList) {
        this.aboutUsList = aboutUsList;
    }

    @Override
    public AboutUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_us, parent, false);
        return new AboutUsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutUsViewHolder holder, int position) {
        AboutUsItem item = aboutUsList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.universityTextView.setText(item.getUniversity());
        holder.photoImageView.setImageResource(item.getPhotoResId());
    }

    @Override
    public int getItemCount() {
        return aboutUsList.size();
    }

    public static class AboutUsViewHolder extends RecyclerView.ViewHolder {

        ImageView photoImageView;
        TextView nameTextView;
        TextView universityTextView;

        public AboutUsViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photo_imageview);
            nameTextView = itemView.findViewById(R.id.name_textview);
            universityTextView = itemView.findViewById(R.id.university_textview);
}
}
}
