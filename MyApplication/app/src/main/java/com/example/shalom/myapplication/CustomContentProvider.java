package com.example.shalom.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Shalom on 11/26/2016.
 */

public class CustomContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.shalom.myapplication";
    // NOT COMPLETE!!! NEED TO FIX THE URI
    static final String URL = "content://" + PROVIDER_NAME + "/";
    static final Uri CONTENT_URI = Uri.parse(URL);

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return null;
    }
    @Override
    public String getType(Uri uri)
    {
        return null;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return 0;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        return null;
    }
    @Override
    public boolean onCreate()
    {
        return false;
    }
}
