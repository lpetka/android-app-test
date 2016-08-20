package com.activity_sync.presentation.repository;

import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.models.LoginResponse;
import rx.Observable;

import java.util.List;

public interface IRestService
{
    Observable<LoginResponse> login(String username, String password);

    Observable<List<Event>> getEvents();

    Observable<Event> getEvent(int eventID);
}
