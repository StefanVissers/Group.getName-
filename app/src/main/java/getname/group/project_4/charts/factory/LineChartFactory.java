package getname.group.project_4.charts.factory;

import getname.group.project_4.charts.Chart;
import getname.group.project_4.charts.LineChartActivity;

public class LineChartFactory implements Factory<Chart> {
    @Override
    public Chart create(String... args) {
        return new LineChartActivity();
    }
}
