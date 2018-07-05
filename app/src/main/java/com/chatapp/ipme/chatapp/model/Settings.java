package com.chatapp.ipme.chatapp.model;

import android.widget.ImageView;

public class Settings {

    private ImageView settingsThumbnail;
    private String settingsTitle;

    public Settings(int cover, String settingsTitle) {
    }

    public Settings(ImageView settingsThumbnail, String settingsTitle) {
        this.settingsThumbnail = settingsThumbnail;
        this.settingsTitle = settingsTitle;
    }

    public ImageView getSettingsThumbnail() {
        return settingsThumbnail;
    }

    public void setSettingsThumbnail(ImageView settingsThumbnail) {
        this.settingsThumbnail = settingsThumbnail;
    }

    public String getSettingsTitle() {
        return settingsTitle;
    }

    public void setSettingsTitle(String settingsTitle) {
        this.settingsTitle = settingsTitle;
    }
}
