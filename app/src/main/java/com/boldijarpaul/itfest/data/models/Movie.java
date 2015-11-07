package com.boldijarpaul.itfest.data.models;

import java.util.List;

/**
 * Created by Browsing on 11/5/2015.
 */
public class Movie {
    public int id;
    public String name;
    public int releaseYear;
    public int genreId;
    public List<Genre> genres;
}
