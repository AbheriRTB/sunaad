package com.abheri.sunaad.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Organizer implements Serializable {
    private long organizer_id;
    private String organizer_name;
    private String organizer_desc;
    private String organizer_website;
    private String organizer_email;
    private String organizer_logo;
    private String organizer_phone;
    private String organizer_address1;
    private String organizer_address2;
    private String organizer_city;
    private String organizer_state;
    private String organizer_country;
    private String organizer_pincode;
    private String organizer_mapcoords;
    private String is_published;

    public long getOrganizerId() {
        return organizer_id;
    }

    public void setOrganizerId(long orgid) {
        this.organizer_id = orgid;
    }

    public String getOrganizerName() {
        return organizer_name;
    }

    public void setOrganizerName(String name) {
        this.organizer_name = name;
    }

    public String getOrganizerDesc() {
        return organizer_desc;
    }

    public void setOrganizerDesc(String desc) {
        this.organizer_desc = desc;
    }

    public String getOrganizerWebsite() {
        return organizer_website;
    }

    public void setOrganizerWebsite(String website) {
        this.organizer_website = website;
    }

    public String getOrganizerEmail() {
        return organizer_email;
    }

    public void setOrganizerEmail(String email) {
        this.organizer_email = email;
    }

    public String getOrganizerLogo() {
        return organizer_logo;
    }

    public void setOrganizerLogo(String logo) {
        this.organizer_logo = logo;
    }

    public String getOrganizerPhone() {
        return organizer_phone;
    }

    public void setOrganizerPhone(String phone) {
        this.organizer_phone = phone;
    }

    public String getOrganizerAddress1() {
        return organizer_address1;
    }

    public void setOrganizerAddress1(String location_address1) {
        this.organizer_address1 = location_address1;
    }

    public String getOrganizerAddress2() {
        return organizer_address2;
    }

    public void setOrganizerAddress2(String location_address2) {
        this.organizer_address2 = location_address2;
    }


    public String getOrganizerCity() {
        return organizer_city;
    }

    public void setOrganizerCity(String location_city) {
        this.organizer_city = location_city;
    }


    public String getOrganizerState() {
        return organizer_state;
    }

    public void setOrganizerState(String location_state) {
        this.organizer_state = location_state;
    }


    public String getOrganizerCountry() {
        return organizer_country;
    }

    public void setOrganizerCountry(String location_country) {
        this.organizer_country = location_country;
    }


    public String getOrganizerPincode() {
        return organizer_pincode;
    }

    public void setOrganizerPincode(String loacation_pincode) {
        this.organizer_pincode = loacation_pincode;
    }


    public String getOrganizerCoords() {
        return organizer_mapcoords;
    }

    public void setOrganizerCoords(String org_coords) {
        this.organizer_mapcoords = org_coords;
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
        return organizer_name;
    }
    //Test

}
