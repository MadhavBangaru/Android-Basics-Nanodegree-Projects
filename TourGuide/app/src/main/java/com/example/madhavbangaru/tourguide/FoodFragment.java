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
public class FoodFragment extends Fragment {

    public FoodFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.foodName1),getString(R.string.timing_12to11),"23100455 ",R.drawable.food1));
        locations.add(new Location(getString(R.string.foodName2),getString(R.string.timing_12to11),"23555072",R.drawable.food2));
        locations.add(new Location(getString(R.string.foodName3),getString(R.string.timing_12to11),"23110523 ",R.drawable.food3));
        locations.add(new Location(getString(R.string.foodName4),getString(R.string.timing_12to11),"23607663 ",R.drawable.food4));
        locations.add(new Location(getString(R.string.foodName5),getString(R.string.timing_12to11),"23512401 ",R.drawable.food5));
        locations.add(new Location(getString(R.string.foodName6),getString(R.string.timing_12to11),"23520172 ",R.drawable.food6));
        locations.add(new Location(getString(R.string.foodName7),getString(R.string.timing_12to11),"23512401",R.drawable.food7));

        LocationAdapter adapter = new LocationAdapter(getActivity(),locations);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        return rootView;
    }

}
