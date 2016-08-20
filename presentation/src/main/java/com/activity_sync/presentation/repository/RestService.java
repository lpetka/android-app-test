package com.activity_sync.presentation.repository;

import com.activity_sync.presentation.IActivitySyncApi;
import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.models.LoginResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import java.util.List;

public class RestService implements IRestService
{
    private IActivitySyncApi apiService;

    public RestService(String baseUrl)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        apiService = retrofit.create(IActivitySyncApi.class);
    }

    @Override
    public Observable<LoginResponse> login(String username, String password)
    {
        return apiService.login(username, password);
    }

    @Override
    public Observable<List<Event>> getEvents()
    {
        return apiService.getEvents();
    }

    @Override
    public Observable<Event> getEvent(int eventId)
    {
        return apiService.getEvent(eventId);
    }


}
