package getname.group.project_4.charts.factory;

import getname.group.project_4.charts.Chart;
import getname.group.project_4.charts.BarChartActivity;
import getname.group.project_4.charts.builder.ChartData;
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
            String[] sArray = s.split(": ");
            String type = sArray[0];
            String value = sArray[1];
            if (type.equalsIgnoreCase("title")) {
                builder.setNestedTitle(value);
            } else if (type.equalsIgnoreCase("desc")) {
                builder.setNestedDesc(value);
            } else if (type.equalsIgnoreCase("color")) {
                builder.setNestedColor(value);
            } else if (type.equalsIgnoreCase("filter")) {
                builder.setNestedFilter(value);
            }
        }
        barChart.addData(builder.createChartData());
        return barChart;
    }
}