package com.example.testret.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Temperature {

    @SerializedName("Date")
    private String Date;
    @SerializedName("EpochDate")
    private String EpochDate;
    @SerializedName("MobileLink")
    private String MobileLink;
    @SerializedName("Link")
    private String Link;

    public Temperature() { }

    public Temperature(String date, String epochDate, String mobileLink, String link) {
        Date = date;
        EpochDate = epochDate;
        MobileLink = mobileLink;
        Link = link;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getEpochDate() {
        return EpochDate;
    }

    public void setEpochDate(String epochDate) {
        EpochDate = epochDate;
    }

    public String getMobileLink() {
        return MobileLink;
    }

    public void setMobileLink(String mobileLink) {
        MobileLink = mobileLink;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
