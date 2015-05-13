package com.frankzhu.filtermenu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/13 23:16
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/13      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FilterModel implements Parcelable {
    public String id;
    public String name;
    public String imageUrl;
    public int imageDrawableId;
    public long count;

    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.imageDrawableId);
        dest.writeLong(this.count);
    }

    public FilterModel() {
    }

    private FilterModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.imageDrawableId = in.readInt();
        this.count = in.readLong();
    }

    public static final Parcelable.Creator<FilterModel> CREATOR = new Parcelable.Creator<FilterModel>() {
        public FilterModel createFromParcel(Parcel source) {
            return new FilterModel(source);
        }

        public FilterModel[] newArray(int size) {
            return new FilterModel[size];
        }
    };
}
