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

public class PlaylistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        final ArrayList<Song> songList = new ArrayList<>();
        songList.add(new Song("Adele","Hello"));
        songList.add(new Song("Taylor Swift","Blank Space"));
        songList.add(new Song("Maroon 5","Wait"));
        songList.add(new Song("OneRepublic","Counting Stars"));
        songList.add(new Song("The Chainsmokers","Closer"));
        songList.add(new Song("Harry Styles","Sign of the Times"));
        songList.add(new Song("The Weeknd"," feat. Daft Punk"));
        songList.add(new Song("Justin Bieber","What Do You Mean?"));

        SongAdapter adapter = new SongAdapter(this, songList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PlaylistActivity.this ,PlayScreenActivity.class);
                i.putExtra("song", songList.get(position).getFirstItem());
                i.putExtra("artist", songList.get(position).getSecondItem());
                startActivity(i);
            }
        });
    }
}
