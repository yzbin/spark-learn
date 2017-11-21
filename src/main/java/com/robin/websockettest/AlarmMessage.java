package com.robin.websockettest;

public class AlarmMessage {
    private String fanNo;
    private String time;
    private String description;

    public AlarmMessage(String fanNo, String time, String description) {
        this.fanNo = fanNo;
        this.time = time;
        this.description = description;
    }

    public String getFanNo() {
        return fanNo;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
