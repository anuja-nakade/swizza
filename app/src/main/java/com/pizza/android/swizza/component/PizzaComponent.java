package com.pizza.android.swizza.component;

import com.pizza.android.swizza.home.PizzaActivity;
import com.pizza.android.swizza.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Anuja on 9/7/17.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface PizzaComponent {
    void inject(PizzaActivity pizzaActivity);

}
