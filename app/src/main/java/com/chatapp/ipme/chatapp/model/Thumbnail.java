package com.chatapp.ipme.chatapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Thumbnail {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("file")
    @Expose
    private File file;
}
