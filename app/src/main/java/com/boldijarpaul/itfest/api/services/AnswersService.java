package com.boldijarpaul.itfest.api.services;


import com.boldijarpaul.itfest.data.models.Answer;
import com.boldijarpaul.itfest.data.models.QuizResponse;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface AnswersService {

    @POST("/answer")
    Observable<String> addAnswer(
            @Body Answer answer
    );

}
