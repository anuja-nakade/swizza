package com.pizza.android.swizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pizza.android.swizza.component.DaggerPizzaComponent;
import com.pizza.android.swizza.component.PizzaComponent;
import com.pizza.android.swizza.networking.NetworkModule;

/**
 * Created by Anuja on 9/7/17.
 */

public class BaseApp  extends AppCompatActivity {
    PizzaComponent mPizzaComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPizzaComponent = DaggerPizzaComponent.builder().networkModule(new NetworkModule()).build();

    }

    public PizzaComponent getPizzaComponent() {
        return mPizzaComponent;
    }
}
