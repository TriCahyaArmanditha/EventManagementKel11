package com.example.eventmanagementkel11;

public class Schedule {
    private String eventName;
    private String day;
    private String startTime;
    private String endTime;

    // Constructor untuk Schedule (dengan parameter Map)
    public Schedule(String eventName, String day, String startTime, String endTime) {
        this.eventName = eventName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getter dan Setter untuk setiap field
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
}
}
