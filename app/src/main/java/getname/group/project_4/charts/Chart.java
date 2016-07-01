package getname.group.project_4.charts;


import getname.group.project_4.charts.builder.ChartData;

public interface Chart {
    void addData(ChartData cd);
    ChartData getData();
}
