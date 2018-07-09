package com.example.madhavbangaru.tourguide;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelsFragment extends Fragment {

    public HotelsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.hotelName1),getString(R.string.round_the_clock),"23555072",R.drawable.hotel1));
        locations.add(new Location(getString(R.string.hotelName2),getString(R.string.round_the_clock),"987654321",R.drawable.hotel2));
        locations.add(new Location(getString(R.string.hotelName3),getString(R.string.round_the_clock),"23100455 ",R.drawable.hotel3));
        locations.add(new Location(getString(R.string.hotelName4),getString(R.string.round_the_clock),"987654321",R.drawable.hotel4));
        locations.add(new Location(getString(R.string.hotelName5),getString(R.string.round_the_clock),"24477355",R.drawable.hotel5));
        locations.add(new Location(getString(R.string.hotelName6),getString(R.string.round_the_clock),"987654321",R.drawable.hotel6));
        locations.add(new Location(getString(R.string.hotelName7),getString(R.string.round_the_clock),"23234942",R.drawable.hotel7));

        LocationAdapter adapter = new LocationAdapter(getActivity(),locations);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }
}
