package com.wallmart.busroute.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import com.wallmart.busroute.database.ProviderUri;
import com.wallmart.busroute.database.SqlConstants;
import com.wallmart.busroute.database.column.BusRouteTableColumn;
import com.wallmart.busroute.network.pojo.RouteInfo;
import com.wallmart.busroute.network.pojo.StopsInfo;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 * Database operation handler
 * This layer helps to communicate with data base layer
 */

public class DatabaseManager {
    private static DatabaseManager mDatabaseManager;

    private StringBuilder queryBuilder;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if(mDatabaseManager == null) {
            mDatabaseManager = new DatabaseManager();
        }
        return mDatabaseManager;
    }

    public void modifyBusRoute(RouteInfo article, Context context) {
        if(updateBusRoutes(article, context) == 0) {
            insertBusRoutes(article,context);
        }
    }

    private int updateBusRoutes(RouteInfo busRoutes, Context context) {
        ContentValues values = setValues(busRoutes);
        String where = BusRouteTableColumn.ROUTE_ID + SqlConstants.EQUALS_ARG;
        return context.getContentResolver().update(ProviderUri.URI_BUS_ROUTES,
                values, where , new String[] {String.valueOf(busRoutes.getId())});
    }

    public void insertBusRoutes(RouteInfo busRoutes, Context context) {
        ContentValues values = setValues(busRoutes);
        context.getContentResolver().insert(ProviderUri.URI_BUS_ROUTES, values);
    }

    private ContentValues setValues(RouteInfo busRoutes) {
        ContentValues values = new ContentValues();
        values.put(BusRouteTableColumn.ROUTE_ID, busRoutes.getId());
        values.put(BusRouteTableColumn.NAME, busRoutes.getName());
        values.put(BusRouteTableColumn.DESCRIPTION, busRoutes.getDescription());
        values.put(BusRouteTableColumn.ACCESSIBLE, busRoutes.getAccessible());
        values.put(BusRouteTableColumn.IMAGE, busRoutes.getImage());
        List<StopsInfo> stopsName = busRoutes.getStops();
        StringBuilder stopName = new StringBuilder();
        if (stopsName != null && stopsName.size() > 0) {
            for (int i = 0; i < stopsName.size(); i++) {
                stopName.append(stopsName.get(i).getName());
                if (i < stopsName.size() - 1) {
                    stopName.append(",");
                }
            }
        }
        values.put(BusRouteTableColumn.STOP_NAME, stopName.toString());

        return values;
    }

    public Cursor fetchBusRouteInfo(final Context context, final String routeId) {
        String selection = BusRouteTableColumn.ROUTE_ID + SqlConstants.EQUALS_ARG;
        String[] arguments = new String[]{routeId};
        return context.getContentResolver().query(ProviderUri.URI_BUS_ROUTES, null,
                selection, arguments, null);

    }


}
