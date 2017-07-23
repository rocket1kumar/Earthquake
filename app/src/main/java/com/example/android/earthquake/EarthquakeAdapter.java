package com.example.android.earthquake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Raider on 29-03-2017.
 */
// earthquake is list of earthquakes whichis data adapter
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String locationOffset;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // if there is existing list view item, which can be reused
        // if convertview is nullm then inflate new list view item.
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.eathquake_list_item, parent, false);

        }
        // find the position of the earthquake in the list of earthquake items
        Earthquake currentEarthquake = getItem(position);
        // Spit location string into two parts
        String originalLocation = currentEarthquake.getLocation();
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            // if offset location is not available
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        // find the textview with id magnitude
        TextView magnitudeView = (TextView) listView.findViewById(R.id.magnitude);
        // display the magnitude of the current earthquake in textview
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getMagnitude());
        magnitudeView.setText(output);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        int magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // find the textview with id primary location
        TextView primaryLocationView = (TextView) listView.findViewById(R.id.primary_location);
        // display the primary location of the current earthquake in textview
        primaryLocationView.setText(primaryLocation);

        // find the textview with id primary location_offset
        TextView locationOffsetView = (TextView) listView.findViewById(R.id.location_offset);
        // display the location_offset of the current earthquake in textview
        locationOffsetView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
