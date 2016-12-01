package com.example.shalom.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.shalom.myapplication.model.backend.FactoryDataSource;
import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.entities.Activity;

/**
 * Created by Shalom on 11/26/2016.
 */

public class CustomContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.shalom.myapplication";

    public static IDataSource DB_Manager = FactoryDataSource.getDataBase();

    static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //static constructor for initializing static attributes
    static
    {
        sUriMatcher.addURI(PROVIDER_NAME,"businesses",1);
        sUriMatcher.addURI(PROVIDER_NAME,"activities",2);
        sUriMatcher.addURI(PROVIDER_NAME,"users",3);
    }

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
        String table = uri.getAuthority();
        switch(uri.getPath())
        {
            case "activities":
                Activity activity = new Activity();
                break;
            case "buisnesses":
                break;
            case "users":
                break;
            default:

        }
        return null;
    }
    @Override
    public boolean onCreate()
    {
        return false;
    }
}
