package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Browsing on 11/7/2015.
 */
public class User implements Serializable {

    @SerializedName("id")
    public long id;
    @SerializedName("fullname")
    public String fullName;
    @SerializedName("deviceId")
    public String deviceId;
    @SerializedName("age")
    public int age;
}
