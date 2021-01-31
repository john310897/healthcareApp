package com.example.ifyoucanhelp;

public class IncidentsRegister {
    private String location;
    private String message;
    private String time;
    private String date;
    public IncidentsRegister() {

    }

    public IncidentsRegister(String location, String message, String time) {
        this.location = location;
        this.message = message;
        this.time = time;
        this.date=date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
