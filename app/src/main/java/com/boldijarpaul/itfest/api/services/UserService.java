package com.boldijarpaul.itfest.api.services;


import com.boldijarpaul.itfest.data.models.QuizResponse;
import com.boldijarpaul.itfest.data.models.User;
import com.boldijarpaul.itfest.data.models.UserResponse;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

public interface UserService {


    @POST("/user")
    Observable<String> addUser(@Body User user
    );


    @GET("/user")
    Observable<UserResponse> getUsers(@Query("filter") String containsFilter,
                                      @Query("filter") String ageFilter
    );
}
