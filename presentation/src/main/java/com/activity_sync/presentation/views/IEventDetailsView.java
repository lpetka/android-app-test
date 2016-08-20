package com.activity_sync.presentation.views;

import com.activity_sync.presentation.models.Event;

public interface IEventDetailsView
{
    void loadEventData(Event event);

    void showError();
}
