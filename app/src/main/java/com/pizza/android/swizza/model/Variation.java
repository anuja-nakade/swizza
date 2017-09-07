package com.pizza.android.swizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anuja on 9/7/17.
 */

public class Variation implements Parcelable {
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Integer price;

    @SerializedName("default")
    private Integer _default;

    @SerializedName("id")
    private String id;

    @SerializedName("inStock")
    private Integer inStock;

    @SerializedName("isVeg")
    private Integer isVeg;

    protected Variation(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Variation> CREATOR = new Creator<Variation>() {
        @Override
        public Variation createFromParcel(Parcel in) {
            return new Variation(in);
        }

        @Override
        public Variation[] newArray(int size) {
            return new Variation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
