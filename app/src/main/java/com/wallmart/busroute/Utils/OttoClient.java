package com.wallmart.busroute.Utils;

import com.squareup.otto.Bus;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class OttoClient {

    private static OttoClient client;
    private MainThreadBus mainThreadBus;

    private OttoClient() {
        mainThreadBus = new MainThreadBus();
    }

    public static OttoClient getInstance() {
        if (client == null) {

            synchronized (OttoClient.class) {
                if (client == null) {
                    client = new OttoClient();
                }
            }
        }
        return client;
    }

    public Bus getOttoBus() {
        return mainThreadBus;
    }

}

