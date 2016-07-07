package getname.group.project_4.Modules;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import javax.xml.datatype.Duration;

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}