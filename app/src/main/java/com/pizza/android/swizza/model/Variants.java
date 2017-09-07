package com.pizza.android.swizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anuja on 9/7/17.
 */

public class Variants implements Parcelable {
    @SerializedName("variant_groups")
    private ArrayList<VariantGroup> variantGroups = null;

    @SerializedName("exclude_list")
    private ArrayList<ArrayList<ExcludeList>> excludeList = null;

    protected Variants(Parcel in) {
    }

    public static final Creator<Variants> CREATOR = new Creator<Variants>() {
        @Override
        public Variants createFromParcel(Parcel in) {
            return new Variants(in);
        }

        @Override
        public Variants[] newArray(int size) {
            return new Variants[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public ArrayList<VariantGroup> getVariantGroups() {
        return variantGroups;
    }

    public void setVariantGroups(ArrayList<VariantGroup> variantGroups) {
        this.variantGroups = variantGroups;
    }

    public ArrayList<ArrayList<ExcludeList>> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(ArrayList<ArrayList<ExcludeList>> excludeList) {
        this.excludeList = excludeList;
    }
}
