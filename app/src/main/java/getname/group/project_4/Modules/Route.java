package getname.group.project_4.Modules;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class Route {
    public Distance distance;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}