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
public class PlaceFragment extends Fragment {

    public PlaceFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.placeName1),getString(R.string.timing_10to5),"23522990",R.drawable.place1));
        locations.add(new Location(getString(R.string.placeName2),getString(R.string.timing_10to5),"23512401 ",R.drawable.place2));
        locations.add(new Location(getString(R.string.placeName3),getString(R.string.timing_10to5),"23520172 ",R.drawable.place3));
        locations.add(new Location(getString(R.string.placeName4),getString(R.string.timing_10to5),"23233259 ",R.drawable.place4));
        locations.add(new Location(getString(R.string.placeName5),getString(R.string.timing_10to5),"24523211 ",R.drawable.place5));
        locations.add(new Location(getString(R.string.placeName6),getString(R.string.timing_10to5),"23234942",R.drawable.place6));
        locations.add(new Location(getString(R.string.placeName7),getString(R.string.timing_10to5),"24477355",R.drawable.place7));

        LocationAdapter adapter = new LocationAdapter(getActivity(),locations);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }

}
