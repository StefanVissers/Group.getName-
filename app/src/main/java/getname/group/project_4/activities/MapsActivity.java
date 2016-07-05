package getname.group.project_4.activities;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import Data.Main;
import Data.Queries;
import Data.builder.ChartData;
import getname.group.project_4.MainActivity;
import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.debug.LogHelper;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<LatLng> points = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latlngloc = new LatLng(MyLocation.getCurLat(),MyLocation.getCurLong());

        mMap = googleMap;

        float zoomlevel = 16.0f;
        mMap.addMarker(new MarkerOptions().position(latlngloc).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngloc, zoomlevel));


        DatabaseHelper dbhelper = new DatabaseHelper(this);

        try {
            dbhelper.createDataBase();
            dbhelper.openDataBase();

            String xyQuery = Queries.getFietstrommelsCoord()[0].split(": ")[1];
            ArrayList<Entry> Xentries = dbhelper.getFloatEntryList(xyQuery, 0);
            ArrayList<Entry> Yentries = dbhelper.getFloatEntryList(xyQuery, 1);
            ArrayList filter = new ArrayList();
            filter.add(new Entry(0f, 0));


            for (int i = 0; i < Xentries.size(); i++){
                LatLng latlong = new LatLng(((double) Xentries.get(i).getVal()), ((double) Yentries.get(i).getVal()));
                if (!(latlong.latitude == 0f && latlong.longitude == 0f)) {
                    points.add(latlong);
                }
            }

            for (int i = 0; i < points.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(points.get(i)).title("ik ben een fietstrommel"));
            }
        } catch (Exception e) {

        }
//        LatLng fietstrommel1 = new LatLng(getSql_query());

    }
}
