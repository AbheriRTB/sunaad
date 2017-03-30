package com.abheri.sunaad.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */

public class Program implements Serializable{
    private long id;
    private String title;
    private String art_type;
    private String details;
    private String entry_fee;
    private String organizer_website;
    private String artiste_name;
    private String accompanists;
    private double  duration;
    private Date   eventDate;
    private String startTime;
    private String endTime;
    private String venue_name;
    private String organizer_name;
    private String organizer_phone;
    private String venue_address1;
    private String venue_address2;
    private String venue_city;
    private String venue_state;
    private String venue_country;
    private String venue_pincode;
    private String venue_coords;
    private String venue_eataries;
    private String venue_parking;
    private String artiste_image;
    private String is_featured;
    private String splash_url;
    private String is_published;

    //Locally updated (not from services)
    public long alarm_millis=-1;


    public long getId() {  return id;
    }
    public void setId(long id) { this.id = id;
    }


    public String getTitle() { return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getArtType() { return art_type;
    }
    public void setArtType(String art_type) {
        this.art_type = art_type;
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


    public String getOrganizerWebsite() {
        return organizer_website;
    }

    public void setOrganizerWebsite(String organizer_website) {
        this.organizer_website = organizer_website;
    }


    public String getArtisteName() {
        return artiste_name;
    }
    public void setArtisteName(String artiste) {this.artiste_name = artiste;
    }

    public String getAccompanists() {
        return accompanists;
    }
    public void setAccompanists(String accompanists) {this.accompanists = accompanists;
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


    public String getOrganizerName() {
        return organizer_name;
    }

    public void setOrganizerName(String organizer_name) {
        this.organizer_name = organizer_name;
    }

    public String getVenueName() {
        return venue_name;
    }

    public void setVenueName(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getOrganizerPhone() {
        return organizer_phone;
    }

    public void setOrganizerPhone(String phone) {
        this.organizer_phone = phone;
    }


    public String getVenueAddress1() {
        return venue_address1;
    }

    public void setVenueAddress1(String location_address1) {
        this.venue_address1 = location_address1;
    }

    public String getVenueAddress2() {
        return venue_address2;
    }

    public void setVenueAddress2(String location_address2) {
        this.venue_address2 = location_address2;
    }


    public String getVenueCity() {
        return venue_city;
    }

    public void setVenueCity(String location_city) {
        this.venue_city = location_city;
    }


    public String getVenueState() {   return venue_state;
    }
    public void setVenueState(String location_state) {
        this.venue_state = location_state;
    }


    public String getVenueCountry() {   return venue_country;
    }
    public void setVenueCountry(String location_country) {
        this.venue_country = location_country;
    }


    public String getVenuePincode() {   return venue_pincode;
    }
    public void setVenuePincode(String loacation_pincode) {
        this.venue_pincode = loacation_pincode;
    }


    public String getVenueCoords() {
        return venue_coords;
    }
    public void setVenueCoords(String location_coords) {
        this.venue_coords = location_coords;
    }


    public String getVenueEataries() {
        return venue_eataries;
    }
    public void setVenueEataries(String location_eataries) {
        this.venue_eataries = location_eataries;
    }


    public String getVenueParking() {
        return venue_parking;
    }
    public void setVenueParking(String parking) {
        this.venue_parking = parking;
    }

    public String getArtiste_image() {
        return artiste_image;
    }
    public void setArtiste_image(String image) {
        this.artiste_image = image;
    }

    public String getIs_featured() {
        return is_featured;
    }
    public void setIs_featured(String featured) {
        this.is_featured = featured;
    }

    public String getSplash_url() {
        return splash_url;
    }
    public void setSplash_url(String splashUrl) {
        if(null != splashUrl && splashUrl.equalsIgnoreCase("null")) {
            this.splash_url = "";
        }
        else{
            this.splash_url = splashUrl;
        }
    }

    public String getIs_published() {
        return is_published;
    }
    public void setIs_published(String published) {
        this.is_published = published;
    }



    public static int selectedPosition=-1;


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return title;
    }
    //Test

}
