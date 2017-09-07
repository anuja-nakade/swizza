package com.pizza.android.swizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anuja on 9/7/17.
 */

public class ExcludeList implements Parcelable {
    @SerializedName("group_id")
    private String groupId;

    @SerializedName("variation_id")
    private String variationId;

    protected ExcludeList(Parcel in) {
        groupId = in.readString();
        variationId = in.readString();
    }

    public static final Creator<ExcludeList> CREATOR = new Creator<ExcludeList>() {
        @Override
        public ExcludeList createFromParcel(Parcel in) {
            return new ExcludeList(in);
        }

        @Override
        public ExcludeList[] newArray(int size) {
            return new ExcludeList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(groupId);
        parcel.writeString(variationId);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }
}
