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
public class HistoricalFragment extends Fragment {

    public HistoricalFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.historicName1),getString(R.string.timing_10to5),"987654321",R.drawable.historic1));
        locations.add(new Location(getString(R.string.historicName2),getString(R.string.timing_10to5),"23512401 ",R.drawable.historic2));
        locations.add(new Location(getString(R.string.historicName3),getString(R.string.timing_10to5),"23520172 ",R.drawable.historic3));
        locations.add(new Location(getString(R.string.historicName4),getString(R.string.timing_10to5),"23234942",R.drawable.historic4));
        locations.add(new Location(getString(R.string.historicName5),getString(R.string.timing_10to5),"987654321",R.drawable.historic5));
        locations.add(new Location(getString(R.string.historicName6),getString(R.string.timing_10to5),"23522990",R.drawable.historic6));

        LocationAdapter adapter = new LocationAdapter(getActivity(),locations);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }
}
