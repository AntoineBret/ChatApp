package com.chatapp.ipme.chatapp.model;

import android.widget.ImageView;

public class Profile {

    private String name;
    private String surname;
    private String birthdate;
    private ImageView thumbnail;

    public Profile() {
    }

    public Profile(String name, String surname, String birthdate, ImageView thumbnail) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
    }
}
