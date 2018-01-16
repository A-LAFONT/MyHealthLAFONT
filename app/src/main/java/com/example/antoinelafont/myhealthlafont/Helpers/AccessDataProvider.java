package com.example.antoinelafont.myhealthlafont.Helpers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Antoine LAFONT on 17/11/2017.
 */

public class AccessDataProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.example.myhealth.provider";
    public static final String URL = "content://" + PROVIDER_NAME;
    //public static Uri CONTENT_URI = Uri.parse(URL);

    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public SQLiteDatabase db;

    static {
        sUriMatcher.addURI(PROVIDER_NAME,"Personnes", 1);
        sUriMatcher.addURI(PROVIDER_NAME,"Personnes/#", 2);
    }

    @Override
    public boolean onCreate() {
        try {
            MyDbHelper _db = new MyDbHelper(getContext(), "AccessDataProviderDB", null, 1);
            db = _db.getWritableDatabase();

            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (sUriMatcher.match(uri)) {
            case 1: break;
            case 2: break;
            default : break;
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 1: return "vnd.android.cursor.dir/vnd.com.example.provider.Personnes";
            case 2: return "vnd.android.cursor.item/vnd.com.example.provider.Personnes";
            default : return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
