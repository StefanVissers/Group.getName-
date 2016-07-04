package getname.group.project_4.charts.factory;

import Data.Queries;
import getname.group.project_4.charts.GroupedBarChartActivity;
import Data.builder.ChartData;

public class GroupedBarChartFactory implements Factory<GroupedBarChartActivity> {
    @Override
    public GroupedBarChartActivity create(String... args) {
        ChartData cd;
        GroupedBarChartActivity groupedBarChart = new GroupedBarChartActivity();
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
        groupedBarChart.addData(builder.createChartData());
        return groupedBarChart;
    }
}
