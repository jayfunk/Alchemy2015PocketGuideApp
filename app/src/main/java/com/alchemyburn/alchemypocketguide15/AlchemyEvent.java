package com.alchemyburn.alchemypocketguide15;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by jfunk on 9/22/15.
 */
public class AlchemyEvent {

    String startDate;
    String endDate;
    String[] days;
    String eventName;
    String location;
    String description;
    Boolean allDayEvent;
    private JSONObject JSONEvent;

    public AlchemyEvent(JSONObject event) {
        setJSONEvent(event);
        setStartDate(event.optString("Start"));
        setEndDate(event.optString("End"));
        setEventName(event.optString("Event Name"));
        setLocation(event.optString("Location"));
        setDescription(event.optString("Desription"));
        setAllDayEvent(event.optString("All Day Event?"));
    }

    public Boolean getAllDayEvent() {
        return allDayEvent;
    }

    public void setAllDayEvent(String allDayEvent) {
        if(allDayEvent.toLowerCase().equals("yes")) {
            this.allDayEvent = true;
        }else {
            this.allDayEvent = false;
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days  = days.split(",");
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
}
