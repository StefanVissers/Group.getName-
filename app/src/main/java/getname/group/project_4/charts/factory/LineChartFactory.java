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
            String[] splitted = s.split(": ");
            builder.tryParse(splitted[0], splitted[1]);
        }
        lineChart.addData(builder.createChartData());
        return lineChart;
    }
}
