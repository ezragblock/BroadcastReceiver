package com.example.shalom.myapplication;

import android.content.ContentProvider;
import android.net.Uri;

/**
 * Created by Shalom on 11/26/2016.
 */

public class CustomContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.shalom.myapplication";
    // NOT COMPLETE!!! NEED TO FIX THE URI
    static final String URL = "content://" + PROVIDER_NAME + "/";
    static final Uri CONTENT_URI = Uri.parse(URL);


}