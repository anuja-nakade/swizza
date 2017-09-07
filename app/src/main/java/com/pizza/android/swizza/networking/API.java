package com.pizza.android.swizza.networking;

import com.pizza.android.swizza.model.PizzaResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Anuja on 9/7/17.
 */

public interface API {
    @GET("bins/3b0u2")
    Observable<PizzaResponse> getVariantsExcludeList();

}
