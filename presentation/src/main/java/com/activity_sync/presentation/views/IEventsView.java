package com.activity_sync.presentation.views;

import com.activity_sync.presentation.models.Event;
import rx.Observable;

import java.util.Collection;

public interface IEventsView
{
    Observable<Event> selectedEvent();

    void addEvent(Event event);
    void addEventsList(Collection<Event> events);
    void showError();

    Observable addEventButtonClick();
}
