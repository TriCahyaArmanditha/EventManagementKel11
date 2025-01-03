package com.example.eventmanagementkel11;

public class Event {
    private String eventId;
    private String eventName;
    private String eventDate;
    private String imageName;

    // Constructor
    public Event(String eventId, String eventName, String eventDate, String imageName) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.imageName = imageName;
    }

    // Default constructor (untuk Firebase)
    public Event() {
    }

    // Getter and Setter
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
}
}
