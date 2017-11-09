package com.wallmart.busroute.network;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public interface OnHttpResponseListener {
    void onSuccess(final int requestCode, final String message);

    void onError(final int statusCode, final String message);

    void onError(final String resStr);

    void onRequestCancelled(final int requestCode);
}
