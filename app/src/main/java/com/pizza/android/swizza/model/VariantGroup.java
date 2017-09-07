package com.pizza.android.swizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anuja on 9/7/17.
 */

public class VariantGroup implements Parcelable {
    @SerializedName("group_id")
    private String groupId;

    @SerializedName("name")
    private String name;

    @SerializedName("variations")
    private ArrayList<Variation> variations = null;

    protected VariantGroup(Parcel in) {
        groupId = in.readString();
        name = in.readString();
        variations = in.createTypedArrayList(Variation.CREATOR);
    }

    public static final Creator<VariantGroup> CREATOR = new Creator<VariantGroup>() {
        @Override
        public VariantGroup createFromParcel(Parcel in) {
            return new VariantGroup(in);
        }

        @Override
        public VariantGroup[] newArray(int size) {
            return new VariantGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(groupId);
        parcel.writeString(name);
        parcel.writeTypedList(variations);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Variation> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<Variation> variations) {
        this.variations = variations;
    }
}

