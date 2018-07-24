package com.chatapp.ipme.chatapp.model;

public class Profile {

    public Profile() {
    }

    private String profileItem;
    private String profileData;
    private int profileThumbnail;

    public Profile(String profileItem, String profileData, int profileThumbnail) {
        this.profileItem = profileItem;
        this.profileData = profileData;
        this.profileThumbnail = profileThumbnail;
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

    public int getProfileThumbnail() {
        return profileThumbnail;
    }

    public Profile setProfileThumbnail(int profileThumbnail) {
        this.profileThumbnail = profileThumbnail;
        return this;
    }
}
