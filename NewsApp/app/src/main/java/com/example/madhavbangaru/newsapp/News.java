package com.example.madhavbangaru.newsapp;

/**
 * Created by Madhav Bangaru on 06-07-2018.
 */

public class News {
    private String mTitle ;
    private String mCategory;
    private String mDateTime;
    private String mUrl;
    private String mAuthor;


    public News(String mTitle, String mCategory,String mAuthor, String mDateTime, String mUrl) {
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.mAuthor = mAuthor;
        this.mDateTime = mDateTime;
        this.mUrl = mUrl;

    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public String getmAuthor() { return mAuthor; }
}
