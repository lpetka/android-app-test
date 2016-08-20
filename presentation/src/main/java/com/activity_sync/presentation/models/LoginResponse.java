package com.activity_sync.presentation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginResponse implements Serializable
{
    @SerializedName("token")
    private String token;

    @SerializedName("non_field_errors")
    private List<String> nonFieldErrors;

    @SerializedName("password")
    private List<String> password;

    @SerializedName("username")
    private List<String> username;

    public LoginResponse(String token, List<String> nonFieldErrors, List<String> password, List<String> username)
    {
        this.token = token;
        this.nonFieldErrors = nonFieldErrors;
        this.password = password;
        this.username = username;
    }

    public LoginResponse()
    {

    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public List<String> getNonFieldErrors()
    {
        return nonFieldErrors;
    }

    public void setNonFieldErrors(List<String> nonFieldErrors)
    {
        this.nonFieldErrors = nonFieldErrors;
    }

    public List<String> getPassword()
    {
        return password;
    }

    public void setPassword(List<String> password)
    {
        this.password = password;
    }

    public List<String> getUsername()
    {
        return username;
    }

    public void setUsername(List<String> username)
    {
        this.username = username;
    }
}
