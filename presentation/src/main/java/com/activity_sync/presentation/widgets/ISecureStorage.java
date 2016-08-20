package com.activity_sync.presentation.widgets;

public interface ISecureStorage
{
    void save(String key, String value);

    String retrieveString(String key, String defaultValue);

    void saveToken(String value);

    String retrieveToken();

    void clear();
}