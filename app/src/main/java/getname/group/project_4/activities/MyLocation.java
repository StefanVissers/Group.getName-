package getname.group.project_4.activities;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import getname.group.project_4.R;

public class MyLocation extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    private TextView mLatitudeText;
    private TextView mLongitudeText;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    Location mLastLocation;

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
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        catch(SecurityException sx){
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this,"onConnected",Toast.LENGTH_SHORT).show();
        try
        {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
            catch(SecurityException ex){
            };
        if (mLastLocation != null){
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(1);

        try
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        catch(SecurityException sx){
        };
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view){

    }

    protected synchronized void buildGoogleApiClient(){
        Toast.makeText(this,"buildGoogleApiClient",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    public void onLocationChanged(Location location) {

        Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

        mLatitudeText.setText(String.valueOf(location.getLatitude()));
        mLongitudeText.setText(String.valueOf(location.getLongitude()));

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }
}