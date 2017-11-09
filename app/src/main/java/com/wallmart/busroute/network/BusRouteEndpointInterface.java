package com.wallmart.busroute.network;

import com.wallmart.busroute.network.pojo.ListOfRoutes;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public interface BusRouteEndpointInterface {

    @POST(HttpRequestConstants.BUS_ROUTE_LIST)
    Observable<ListOfRoutes> getBusRouteList();
}
