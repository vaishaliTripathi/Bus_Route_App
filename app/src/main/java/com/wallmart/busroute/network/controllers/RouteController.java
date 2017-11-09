package com.wallmart.busroute.network.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

import com.wallmart.busroute.R;
import com.wallmart.busroute.database.table.DatabaseManager;
import com.wallmart.busroute.network.HttpRequestConstants;
import com.wallmart.busroute.network.NetworkManager;
import com.wallmart.busroute.network.OnHttpResponseListener;
import com.wallmart.busroute.network.RetrofitException;
import com.wallmart.busroute.network.pojo.ListOfRoutes;
import com.wallmart.busroute.network.pojo.RouteInfo;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class RouteController extends NetworkManager {
    private static final String TAG = RouteController.class.getSimpleName();
    private static RouteController routeController;

    public RouteController() {
        super();
    }

    public static RouteController getInstance() {
        if (routeController == null) {
            routeController = new RouteController();
        }
        return routeController;
    }

    public void getListOfRoutes(final Context context, final OnHttpResponseListener onHttpResponseListener) {

        final Observable<ListOfRoutes> responseObservable = getApiService(context).getBusRouteList();
        final ProgressDialog progressDialog = new ProgressDialog(context);

        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Subscriber<ListOfRoutes>() {
                    @Override
                    public void onStart() {
                        progressDialog.setMessage(context.getString(R.string.please_wait));
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                    @Override
                    public void onCompleted() {
                        progressDialog.cancel();
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        Log.d(TAG, "ERROR: " + throwable.getMessage());
                        throwable.printStackTrace();
                        progressDialog.cancel();

                        if (throwable instanceof RetrofitException) {
                            RetrofitException error = (RetrofitException) throwable;
                            if (error.getKind() == RetrofitException.Kind.NETWORK) {
                                onHttpResponseListener.onError(-1, context.getString(R.string.internal_server_error));
                            } else if (error.getResponse().code() == 401 || error.getResponse().code() == 404) {
                                onHttpResponseListener.onError(error.getResponse().code(),
                                        error.getResponse().message());
                            } else {
                                try {
                                    ListOfRoutes response = (error.getErrorBodyAs((ListOfRoutes.class)));
                                    onHttpResponseListener.onError(HttpRequestConstants.BUS_ROUTE_LIST_REQ_CODE,
                                            context.getString(R.string.error));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            onHttpResponseListener.onError(-1, context.getString(R.string.internal_server_error));
                        }
                    }

                    @Override
                    public void onNext(final ListOfRoutes response) {
                        if (response != null) {
                            if(response != null && response.routesList.size() > 0) {
                                for(int i=0;i< response.routesList.size();i++) {
                                    RouteInfo routeInfo = response.routesList.get(i);
                                    DatabaseManager.getInstance().modifyBusRoute(routeInfo, context);
                                }
                            }
                            onHttpResponseListener.onSuccess(HttpRequestConstants.BUS_ROUTE_LIST_REQ_CODE,
                                    context.getString(R.string.success));
                        } else {
                            onHttpResponseListener.onError(HttpRequestConstants.BUS_ROUTE_LIST_REQ_CODE,
                                    context.getString(R.string.error));
                        }
                    }
                });

    }
}
