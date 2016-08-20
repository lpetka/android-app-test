package com.activity_sync.screens;

import android.os.Bundle;
import android.widget.Button;
import butterknife.Bind;
import com.activity_sync.R;
import com.activity_sync.presentation.presenters.IPresenter;
import com.activity_sync.presentation.presenters.WelcomePresenter;
import com.activity_sync.presentation.views.IWelcomeView;
import com.activity_sync.services.Navigator;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.ViewObservable;

public class WelcomeScreen extends Screen implements IWelcomeView
{
    @Bind(R.id.login_btn)
    Button loginButton;

    @Bind(R.id.register_btn)
    Button registerButton;

    public WelcomeScreen()
    {
        super(R.layout.welcome_screen);
    }

    @Override
    protected IPresenter createPresenter(Screen screen, Bundle savedInstanceState)
    {
        return new WelcomePresenter(AndroidSchedulers.mainThread(), this, new Navigator(this));
    }

    @Override
    public Observable loginButtonClick()
    {
        return ViewObservable.clicks(loginButton);
    }

    @Override
    public Observable registerButtonClick()
    {
        return ViewObservable.clicks(registerButton);
    }
}