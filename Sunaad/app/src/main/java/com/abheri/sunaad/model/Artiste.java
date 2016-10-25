package com.abheri.sunaad.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Artiste implements Serializable {
    private long id;
    private String artiste_name;
    private String phone;
    private String location_address1;
    private String location_address2;
    private String location_city;
    private String location_state;
    private String location_country;
    private String loacation_pincode;
    private String location_coords;

    private String is_published;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtiste_name() {
        return artiste_name;
    }

    public void setArtiste_name(String name) {
        this.artiste_name = name;
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


    public String getLocation_state() {
        return location_state;
    }

    public void setLocation_state(String location_state) {
        this.location_state = location_state;
    }


    public String getLocation_country() {
        return location_country;
    }

    public void setLocation_country(String location_country) {
        this.location_country = location_country;
    }


    public String getLoacation_pincode() {
        return loacation_pincode;
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

    public String getIs_published() {
        return is_published;
    }

    public void setIs_published(String published) {
        this.is_published = published;
    }


    public static int selectedPosition = -1;


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return artiste_name;
    }
    //Test

}
