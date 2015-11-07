package com.boldijarpaul.itfest.presenter.views;

import com.boldijarpaul.itfest.data.models.Movie;
import com.boldijarpaul.itfest.data.models.PersonResponse;

import java.util.List;

/**
 * Created by Browsing on 11/3/2015.
 */
public interface PeopleView {
    void showPeople(PersonResponse personResponse);
    void showMovies(List<Movie> movies);
}
