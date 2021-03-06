package getname.group.project_4.activities;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.evernote.android.intent.CreateNewNoteIntentBuilder;
import com.evernote.android.intent.EvernoteIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import getname.group.project_4.R;
import getname.group.project_4.debug.LogHelper;

public class MyLocation extends ActivityExtender implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{
    // Finds your location.

    protected TextView mLatitudeText;
    protected TextView mLongitudeText;
    protected static Location mLastLocation;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLatitudeText = (TextView) findViewById(R.id.loclat);
        mLongitudeText = (TextView)  findViewById(R.id.loclong);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        try
        {
            Location newLoc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (newLoc!=null) {
                mLastLocation = newLoc;
            }
        }
        catch(SecurityException sx)
        {
            Toast.makeText(this,"Security Exception",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        // Connect to the google maps api.
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public static double getCurLat(){
        double currentLatLng = mLastLocation.getLatitude();
        return currentLatLng;
    }

    public static double getCurLong(){
        double currentLongLng = mLastLocation.getLongitude();
        return currentLongLng;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this,"onConnected",Toast.LENGTH_SHORT).show();
        try
        {
            Location newLoc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (newLoc!=null) {
                mLastLocation = newLoc;
            }
        }
        catch(SecurityException ex)
        {
            Toast.makeText(this,"Security Exception",Toast.LENGTH_SHORT).show();
        }
        if (mLastLocation != null){
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(1);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                0);

        try
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        catch(SecurityException sx)
        {
            Toast.makeText(this,"Security Exception",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"Connection Failed",Toast.LENGTH_SHORT).show();
    }

    public void onLocationChanged(Location location) {

        Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

        mLatitudeText.setText(String.valueOf(location.getLatitude()));
        mLongitudeText.setText(String.valueOf(location.getLongitude()));

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    public String getLocation() {
        // Gets your location.
        if (!(mLastLocation == null)) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                String gotAddressLine = addresses.get(0).getAddressLine(0);
                String gotCity = addresses.get(0).getLocality();
                String gotPostalCode = addresses.get(0).getPostalCode();
                String sAddress = gotAddressLine + "\n" + gotCity + "\n"+ gotPostalCode;
                return sAddress;

            } catch (IOException e)
            {
                Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
            }
        }
        return "Not Found";
    }

    public void savePlainTextNote(View view) {
        String address = getLocation();
        Intent intent = EvernoteIntent.createNewNote()
                .setTitle("Location")
                .addTags("Location")
                .setTextPlain("Address: " + address)
                .setSourceApp(getPackageName())
                .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
                .create();

        startActivity(intent);
    }
}