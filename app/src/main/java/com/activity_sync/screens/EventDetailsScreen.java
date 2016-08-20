package com.activity_sync.screens;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import com.activity_sync.App;
import com.activity_sync.R;
import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.presenters.EventDetailsPresenter;
import com.activity_sync.presentation.presenters.EventsPresenter;
import com.activity_sync.presentation.presenters.IPresenter;
import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.views.IEventDetailsView;
import rx.android.schedulers.AndroidSchedulers;

import javax.inject.Inject;

public class EventDetailsScreen extends ScreenWithNavigation implements IEventDetailsView
{
    @Inject
    IRestService restService;

    @Bind(R.id.disc)
    TextView discipline;

    @Bind(R.id.organizer)
    TextView organizer;

    @Bind(R.id.date)
    TextView date;

    @Bind(R.id.location)
    TextView location;

    @Bind(R.id.player_number)
    TextView playerNumber;

    @Bind(R.id.creditability)
    TextView creditability;


    @Bind(R.id.description)
    TextView description;

    private Event event;

    @Override
    protected IPresenter createPresenter(Screen screen, Bundle savedInstanceState)
    {
        this.event = (Event) getIntent().getExtras().getSerializable(EventsPresenter.EVENT_CHOSEN);
        setTitle(R.string.title_event_details);

        return new EventDetailsPresenter(this, AndroidSchedulers.mainThread(), event, restService);
    }

    public EventDetailsScreen()
    {
        super(R.layout.event_details_screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        App.component(this).inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadEventData(Event event)
    {
        this.discipline.setText(event.getDiscipline().getName());
        this.date.setText(event.getReadableDate());
        this.creditability.setText(event.getOrganizer().getCreditability());
        this.location.setText(event.getLocation().getName());
        this.description.setText(event.getDescription());
        this.organizer.setText(String.format("%s %s", event.getOrganizer().getUserDetails().getFirstName(), event.getOrganizer().getUserDetails().getLastName()));
        this.playerNumber.setText(String.format("%d", event.getPlaces()));
    }

    @Override
    public void showError()
    {
        Toast.makeText(this, R.string.repository_event_details_error, Toast.LENGTH_LONG).show();
    }
}