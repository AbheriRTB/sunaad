package com.abheri.sunaad.dao;

import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */

public class Program {
    private long id;
    private String title;
    private String details;
    private Date eventDate;
    private String startTime;
    private String endTime;
    private String place;
    private String phone;
    private String location_address;
    private String location_coords;
    private String location_eataries;
    private String parking;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getLocation_coords() {
        return location_coords;
    }

    public void setLocation_coords(String location_coords) {
        this.location_coords = location_coords;
    }

    public String getLocation_eataries() {
        return location_eataries;
    }

    public void setLocation_eataries(String location_eataries) {
        this.location_eataries = location_eataries;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public static int selectedPosition=-1;

    public long getId() {

        return id;
    }
    public void setId(long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {

        return title;
    }
}
