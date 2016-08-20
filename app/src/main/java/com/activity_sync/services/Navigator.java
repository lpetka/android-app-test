package com.activity_sync.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.activity_sync.presentation.models.Event;
import com.activity_sync.presentation.presenters.EventsPresenter;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.screens.*;

public class Navigator implements INavigator
{
    private final Context context;

    public Navigator(Context context)
    {
        this.context = context;
    }

    @Override
    public void openDummyScreen()
    {
        this.startActivity(getIntent(DummyScreen.class));
    }

    @Override
    public void openWelcomeScreen()
    {
        this.startActivity(getIntent(WelcomeScreen.class));
    }

    @Override
    public void openLoginScreen()
    {
        this.startActivity(getIntent(LoginScreen.class));
    }

    @Override
    public void openEventsScreen()
    {
        this.startActivity(getIntent(EventsScreen.class));
    }

    @Override
    public void openEventDetailScreen(Event event)
    {
        Intent intent = getIntent(EventDetailsScreen.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EventsPresenter.EVENT_CHOSEN, event);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    private void startActivity(Intent intent)
    {
        if (context instanceof Activity == false)
        {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private Intent getIntent(Class<? extends Activity> type)
    {
        return new Intent(context, type);
    }
}
