package com.activity_sync.tests;

import com.activity_sync.presentation.presenters.WelcomePresenter;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.views.IWelcomeView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@RunWith(MockitoJUnitRunner.class)
public class WelcomePresenterTests
{
    @Mock
    IWelcomeView view;

    @Mock
    INavigator navigator;

    PublishSubject openDummyEvent = PublishSubject.create();
    PublishSubject showMessageEvent = PublishSubject.create();

    @Before
    public void setup()
    {
        Mockito.when(view.loginButtonClick()).thenReturn(openDummyEvent);
        Mockito.when(view.registerButtonClick()).thenReturn(showMessageEvent);
    }

    @Test
    public void welcomePresenter_clickLoginBtn_openDummyScreen()
    {
        WelcomePresenter welcomePresenter = createPresenter();
        welcomePresenter.start();

        openDummyEvent.onNext(this);

        Mockito.verify(navigator).openLoginScreen();
    }

    @Test
    public void welcomePresenter_clickRegisterBtn_displayMessage()
    {
        WelcomePresenter welcomePresenter = createPresenter();
        welcomePresenter.start();

        showMessageEvent.onNext(this);

        Mockito.verify(navigator).openDummyScreen();
    }

    private WelcomePresenter createPresenter()
    {
        return new WelcomePresenter(Schedulers.immediate(), view, navigator);
    }
}