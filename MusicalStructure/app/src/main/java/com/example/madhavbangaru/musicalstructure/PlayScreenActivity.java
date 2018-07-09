package com.example.madhavbangaru.musicalstructure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Madhav Bangaru on 02-07-2018.
 */

public class PlayScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);
        String nameSong = getIntent().getStringExtra("song");
        String artistName = getIntent().getStringExtra("artist");
        TextView songName = findViewById(R.id.song_name);
        TextView artist = findViewById(R.id.artist);
        songName.setText(nameSong);
        artist.setText(artistName);
    }
}
