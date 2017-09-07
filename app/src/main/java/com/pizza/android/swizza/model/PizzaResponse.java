package com.pizza.android.swizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anuja on 9/7/17.
 */

public class PizzaResponse implements Parcelable {
    @SerializedName("variants")
    private Variants variants;

    protected PizzaResponse(Parcel in) {
        variants = in.readParcelable(Variants.class.getClassLoader());
    }

    public static final Creator<PizzaResponse> CREATOR = new Creator<PizzaResponse>() {
        @Override
        public PizzaResponse createFromParcel(Parcel in) {
            return new PizzaResponse(in);
        }

        @Override
        public PizzaResponse[] newArray(int size) {
            return new PizzaResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(variants, i);
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }
}
