package com.example.madhavbangaru.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Madhav Bangaru on 02-07-2018.
 */

public class LatestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        final ArrayList<Song> songList = new ArrayList<>();
        songList.add(new Song("Ariana Grande","no tears left to cry"));
        songList.add(new Song("Alejandro Reyes","I Already Know"));
        songList.add(new Song("Ed Sheeran","Perfect"));
        songList.add(new Song("ZAYN","Dusk Till Dawn ft. Sia"));
        songList.add(new Song("Camila Cabello","Havana"));
        songList.add(new Song("Taylor Swift","End Game"));
        songList.add(new Song("Ananya Birla","Hold On"));
        songList.add(new Song("Sean Paul, David Guetta","Mad Love"));

        SongAdapter adapter = new SongAdapter(this, songList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(LatestActivity.this ,PlayScreenActivity.class);
                i.putExtra("song", songList.get(position).getFirstItem());
                i.putExtra("artist", songList.get(position).getSecondItem());
                startActivity(i);
            }
        });
    }
}
