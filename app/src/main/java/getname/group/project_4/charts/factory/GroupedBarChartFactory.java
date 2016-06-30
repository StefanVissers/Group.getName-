package getname.group.project_4.charts.factory;

import getname.group.project_4.charts.Chart;
import getname.group.project_4.charts.GroupedBarChartActivity;

public class GroupedBarChartFactory implements Factory<Chart> {
    @Override
    public Chart create(String... args) {
        return new GroupedBarChartActivity();

    }
}
