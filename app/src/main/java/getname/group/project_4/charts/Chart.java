package getname.group.project_4.charts;


import Data.builder.ChartData;

public interface Chart {
    void addData(ChartData cd);
    ChartData getData();
}
