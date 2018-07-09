package com.example.madhavbangaru.musicalstructure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Madhav Bangaru on 02-07-2018.
 */

public class FavoritesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        final ArrayList<Song> songList = new ArrayList<>();
        songList.add(new Song("Eminem","Love The Way You Lie"));
        songList.add(new Song("Cardi B","Bodak Yellow"));
        songList.add(new Song("Rihanna","We Found Love"));
        songList.add(new Song("Calvin Harris","Frank Ocean & Migos"));
        songList.add(new Song("Justin Bieber","Never Say Never"));
        songList.add(new Song("Harry Styles","Sign of the Times"));
        songList.add(new Song("Adele","Someone Like You"));
        songList.add(new Song("Jennifer Lopez","Papi"));

        SongAdapter adapter = new SongAdapter(this, songList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(FavoritesActivity.this ,PlayScreenActivity.class);
                i.putExtra("song", songList.get(position).getFirstItem());
                i.putExtra("artist", songList.get(position).getSecondItem());
                startActivity(i);
            }
        });
    }
}
