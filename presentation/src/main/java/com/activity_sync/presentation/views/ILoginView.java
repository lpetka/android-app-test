package com.activity_sync.presentation.views;

import rx.Observable;

public interface ILoginView
{
    Observable signInClick();

    void showError(String string);

    void showGeneralLoginError();

    String getUsername();

    String getPassword();

    void hideSoftKeyboard();
}