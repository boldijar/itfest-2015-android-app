package com.boldijarpaul.itfest.api.services;


import com.boldijarpaul.itfest.data.models.MovieResponse;
import com.boldijarpaul.itfest.data.models.QuizResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface QuizesService {

    @GET("/quiz?transform=1")
    Observable<QuizResponse> getQuizes(

    );

}
