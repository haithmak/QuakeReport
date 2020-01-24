package com.example.quakereport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EarthquakeAdapter extends ArrayAdapter<earthquake> {

    public EarthquakeAdapter (Context context , List<earthquake> earthquakes)
    {
        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListItemView = convertView;

        if(ListItemView==null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        earthquake currentEarthquake = getItem(position);
        TextView magnitudeView = (TextView) ListItemView.findViewById(R.id.magnitude);

        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());

        magnitudeView.setText(formattedMagnitude);



        TextView locationView = (TextView) ListItemView.findViewById(R.id.primary_location);
        TextView nearView = (TextView) ListItemView.findViewById(R.id.location_offset);


        if(currentEarthquake.getLocation().contains("of") &&currentEarthquake.getLocation().contains("km"))
        {
           String l[]  = currentEarthquake.getLocation().split("of") ;

            nearView.setText(l[0].trim() + " of");
            locationView.setText(l[1].trim());

            Toast.makeText(this.getContext() , ""+l[1] , Toast.LENGTH_SHORT).show();
            //   Log.e(TAG, "ok" );
        }
        else {
            nearView.setText("Near the");
            locationView.setText(currentEarthquake.getLocation());

        }




        // Format the magnitude to show 1 decimal place
       // locationView.setText(currentEarthquake.getLocation());
       // TextView dateView = (TextView) ListItemView.findViewById(R.id.date);
       // dateView.setText(currentEarthquake.getDate());
        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) ListItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) ListItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        // Format the magnitude to show 1 decimal place

        return ListItemView;
    }

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

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


}
