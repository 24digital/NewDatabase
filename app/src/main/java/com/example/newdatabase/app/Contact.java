package com.example.newdatabase.app;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable{
    private long number;
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", number=" + number
                ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(new String[]{String.valueOf(this.id), String.valueOf(this.name),String.valueOf(this.number) });
    }
}