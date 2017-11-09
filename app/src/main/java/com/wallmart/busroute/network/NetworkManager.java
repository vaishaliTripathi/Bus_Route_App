package com.wallmart.busroute.network;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class NetworkManager {
    private static final String TAG = NetworkManager.class.getName();
    protected static NetworkManager networkManager;

    public NetworkManager() {
    }

    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }

    public BusRouteEndpointInterface getApiService(Context context) {

        String baseUrl = HttpRequestConstants.BASE_URL;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
        return retrofit.create(BusRouteEndpointInterface.class);
    }
}
