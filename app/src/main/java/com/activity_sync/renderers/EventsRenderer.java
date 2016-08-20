package com.activity_sync.renderers;

import android.widget.TextView;
import butterknife.Bind;
import com.activity_sync.R;
import com.activity_sync.presentation.models.Event;
import com.activity_sync.renderers.base.Renderer;
import com.activity_sync.renderers.base.RendererBuilder;

import java.util.Arrays;

public class EventsRenderer extends Renderer<Event>
{
    @Bind(R.id.event_organizer)
    TextView eventOrganizer;

    @Bind(R.id.event_desc)
    TextView eventDescription;

    @Bind(R.id.event_date)
    TextView eventDate;

    @Bind(R.id.event_location)
    TextView eventLocation;

    @Bind(R.id.event_disc)
    TextView eventDiscipline;

    public EventsRenderer(int layoutRes)
    {
        super(layoutRes);
    }

    @Override
    public void render()
    {
        eventOrganizer.setText(String.format("%s %s", getContent().getOrganizer().getUserDetails().getFirstName(), getContent().getOrganizer().getUserDetails().getFirstName()));
        eventDescription.setText(getContent().getDescription());
        eventDate.setText(getContent().getReadableDate());
        eventLocation.setText(getContent().getLocation().getName());
        eventDiscipline.setText(getContent().getDiscipline().getName());
    }

    public static class Builder extends RendererBuilder<Event>
    {
        public Builder()
        {
            super(Arrays.asList(new EventsRenderer(R.layout.event_item_view)));
        }

        @Override
        protected Class getPrototypeClass(Event content)
        {
            return EventsRenderer.class;
        }
    }
}
