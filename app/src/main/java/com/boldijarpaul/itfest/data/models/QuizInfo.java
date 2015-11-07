package com.boldijarpaul.itfest.data.models;

import java.io.Serializable;

/**
 * Created by Browsing on 11/7/2015.
 */
public class QuizInfo implements Serializable{
    public String url;
    public String details;

    public QuizInfo(String url, String details) {
        this.url = url;
        this.details = details;
    }

    public QuizInfo() {
    }
}
