package com.abheri.sunaad.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Venue implements Serializable {
    public static int selectedPosition = -1;
    private long venue_id;
    private String venue_name;
    private String venue_description;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String mapcoords;
    private String website;
    private String email;
    private String image;
    private String parking;
    private String eataries;
    private String is_published;

    public long getId() {
        return venue_id;
    }

    public void setId(long id) {
        this.venue_id = id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String name) {
        this.venue_name = name;
    }

    public String getVenue_description() {
        return venue_description;
    }

    public void setVenue_description(String desc) {
        this.venue_description = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String location_address1) {
        this.address1 = location_address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String location_address2) {
        this.address2 = location_address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String location_city) {
        this.city = location_city;
    }

    public String getState() {
        return state;
    }

    public void setState(String location_state) {
        this.state = location_state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String location_country) {
        this.country = location_country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String loacation_pincode) {
        this.pincode = loacation_pincode;
    }

    public String getMapcoords() {
        return mapcoords;
    }

    public void setMapcoords(String location_coords) {
        this.mapcoords = location_coords;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getEataries() {
        return eataries;
    }

    public void setEataries(String eataries) {
        this.eataries = eataries;
    }

    public String getIs_published() {
        return is_published;
    }

    public void setIs_published(String published) {
        this.is_published = published;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return venue_name;
    }
    //Test

}
