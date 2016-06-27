package getname.group.project_4.charts.factory;

import getname.group.project_4.charts.Chart;
import getname.group.project_4.charts.BarChartActivity;

/**
 * Created by Lucas on 6/27/2016.
 */
public class BarChartFactory implements Factory<Chart> {
    @Override
    public Chart create(String... args) {
        return new BarChartActivity();
    }
}
