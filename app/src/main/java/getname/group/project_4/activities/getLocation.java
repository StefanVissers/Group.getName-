package getname.group.project_4.activities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import getname.group.project_4.debug.LogHelper;

public class getLocation extends MyLocation {

    public void run(Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
//            System.out.println(addresses);
//            logDebugMessage("ADRESS", addresses);
            LogHelper.logDebugMessage("run", "werk nie");
        } catch (IOException e)
        {

        }
        run(context);
    }
}
