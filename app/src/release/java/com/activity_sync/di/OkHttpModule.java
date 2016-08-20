package com.activity_sync.di;

import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OkHttpModule
{
    @Singleton
    @Provides
    public OkHttpClient provideHttpClient()
    {
        return  new OkHttpClient();
    }
}
