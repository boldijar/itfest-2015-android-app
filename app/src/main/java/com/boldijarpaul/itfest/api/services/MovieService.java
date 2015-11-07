package com.boldijarpaul.itfest.api.services;


import com.boldijarpaul.itfest.data.models.MovieResponse;
import com.boldijarpaul.itfest.data.models.PersonResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface MovieService {

    @GET("/movie,genre?transform=1?order=id")
    Observable<MovieResponse> getMovies(
            @Query("page") String page
    );

}
