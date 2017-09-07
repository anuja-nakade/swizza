package com.pizza.android.swizza.home;

import com.pizza.android.swizza.model.PizzaResponse;
import com.pizza.android.swizza.networking.NetworkError;
import com.pizza.android.swizza.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Anuja on 9/7/17.
 */

public class PizzaPresenter {
    private final Service service;
    private final PizzaView view;
    private CompositeSubscription subscriptions;

    public PizzaPresenter(Service service, PizzaView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getPizzaList() {

        Subscription subscription = service.getPizzaDetailsList(new Service.GetVariationExcludeListCallback() {
            @Override
            public void onSuccess(PizzaResponse pizzaResponse) {
                view.getPizzaResponseSuccess(pizzaResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getAppErrorMessage());
            }


        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}
