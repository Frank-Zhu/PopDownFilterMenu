package com.frankzhu.filtermenu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;

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
public class DefaultFilterModel extends BaseModel implements Parcelable {
    public String id;
    public String name;
    public String imageUrl;
    public int imageDrawableId;
    public long count;
    public ArrayList<DefaultFilterModel> mSeconds;

    @Override
    public String getItemName() {
        return name;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public DefaultFilterModel() {
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
        dest.writeList(this.mSeconds);
    }

    private DefaultFilterModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.imageDrawableId = in.readInt();
        this.count = in.readLong();
        this.mSeconds = (ArrayList<DefaultFilterModel>) in.readArrayList(DefaultFilterModel.class.getClassLoader());
    }

    public static final Creator<DefaultFilterModel> CREATOR = new Creator<DefaultFilterModel>() {
        public DefaultFilterModel createFromParcel(Parcel source) {
            return new DefaultFilterModel(source);
        }

        public DefaultFilterModel[] newArray(int size) {
            return new DefaultFilterModel[size];
        }
    };
}
