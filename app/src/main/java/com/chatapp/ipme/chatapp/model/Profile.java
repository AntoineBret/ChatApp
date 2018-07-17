package com.chatapp.ipme.chatapp.model;

public class Profile {

    private String profileItem;
    private String profileData;

    public Profile() {
    }

    public Profile(String profileItem, String profileData) {
        this.profileItem = profileItem;
        this.profileData = profileData;
    }

    public String getProfileItem() {
        return profileItem;
    }

    public Profile setProfileItem(String profileItem) {
        this.profileItem = profileItem;
        return this;
    }

    public String getProfileData() {
        return profileData;
    }

    public Profile setProfileData(String profileData) {
        this.profileData = profileData;
        return this;
    }
}
