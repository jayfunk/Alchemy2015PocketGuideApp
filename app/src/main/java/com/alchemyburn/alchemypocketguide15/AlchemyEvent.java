package com.alchemyburn.alchemypocketguide15;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by jfunk on 9/22/15.
 */
public class AlchemyEvent {

    String startTime;
    String endTime;
    String[] days;
    String eventName;
    String location;
    String description;
    String allDayEvent;
    Boolean isAllDayEvent;
    private JSONObject JSONEvent;
    private String daysString;
    private String daysAbrv;
    private String dayDate;

    public AlchemyEvent(JSONObject event) {
        setJSONEvent(event);
        setStartTime(event.optString("Start"));
        setEndTime(event.optString("End"));
        setEventName(event.optString("Event Name"));
        setLocation(event.optString("Location"));
        setDescription(event.optString("Description"));
        setAllDayEvent(event.optString("All Day Event?"));
        setDays(event.optString("Days"));
    }

    public String getAllDayEvent() {
        return allDayEvent;
    }

    public void setAllDayEvent(String allDayEvent) {
        if (allDayEvent.toLowerCase().equals("yes")) {
            this.isAllDayEvent = true;
        } else {
            this.isAllDayEvent = false;
        }

        this.allDayEvent = allDayEvent;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStartDate() {
        String startDate = startTime;

        return startDate + " " + getDayDate(days[0]);
    }

    public String getEndDate() {
        String startDate = endTime;

        return startDate + " " + getDayDate(days[days.length-1]);
    }

    public void setStartTime(String startDate) {
        this.startTime = startDate.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endDate) {
        this.endTime = endDate.trim();
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = makeDaysArray(days);
        this.daysAbrv = makeDayAbrvs();
        this.daysString = days;
    }

    private String[] makeDaysArray(String days) {
        String[] daysArray = days.split(",");

        for (int i = 0; i < daysArray.length; i++) {
            String day = daysArray[i];
            daysArray[i] = day.trim();
        }

        return daysArray;
    }

    private String makeDayAbrvs() {
        String abrvDays = "";

        for (int i = 0; i < days.length; i++) {
            String day = days[i];

            abrvDays += day.trim().substring(0, 2).toUpperCase() + ",";
        }

        return abrvDays.substring(0, abrvDays.length() - 1);
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject getJSONEvent() {
        return JSONEvent;
    }

    public void setJSONEvent(JSONObject JSONEvent) {
        this.JSONEvent = JSONEvent;
    }

    public String getDaysText() {
        return daysString;
    }

    public String getDaysAbrv() {
        return daysAbrv;
    }

    public String getDayDate(String day) {
        String dayDate = null;
        switch (day.toLowerCase()){
            case "thursday":
                dayDate = "10/01/15";
                break;
            case "friday":
                dayDate = "10/02/15";
                break;
            case "saturday":
                dayDate = "10/03/15";
                break;
            case "sunday":
                dayDate = "10/04/15";
                break;
        }
        return dayDate;
    }
}
