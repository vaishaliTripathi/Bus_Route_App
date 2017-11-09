package com.wallmart.busroute.Utils;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class ConnectionState {

    public final static int CONNECTED           =   1;
    public final static int NOT_CONNECTED       =   2;
    private int connectionState;

    public int getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(final int connectionState) {
        this.connectionState = connectionState;
    }
}
