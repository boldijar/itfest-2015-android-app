package com.boldijarpaul.itfest.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class AnswerResponse {
    @SerializedName("answer")
    public List<Answer> answers;
}
