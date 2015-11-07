package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Browsing on 11/3/2015.
 */
public class PersonResponse {
    @SerializedName("person")
    public List<Person> people;
}
