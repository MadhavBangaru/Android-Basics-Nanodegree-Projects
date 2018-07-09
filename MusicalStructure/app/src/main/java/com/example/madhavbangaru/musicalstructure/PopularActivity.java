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

public class PopularActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        final ArrayList<Song> songList = new ArrayList<>();
        songList.add(new Song("Selena Gomez","Bad Liar"));
        songList.add(new Song("Luis Fonsi","Despacito"));
        songList.add(new Song("Maroon 5","Wait"));
        songList.add(new Song("Wiz Khalifa","See You Again"));
        songList.add(new Song("Ed Sheeran","Shape of You"));
        songList.add(new Song("PSY","GANGNAM STYLE"));
        songList.add(new Song("The Weeknd"," feat. Daft Punk"));
        songList.add(new Song("Mark Ronson","Uptown Funk"));

        SongAdapter adapter = new SongAdapter(this, songList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PopularActivity.this ,PlayScreenActivity.class);
                i.putExtra("song", songList.get(position).getFirstItem());
                i.putExtra("artist", songList.get(position).getSecondItem());
                startActivity(i);
            }
        });
    }
}
