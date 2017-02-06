package com.example.shalom.myapplication.model.datasource;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.net.Uri;
import android.text.TextUtils;

import com.example.shalom.myapplication.model.backend.FactoryDataSource;
import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.entities.Activity;

import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by Shalom on 11/26/2016.
 */

public class CustomContentProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.example.shalom.myapplication";

    public static IDataSource DB_Manager = FactoryDataSource.getDataBase();

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //static constructor for initializing static attributes
    static {
        sUriMatcher.addURI(PROVIDER_NAME, "businesses", 1);
        sUriMatcher.addURI(PROVIDER_NAME, "activities", 2);
        sUriMatcher.addURI(PROVIDER_NAME, "users", 3);
    }

    /**
     * This method is in charge of returning part or all the database from a query
     * @param uri the uri of the part of the database being queried
     * @param projection the projection of the query
     * @param selection the selection of the query
     * @param selectionArgs not used in this application
     * @param sortOrder the order the columns are sorted in
     * @return a cursor containing the thing that was queried
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            if(sUriMatcher.match(uri) == 1)
            {
                Cursor cursor = DB_Manager.getBusinesses();
                return cursor;
            }
            else if(sUriMatcher.match(uri) == 2) {
                return DB_Manager.getActivities();
            }
            else if(sUriMatcher.match(uri) == 3){
                return DB_Manager.getUsers();
            }
            throw new IllegalArgumentException("Unsupported URI: " + uri);
            //Delete Columns without the projection and selection
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException("Cannot connect to the server" + e.getMessage());
        }
    }

    /**
     * not implemented
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * update - NOT IMPLEMENTED
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * delete from the database - NOT IMPLEMENTED
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * This method is in charge od inserting something into the database
     * @param uri the uri (path) where we want to insert it (for example content://com.example.ezra.users
     * @param values the thing being inserted
     * @return the uri of the inserted item
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            switch (uri.getPath().substring(1)) {
                case "activities":
                    DB_Manager.addActivity(values);
                    return null;
                case "businesses":
                    DB_Manager.addBusiness(values);
                    return null;
                case "users":
                    DB_Manager.addUser(values);
                    return null;
                default:
                    throw new IllegalArgumentException("This Content Provider doesn't support this type of thing");
            }
        } catch (IOException e) {
            throw new SQLException("There was a problem with the server" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * constructor for the content provider
     * @return
     */
    @Override
    public boolean onCreate() {
        return true;
    }
}
