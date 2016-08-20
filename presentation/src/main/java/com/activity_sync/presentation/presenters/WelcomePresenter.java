package com.activity_sync.presentation.presenters;


import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.views.IWelcomeView;
import rx.Scheduler;

public class WelcomePresenter extends Presenter<IWelcomeView>
{
    private Scheduler uiThread;
    private final INavigator navigator;

    public WelcomePresenter(Scheduler uiThread, IWelcomeView view, INavigator navigator)
    {
        super(view);
        this.uiThread = uiThread;
        this.navigator = navigator;
    }

    @Override
    public void start()
    {
        super.start();

        this.subscriptions.add(view.loginButtonClick()
                .observeOn(uiThread)
                .subscribe(o -> {
                    navigator.openLoginScreen();
                })
        );

        this.subscriptions.add(view.registerButtonClick()
                .observeOn(uiThread)
                .subscribe(o -> {
                    navigator.openDummyScreen();
                })
        );
    }
}
