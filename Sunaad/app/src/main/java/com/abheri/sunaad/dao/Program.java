package com.abheri.sunaad.dao;

import android.os.Parcelable;
import android.os.Parcel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Program implements Serializable{
    private long id;
    private String title;
    private String event_type;
    private String details;
    private String entry_fee;
    private String website;
    private String artiste;
    private double  duration;
    private Date   eventDate;
    private String startTime;
    private String endTime;
    private String place;
    private String phone;
    private String location_address1;
    private String location_address2;
    private String location_city;
    private String location_state;
    private String location_country;
    private String loacation_pincode;
    private String location_coords;
    private String location_eataries;
    private String parking;


    public long getId() {  return id;
    }
    public void setId(long id) { this.id = id;
    }


    public String getTitle() { return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getEventType() { return event_type;
    }
    public void setEventType(String event_type) {
        this.event_type = event_type;
    }


    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }


    public String getEntryFee() {
        return entry_fee;
    }
    public void setEntryFee(String entry_fee) {
        this.entry_fee = entry_fee;
    }


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public String getArtiste() {
        return artiste;
    }
    public void setArtiste(String artiste) {this.artiste = artiste;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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


    public String getLocation_address1() {
        return location_address1;
    }

    public void setLocation_address1(String location_address1) {
        this.location_address1 = location_address1;
    }

    public String getLocation_address2() {
        return location_address2;
    }

    public void setLocation_address2(String location_address2) {
        this.location_address2 = location_address2;
    }


    public String getLocation_city() {
        return location_city;
    }

    public void setLocation_city(String location_city) {
        this.location_city = location_city;
    }


    public String getLocation_state() {   return location_state;
    }
    public void setLocation_state(String location_state) {
        this.location_state = location_state;
    }


    public String getLocation_country() {   return location_country;
    }
    public void setLocation_country(String location_country) {
        this.location_country = location_country;
    }


    public String getLoacation_pincode() {   return loacation_pincode;
    }
    public void setLoacation_pincode(String loacation_pincode) {
        this.loacation_pincode = loacation_pincode;
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


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return title;
    }

}
