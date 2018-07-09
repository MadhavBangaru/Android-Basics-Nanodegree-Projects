package com.example.madhavbangaru.tourguide;

/**
 * Created by Madhav Bangaru on 03-07-2018.
 */

public class Location {
    private String mLocationName;
    private String mTimings;
    private String mLocationContact;
    private int mImageResourceId;

    public Location(String locationName, String timing,String locationContact , int imageResourceId) {
        this.mLocationName = locationName;
        this.mTimings = timing;
        this.mLocationContact = locationContact;
        this.mImageResourceId = imageResourceId;
    }
    public String getLocationName() {
        return mLocationName;
    }

    public String getTimings() {
        return mTimings;
    }

    public String getLocationContact() {
        return mLocationContact;
    }

    public int getImageResourceId() { return mImageResourceId; }

}
