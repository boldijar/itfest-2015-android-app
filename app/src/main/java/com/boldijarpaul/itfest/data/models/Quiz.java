package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Browsing on 11/7/2015.
 */
public class Quiz implements Serializable{

    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;
    @SerializedName("difficulty")
    public String difficulty;
    @SerializedName("about")
    public String about;
    @SerializedName("question")
    public String question;
    @SerializedName("answer1")
    public String answer1;
    @SerializedName("answer2")
    public String answer2;
    @SerializedName("answer3")
    public String answer3;
    @SerializedName("answer4")
    public String answer4;
    @SerializedName("answer5")
    public String answer5;
    @SerializedName("answer6")
    public String answer6;
    @SerializedName("details1")
    public String details1;
    @SerializedName("details2")
    public String details2;
    @SerializedName("details3")
    public String details3;
    @SerializedName("details4")
    public String details4;
    @SerializedName("details5")
    public String details5;
    @SerializedName("details6")
    public String details6;


}
