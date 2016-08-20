package com.activity_sync.screens;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import butterknife.Bind;
import com.activity_sync.App;
import com.activity_sync.R;
import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.presenters.EventsPresenter;
import com.activity_sync.presentation.presenters.IPresenter;
import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.views.IEventsView;
import com.activity_sync.renderers.EventsRenderer;
import com.activity_sync.renderers.base.DividerItemDecoration;
import com.activity_sync.renderers.base.RVRendererAdapter;
import com.activity_sync.widgets.MyRecyclerView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.ViewObservable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventsScreen extends ScreenWithNavigation implements IEventsView
{
    @Inject
    INavigator navigator;

    @Inject
    IRestService restService;

    @Bind(R.id.events_list)
    MyRecyclerView eventsList;

    @Bind(R.id.add_event_floating_button)
    FloatingActionButton addEventButton;

    public EventsScreen()
    {
        super(R.layout.events_screen);
    }

    private RVRendererAdapter<Event> adapter;
    private List<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        App.component(this).inject(this);

        super.onCreate(savedInstanceState);
        adapter = new RVRendererAdapter<>(this, new EventsRenderer.Builder());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        eventsList.setLayoutManager(linearLayoutManager);
        eventsList.addItemDecoration(new DividerItemDecoration(this));
        eventsList.setAdapter(adapter);

        setTitle(R.string.title_events);
    }

    @Override
    protected IPresenter createPresenter(Screen screen, Bundle savedInstanceState)
    {
        return new EventsPresenter(this, navigator, AndroidSchedulers.mainThread(), restService);
    }

    @Override
    public Observable<Event> selectedEvent()
    {
        return adapter.itemSelected();
    }

    @Override
    public Observable addEventButtonClick()
    {
        return ViewObservable.clicks(addEventButton);
    }

    @Override
    public void addEvent(Event event)
    {
        this.events.add(event);
        adapter.add(event);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addEventsList(Collection<Event> events)
    {
        adapter.clear();
        this.events.addAll(events);
        adapter.addAll(events);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError()
    {
        Toast.makeText(this, R.string.repository_events_error, Toast.LENGTH_LONG).show();
    }
}