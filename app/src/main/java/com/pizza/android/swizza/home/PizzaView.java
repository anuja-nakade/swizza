package com.pizza.android.swizza.home;

import com.pizza.android.swizza.model.PizzaResponse;

/**
 * Created by Anuja on 9/7/17.
 */

public interface PizzaView {

    void onFailure(String appErrorMessage);

    void getPizzaResponseSuccess(PizzaResponse pizzaResponse);

}

