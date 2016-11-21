package com.abheri.sunaad.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Artiste implements Serializable {
    private long id;
    private String artiste_name;
    private String artiste_description;
    private String artiste_phone;
    private String artiste_address1;
    private String artiste_address2;
    private String artiste_city;
    private String artiste_state;
    private String artiste_country;
    private String artiste_pincode;
    private String artiste_coords;
    private String artiste_website;
    private String artiste_image;
    private String artiste_instrument;
    private String artiste_audio_clip;
    private String art_type;
    private String artiste_email;
    private Date artiste_DOB;
    private String artiste_gender;
    private String is_published;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtisteName() {
        return artiste_name;
    }

    public void setArtisteName(String name) {
        this.artiste_name = name;
    }

    public String getArtiste_description() {
        return artiste_description;
    }

    public void setArtiste_description(String description) {
        this.artiste_description = description;
    }

    public String getArtistePhone() {
        return artiste_phone;
    }

    public void setArtistePhone(String phone) {
        this.artiste_phone = phone;
    }


    public String getArtisteAddress1() {
        return artiste_address1;
    }

    public void setArtisteAddress1(String location_address1) {
        this.artiste_address1 = location_address1;
    }

    public String getArtisteAddress2() {
        return artiste_address2;
    }

    public void setArtisteAddress2(String location_address2) {
        this.artiste_address2 = location_address2;
    }


    public String getArtisteCity() {
        return artiste_city;
    }

    public void setArtisteCity(String location_city) {
        this.artiste_city = location_city;
    }


    public String getArtisteState() {
        return artiste_state;
    }

    public void setArtisteState(String location_state) {
        this.artiste_state = location_state;
    }


    public String getArtisteCountry() {
        return artiste_country;
    }

    public void setArtisteCountry(String location_country) {
        this.artiste_country = location_country;
    }


    public String getArtistePincode() {
        return artiste_pincode;
    }

    public void setArtistePincode(String loacation_pincode) {
        this.artiste_pincode = loacation_pincode;
    }


    public String getArtisteCoords() {
        return artiste_coords;
    }

    public void setArtisteCoords(String location_coords) {
        this.artiste_coords = location_coords;
    }

    public String getArtisteImage() {
        return artiste_image;
    }

    public void setArtisteImage(String image) {
        this.artiste_image = image;
    }

    public String getArtisteWebsite() {
        return artiste_website;
    }

    public void setArtisteWebsite(String website) {
        this.artiste_website = website;
    }

    public String getArtisteInstrument() {
        return artiste_instrument;
    }

    public void setArtisteInstrument(String instrument) {
        this.artiste_instrument = instrument;
    }

    public String getArtisteAudioClip() {
        return artiste_audio_clip;
    }

    public void setArtisteAudioClip(String audioClip) {
        this.artiste_audio_clip = audioClip;
    }

    public String getArtisteArtType() {
        return art_type;
    }

    public void setArtisteArtType(String artType) {
        this.art_type = artType;
    }

    public String getArtisteEmail() {
        return artiste_email;
    }

    public void setArtisteEmail(String email) {
        this.artiste_email = email;
    }

    public Date getArtisteDOB() {
        return artiste_DOB;
    }

    public void setArtisteDOB(Date dob) {
        this.artiste_DOB = dob;
    }

    public String getArtisteGender() {
        return artiste_gender;
    }

    public void setArtisteGender(String gender) {
        this.artiste_gender = gender;
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
