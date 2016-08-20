package com.activity_sync.presentation.presenters;

import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.views.ILoginView;
import com.activity_sync.presentation.widgets.ISecureStorage;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class LoginPresenter extends Presenter<ILoginView>
{
    private final Scheduler uiThread;
    private final INavigator navigator;
    private final IRestService restService;
    private final ISecureStorage secureStorage;

    public LoginPresenter(Scheduler uiThread, ILoginView view, INavigator navigator, IRestService restService, ISecureStorage secureStorage)
    {
        super(view);
        this.uiThread = uiThread;
        this.restService = restService;
        this.navigator = navigator;
        this.secureStorage = secureStorage;
    }

    @Override
    public void start()
    {
        super.start();
        this.secureStorage.clear();

        this.subscriptions.add(this.view.signInClick()
                .subscribe(o -> {
                            this.view.hideSoftKeyboard();

                            this.restService.login(this.view.getUsername(), this.view.getPassword())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(this.uiThread)
                                    .subscribe(response ->
                                    {
                                        if (response.getToken() == null)
                                        {
                                            if (response.getNonFieldErrors().size() > 0)
                                            {
                                                this.view.showError(response.getNonFieldErrors().get(0));
                                            }
                                            else if (response.getPassword().size() > 0)
                                            {
                                                this.view.showError(response.getPassword().get(0));
                                            }
                                            else if (response.getUsername().size() > 0)
                                            {
                                                this.view.showError(response.getUsername().get(0));
                                            }
                                        }
                                        else
                                        {
                                            this.secureStorage.saveToken(response.getToken());
                                            this.navigator.openEventsScreen();
                                        }
                                    }, throwable ->
                                    {
                                        Timber.e(throwable.getMessage());
                                        this.view.showError("Wrong password or email. Try again");
                                    });
                        }
                ));
    }
}