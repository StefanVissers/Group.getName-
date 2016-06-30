package getname.group.project_4.activities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import getname.group.project_4.debug.LogHelper;


public class getLocation extends MyLocation {

//    public void run(Context context, Location mLastLocation) {
//
//        double lat = mLastLocation.getLatitude();
//        double lng = mLastLocation.getLongitude();
//
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//        try {
//            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//            String address = addresses.get(0).getAddressLine(0);
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
//
//            Log.d("a", address);
//        } catch (IOException io)
//        {
//        }
//    }
//    public void run(Context context) {
//        if (!(mLastLocation == null)) {
//            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//            try {
//                List<Address> addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
//                Address gotAddress = addresses.get(0);
//                String sAddress = gotAddress.toString();
//                //Toast.makeText(this, sAddress, Toast.LENGTH_SHORT);
//                LogHelper.logDebugMessage("GetLoc: ", sAddress);
//            } catch (IOException e) {
//                Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
