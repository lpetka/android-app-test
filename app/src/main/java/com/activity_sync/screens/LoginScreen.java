package com.activity_sync.screens;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.Bind;
import com.activity_sync.App;
import com.activity_sync.R;
import com.activity_sync.presentation.presenters.IPresenter;
import com.activity_sync.presentation.presenters.LoginPresenter;
import com.activity_sync.presentation.repository.IRestService;
import com.activity_sync.presentation.services.INavigator;
import com.activity_sync.presentation.views.ILoginView;
import com.activity_sync.presentation.widgets.ISecureStorage;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.ViewObservable;

import javax.inject.Inject;

public class LoginScreen extends Screen implements ILoginView
{
    @Inject
    IRestService restService;

    @Inject
    INavigator navigator;

    @Inject
    ISecureStorage secureStorage;

    @Bind(R.id.email)
    AutoCompleteTextView mEmailView;

    @Bind(R.id.password)
    EditText mPasswordView;

    @Bind(R.id.login_progress)
    View mProgressView;

    @Bind(R.id.login_form)
    View mLoginFormView;

    @Bind(R.id.email_sign_in_button)
    Button mEmailSignInButton;

    public LoginScreen() {
        super(R.layout.login_screen);
    }

    @Override
    protected IPresenter createPresenter(Screen screen, Bundle savedInstanceState) {
        return new LoginPresenter(AndroidSchedulers.mainThread(), this, navigator, restService, secureStorage);
    }

    @Override
    public Observable signInClick()
    {
        return ViewObservable.clicks(mEmailSignInButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        App.component(this).inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void showError(String string)
    {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showGeneralLoginError()
    {
        this.showError(getString(R.string.err_login));
    }

    @Override
    public String getUsername()
    {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPassword()
    {
        return mPasswordView.getText().toString();
    }

    @Override
    public void hideSoftKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}