package com.example.demo.model;

public class Request {
    private String message;
    private String phoneNumber;
    private String scheduledTime;

    public Request(String message, String phoneNumber, String scheduledTime) {
        this.message = message;
        this.phoneNumber = phoneNumber;
        this.scheduledTime = scheduledTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    @Override
    public String toString(){
        return "Request [message=" + message + ", phonenumber=" + phoneNumber + ", scheduledTime=" + scheduledTime
                + "]";
    }
}
