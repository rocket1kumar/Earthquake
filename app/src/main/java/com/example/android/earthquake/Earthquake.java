package com.example.android.earthquake;

/**
 * Created by Raider on 29-03-2017.
 */

public class Earthquake {
    // Magnitude of the earthquake
    private double mMagnitude;
    // location of the earthquake
    private String mLocation;
    /** Time of the earthquake */
    private long mTimeInMilliseconds;
    /** Website URL of the earthquake */
    private String mUrl;

// Constructor to intialize the values of the magnitude,location,date
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url){
        mMagnitude=magnitude;
        mLocation=location;
        mTimeInMilliseconds=timeInMilliseconds;
        mUrl=url;

    }
// method to return the values of magnitude
    public double getMagnitude(){ return mMagnitude;}
    // method to return the values of magnitude
    public String getLocation(){ return mLocation;}
    // method to return the values of magnitude
    public long getTimeInMilliseconds(){ return mTimeInMilliseconds;}
    //url is the website URL to find more details about the earthquake
    public String getUrl(){ return mUrl;}


}
