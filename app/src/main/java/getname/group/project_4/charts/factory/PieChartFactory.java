package getname.group.project_4.charts.factory;

import Data.Queries;
import Data.builder.ChartData;
import getname.group.project_4.charts.PieChartActivity;


public class PieChartFactory implements Factory<PieChartActivity> {
    @Override
    public PieChartActivity create(String... args) {
        PieChartActivity pieChart = new PieChartActivity();
        ChartData cd;
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
            } else if (type.equalsIgnoreCase("reltime")) {
                builder.setNestedRelativeTime(Queries.RelativeTime.valueOf(value));
            }
        }
        pieChart.addData(builder.createChartData());
        return pieChart;
    }
}
