package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Browsing on 11/3/2015.
 */
public class Person {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("class")
    public List<Class> favoriteClasses;
}
