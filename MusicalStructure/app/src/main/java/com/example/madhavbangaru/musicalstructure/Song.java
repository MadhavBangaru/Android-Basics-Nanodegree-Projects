package com.example.madhavbangaru.musicalstructure;

/**
 * Created by Madhav Bangaru on 02-07-2018.
 */

public class Song {
    private String mSong;
    private String mArtist;

    public Song(String firstItem, String secondItem) {
        this.mSong = firstItem;
        this.mArtist = secondItem;
    }

    public String getFirstItem() {
        return mSong;
    }

    public String getSecondItem() {
        return mArtist;
    }
}
