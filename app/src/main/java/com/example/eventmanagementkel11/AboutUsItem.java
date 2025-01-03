package com.example.eventmanagementkel11;

public class AboutUsItem {

    private int photoResId;
    private String name;
    private String university;

    public AboutUsItem(int photoResId, String name, String university) {
        this.photoResId = photoResId;
        this.name = name;
        this.university = university;
    }

    public int getPhotoResId() {
        return photoResId;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
}
}