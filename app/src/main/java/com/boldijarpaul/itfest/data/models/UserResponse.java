package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class UserResponse {

    @SerializedName("user")
    public List<User> users;
}
