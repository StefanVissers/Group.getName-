package getname.group.project_4.charts;


import java.util.List;

import Data.builder.ChartData;

public interface Chart {
    void addData(ChartData cd);
    List<ChartData> getData();
}
