package getname.group.project_4.charts.factory;

import Data.Queries;
import Data.builder.ChartData;
import getname.group.project_4.charts.PieChartActivity;


public class PieChartFactory implements Factory<PieChartActivity> {
    // Creates piecharts.
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
            String[] splitted = s.split(": ");
            builder.tryParse(splitted[0], splitted[1]);
        }
        pieChart.addData(builder.createChartData());
        return pieChart;
    }
}
