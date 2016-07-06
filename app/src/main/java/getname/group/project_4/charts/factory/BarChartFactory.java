package getname.group.project_4.charts.factory;

import Data.Queries;
import getname.group.project_4.charts.BarChartActivity;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;


public class BarChartFactory implements Factory<BarChartActivity> {
    @Override
    public BarChartActivity create(String... args) {
        ChartData cd;
        BarChartActivity barChart = new BarChartActivity();
        ChartData.ChartDataBuilder builder = null;
        for (String s : args) {
            if (s.startsWith("sql:")) {
                String[] sArray = s.split(": ");
                builder = new ChartData.ChartDataBuilder(sArray[1]);
            }
        }

        if (builder == null) {
            throw new NullPointerException("No Query found");
        }

        for (String s : args) {
            String[] splitted = s.split(": ");
            builder.tryParse(splitted[0], splitted[1]);
        }
        barChart.addData(builder.createChartData());
        return barChart;
    }
}