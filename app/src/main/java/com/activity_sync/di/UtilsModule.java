package com.activity_sync.di;

import android.content.Context;
import com.activity_sync.presentation.repository.RestService;
import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.widgets.ISecureStorage;
import com.activity_sync.services.Navigator;
import com.activity_sync.utils.SecureStorage;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {ContextModule.class})
public class UtilsModule
{
    @Provides
    @Singleton
    public INavigator provideNavigator(Context context)
    {
        return new Navigator(context);
    }

    @Provides
    @Singleton
    public IRestService provideApiRestService()
    {
        return new RestService("https://secret-gorge-99838.herokuapp.com");
    }

    @Provides
    @Singleton
    public ISecureStorage provideSecureStorage(Context context)
    {
        return new SecureStorage(context);
    }
}