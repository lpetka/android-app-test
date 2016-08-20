package com.activity_sync.presentation.presenters;

import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.views.IEventsView;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class EventsPresenter extends Presenter<IEventsView>
{
    public static final String EVENT_CHOSEN = "EVENT_CHOSEN";

    private final INavigator navigator;
    private final IRestService restService;

    private Scheduler scheduler;

    public EventsPresenter(IEventsView view, INavigator navigator, Scheduler scheduler, IRestService restService)
    {
        super(view);
        this.navigator = navigator;
        this.scheduler = scheduler;
        this.restService = restService;
    }

    @Override
    public void start()
    {
        super.start();

        this.loadEvents();

        this.subscriptions.add(view.addEventButtonClick()
                .subscribe(o -> {
                    navigator.openDummyScreen();
                })
        );

        this.subscriptions.add(this.view.selectedEvent()
                .subscribe(navigator::openEventDetailScreen)
        );
    }

    private void loadEvents()
    {
        this.restService.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(events ->
                {
                    view.addEventsList(events);
                }, throwable ->
                {
                    Timber.e(throwable.getMessage());
                    view.showError();
                });
    }
}
