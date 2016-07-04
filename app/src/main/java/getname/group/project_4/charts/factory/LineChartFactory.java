package getname.group.project_4.charts.factory;

import Data.Queries;
import getname.group.project_4.charts.LineChartActivity;
import Data.builder.ChartData;

public class LineChartFactory implements Factory<LineChartActivity> {
    @Override
    public LineChartActivity create(String... args) {
        LineChartActivity lineChart = new LineChartActivity();
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
        lineChart.addData(builder.createChartData());
        return lineChart;
    }
}
