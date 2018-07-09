package com.example.madhavbangaru.tourguide;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Madhav Bangaru on 03-07-2018.
 */

public class LocationAdapter extends ArrayAdapter<Location> {
    public LocationAdapter(@NonNull Activity context,@NonNull ArrayList<Location> locations) {
        super(context, 0,locations);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.location_item, parent, false);
        }
        Location currentLocation = (Location) getItem(position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        nameTextView.setText(currentLocation.getLocationName());

        TextView timingTextView =(TextView) convertView.findViewById(R.id.timing);
        timingTextView.setText(currentLocation.getTimings());

        TextView contactTextView = (TextView)convertView.findViewById(R.id.phone);
        contactTextView.setText(currentLocation.getLocationContact());

        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageResource(currentLocation.getImageResourceId());

        return convertView;
    }
}
