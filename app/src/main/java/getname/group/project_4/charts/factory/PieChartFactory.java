package getname.group.project_4.charts.factory;

import getname.group.project_4.charts.Chart;
import getname.group.project_4.charts.ChartData;
import getname.group.project_4.charts.PieChartActivity;

/**
 * Created by Lucas on 6/27/2016.
 */
public class PieChartFactory implements Factory<Chart> {
    @Override
    public Chart create(String... args) {
        PieChartActivity pieChartActivity = new PieChartActivity();
        ChartData cd = new ChartData();

        for (String s : args) { cd.parseAdd(s); }
        pieChartActivity.addData(cd);

        return pieChartActivity;
    }
}
