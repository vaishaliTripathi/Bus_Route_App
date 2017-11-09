package com.wallmart.busroute.Utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class MainThreadBus extends Bus {

    private final Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    MainThreadBus.super.post(event);
                }
            });
        }
    }
}
