package com.activity_sync.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.activity_sync.presentation.widgets.ISecureStorage;

public class SecureStorage implements ISecureStorage
{
    public static final String PREFERENCES_NAME = "com.bucket_counter";
    private static final String TOKEN = "token";
    private final Context context;

    public SecureStorage(Context context)
    {
        this.context = context;
    }

    @Override
    public void save(String key, String value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value.toString());
        editor.commit();
    }

    @Override
    public String retrieveString(String key, String defaultValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public void saveToken(String value)
    {
        save(TOKEN, value);
    }

    @Override
    public String retrieveToken()
    {
        return retrieveString(TOKEN, null);
    }

    @Override
    public void clear()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }
}
