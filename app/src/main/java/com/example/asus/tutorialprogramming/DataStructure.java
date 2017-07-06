package com.example.asus.tutorialprogramming;

import java.io.Serializable;

/**
 * Created by Asus on 6/30/2017.
 */

public class DataStructure implements Serializable {
    private boolean mcheckBox = false;
    private String mTitle = "";
    private String mData = "";
    private String mTime = "";

    public DataStructure() {
    }

    public DataStructure(String mTitle, String mData, String mTime) {
        this.mTitle = mTitle;
        this.mData = mData;
        this.mTime = mTime;
    }

    public DataStructure(boolean mcheckBox, String mTitle, String mData, String mTime) {
        this.setmcheckBox(mcheckBox);
        this.mTitle = mTitle;
        this.mData = mData;
        this.mTime = mTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public boolean getmcheckBox() {
        return mcheckBox;
    }

    public void setmcheckBox(boolean mcheckBox) {
        this.mcheckBox = mcheckBox;
    }
}
