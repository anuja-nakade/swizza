package com.pizza.android.swizza.networking;

import com.pizza.android.swizza.model.PizzaResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Anuja on 9/7/17.
 */

public class Service {
    private final API apiService;

    public Service(API apiService) {
        this.apiService = apiService;
    }

    public Subscription getPizzaDetailsList(final GetVariationExcludeListCallback callback) {
        return apiService.getVariantsExcludeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends PizzaResponse>>() {
                    @Override
                    public Observable<? extends PizzaResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<PizzaResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(PizzaResponse pizzaResponse) {
                        callback.onSuccess(pizzaResponse);

                    }
                });

    }

    public interface GetVariationExcludeListCallback {
        void onSuccess(PizzaResponse pizzaResponse);

        void onError(NetworkError networkError);
    }
}

