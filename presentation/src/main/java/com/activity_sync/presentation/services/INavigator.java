package com.activity_sync.presentation.services;

import com.activity_sync.presentation.models.Event;

public interface INavigator
{
    void openDummyScreen();

    void openWelcomeScreen();

    void openLoginScreen();

    void openEventsScreen();

    void openEventDetailScreen(Event event);
}
