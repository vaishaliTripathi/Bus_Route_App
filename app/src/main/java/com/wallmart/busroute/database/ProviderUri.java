package com.wallmart.busroute.database;

import android.net.Uri;

import com.wallmart.busroute.database.table.TableBusRoutes;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class ProviderUri {
    public static final Uri URI_BUS_ROUTES = Uri.parse(DatabaseProviderConstants.URI_PREFIX + TableBusRoutes.NAME);
}
