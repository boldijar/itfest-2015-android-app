package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Browsing on 11/7/2015.
 */
public class Answer {

    @SerializedName("id")
    public long id;
    @SerializedName("deviceId")
    public String deviceId;
    @SerializedName("quizId")
    public long quizId;
    @SerializedName("success")
    public boolean success;
}
