package com.activity_sync.presentation;

import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.models.LoginResponse;
import retrofit2.http.*;
import rx.Observable;

import java.util.List;

public interface IActivitySyncApi
{
    @FormUrlEncoded
    @POST("/api-auth-token")
    Observable<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("/events/all")
    Observable<List<Event>> getEvents();

    @GET("/events/{id}")
    Observable<Event> getEvent(@Path("id") int id);
}
