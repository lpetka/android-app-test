package com.activity_sync.presentation.presenters;

import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.views.IEventDetailsView;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class EventDetailsPresenter extends Presenter<IEventDetailsView>
{
    private final Event event;
    private final IRestService restService;
    private final Scheduler uiThread;

    public EventDetailsPresenter(IEventDetailsView view, Scheduler uiThread, Event event, IRestService restService)
    {
        super(view);
        this.event = event;
        this.restService = restService;
        this.uiThread = uiThread;
    }

    @Override
    public void start()
    {
        super.start();

        restService.getEvent(this.event.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(uiThread)
                .subscribe(event ->
                {
                    view.loadEventData(event);
                }, throwable ->
                {
                    Timber.e(throwable.getMessage());
                    view.showError();
                });
    }
}
