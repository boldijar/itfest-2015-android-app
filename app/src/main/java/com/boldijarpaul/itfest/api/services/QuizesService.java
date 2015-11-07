package com.boldijarpaul.itfest.api.services;


import com.boldijarpaul.itfest.data.models.QuizResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface QuizesService {

    @GET("/quiz?transform=1")
    Observable<QuizResponse> getQuizes(

    );

    @GET("/quiz?transform=1&order=id")
    Observable<QuizResponse> getQuizes(@Query("page") String page

    );

}
