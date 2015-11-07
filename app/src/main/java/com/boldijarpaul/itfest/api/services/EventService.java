package com.boldijarpaul.itfest.api.services;


import com.boldijarpaul.itfest.data.models.PersonResponse;

import retrofit.http.GET;
import rx.Observable;

public interface EventService {

    @GET("/person,class?transform=1")
    Observable<PersonResponse> getPeople(
    );

}
