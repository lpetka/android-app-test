package com.activity_sync.di;

import com.activity_sync.App;
import com.activity_sync.screens.EventDetailsScreen;
import com.activity_sync.screens.EventsScreen;
import com.activity_sync.screens.LoginScreen;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {UtilsModule.class })
public interface DiComponent
{
    final class Initializer
    {
        private Initializer()
        {

        }
        public static DiComponent init(App app)
        {
            return DaggerDiComponent.builder()
                    .contextModule(new ContextModule(app))
                    .build();
        }
    }

    void inject(App app);

    void inject(LoginScreen loginScreen);

    void inject(EventsScreen eventsScreen);

    void inject(EventDetailsScreen eventDetailsScreen);
}