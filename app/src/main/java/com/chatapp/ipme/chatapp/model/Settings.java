package com.chatapp.ipme.chatapp.model;

public class Settings {

  public Settings() {
  }

  public String settingsTitle;
  public int settingsThumbnail;

  public Settings(String settingsTitle, int settingsThumbnail) {
    this.settingsTitle = settingsTitle;
    this.settingsThumbnail = settingsThumbnail;
  }

  public String getSettingsTitle() {
    return settingsTitle;
  }

  public void setSettingsTitle(String settingsTitle) {
    this.settingsTitle = settingsTitle;
  }

  public int getSettingsThumbnail() {
    return settingsThumbnail;
  }

  public void setSettingsThumbnail(int settingsThumbnail) {
    this.settingsThumbnail = settingsThumbnail;
  }
}
