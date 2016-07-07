package getname.group.project_4.charts.factory;

import java.util.ArrayList;
import java.util.List;

import getname.group.project_4.charts.GroupedBarChartActivity;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class GroupedBarChartFactory implements Factory<GroupedBarChartActivity> {
    // Creates grouped barcharts.
    @Override
    public GroupedBarChartActivity create(String... args) {
        List<ChartData> cds = new ArrayList<>();
        GroupedBarChartActivity groupedBarChart = new GroupedBarChartActivity();
        ChartData.ChartDataBuilder builder = null;

        int cdCount = 1;
        boolean inObject = false, ranOnce = false, parsing = false;

        for (String s : args) {
            if (s.equals("^START^" + cdCount) && !inObject) {
                inObject = true;
            } else if (inObject) {
                if ( !(s.equals("^END^" + cdCount)) ) {
                    String[] splitted = s.split(": ");
                    if (s.startsWith("sql:")) {
                        builder = new ChartData.ChartDataBuilder(splitted[1]);
                    } else {
                        if (builder == null) {
                            throw new NullPointerException("No Query found");
                        } else {
                            builder.tryParse(splitted[0], splitted[1]);
                        }
                    }
                } else if (s.equals("^END^" + cdCount)){
                    if (builder == null) {
                        throw new NullPointerException("No Query found");
                    } else {
                        inObject = false; cdCount++; ranOnce = true;
                        cds.add(builder.createChartData());
                        builder = null;
                    }
                }
            } else {
                if (!ranOnce) {
                    LogHelper.logErrorMessage("GBC_FACTORY", "malformed input, every object for chartdata must start with ^START^<NUMBER> and end with ^END^<SAMENUMBER>");
                }
                else {
                    break;
                }
            }
        }

        int index = 0;
        while (index < cds.size()) {
            groupedBarChart.addData(cds.get(index));
            index++;
        }

        return groupedBarChart;
    }
}