package com.pizza.android.swizza.networking;

/**
 * Created by Anuja on 9/7/17.
 */

public class NetworkError {
    public NetworkError(Throwable e) {
        e.printStackTrace();
    }

    public String getAppErrorMessage() {
        return "Error";
    }
}
