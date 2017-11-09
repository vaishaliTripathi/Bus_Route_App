package com.wallmart.busroute.database.table;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.wallmart.busroute.database.SqlConstants;
import com.wallmart.busroute.database.column.BusRouteTableColumn;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class TableBusRoutes {
    public static final String NAME = "busRoutes";

    private static final String DROP_OLD_TABLE = SqlConstants.DROP_TABLE + NAME;

    public static void create(final SQLiteDatabase db) {

        final String tableBuilder = SqlConstants.CREATE_TABLE + NAME + SqlConstants.PARANTHESIS_OPEN
                + BaseColumns._ID + SqlConstants.DATA_INT_PK
                + BusRouteTableColumn.ROUTE_ID + SqlConstants.DATA_TEXT
                + BusRouteTableColumn.NAME + SqlConstants.DATA_TEXT
                + BusRouteTableColumn.DESCRIPTION + SqlConstants.DATA_TEXT
                + BusRouteTableColumn.ACCESSIBLE + SqlConstants.DATA_TEXT
                + BusRouteTableColumn.STOP_NAME + SqlConstants.DATA_TEXT
                + BusRouteTableColumn.IMAGE + SqlConstants.DATA_TEXT_END
                + SqlConstants.SEMI_COLON;
        db.execSQL(tableBuilder);
    }

    public static void upgrade(final SQLiteDatabase database, final int oldVersion, final int
            newVersion) {
        database.execSQL(DROP_OLD_TABLE);
        create(database);
    }
}
