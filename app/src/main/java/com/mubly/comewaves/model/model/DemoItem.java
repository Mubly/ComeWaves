package com.mubly.comewaves.model.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.mubly.comewaves.view.costomview.AsymmetricItem;


public class DemoItem implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;
    private String img_url;
    private int cate_id;

    public DemoItem() {
        this(1, 1, 0);
    }

    public DemoItem(int columnSpan, int rowSpan, int position) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
    }

    public DemoItem(int columnSpan, int rowSpan, int position, String img_url) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
        this.img_url = img_url;
    }

    public DemoItem(int columnSpan, int rowSpan, int position, String img_url, int cate_id) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
        this.img_url = img_url;
        this.cate_id = cate_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public DemoItem(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
    }

    /* Parcelable interface implementation */
    public static final Parcelable.Creator<DemoItem> CREATOR = new Parcelable.Creator<DemoItem>() {
        @Override
        public DemoItem createFromParcel(@NonNull Parcel in) {
            return new DemoItem(in);
        }

        @Override
        @NonNull
        public DemoItem[] newArray(int size) {
            return new DemoItem[size];
        }
    };
}
