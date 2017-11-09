package com.wallmart.busroute.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class AppUtil {
    /**
     * Get Network status
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * @return
     */
    public static  boolean isConnectingToInternet(Context context) {
        return isNetworkAvailable(context);
    }
}
