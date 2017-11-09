package com.wallmart.busroute.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wallmart.busroute.database.table.TableBusRoutes;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 * SQLite Open Helper for the application
 */

public final class DBOpenHelper extends SQLiteOpenHelper {

    private static DBOpenHelper mInstance = null;

    private static final String DB_NAME = "app_database.db";

    public static final int DB_VERSION = 1;

    public static DBOpenHelper getInstance(final Context ctx) {
        /**
         * use the application context as suggested by CommonsWare. this will
         * ensure that you don't accidentally leak an Activity's context (see this
         * article for more information:
         * http://android-developers.blogspot.nl/2009
         * /01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DBOpenHelper(ctx.getApplicationContext(), DB_NAME, null, DB_VERSION);
        }
        return mInstance;
    }

    public DBOpenHelper(final Context context, final String name, final SQLiteDatabase.CursorFactory factory, final int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(final SQLiteDatabase database) {
        TableBusRoutes.create(database);

    }

    @Override
    public void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {
        TableBusRoutes.upgrade(database, oldVersion, newVersion);
    }

}
