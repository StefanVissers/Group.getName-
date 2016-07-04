package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class GroupedBarChartActivity extends ActivityExtender implements Chart {
    private static int counter = 0;
    private BarChart barchart;
    ArrayList<BarEntry> entries;
    ArrayList<IBarDataSet> groups;
    ArrayList<String> labels;
    String title;
    String description;
    ChartData chartData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_groupedbarchart);
//
//        ID = counter++;
//        LogHelper.logDebugMessage("CREATE", this);
//
//        Intent intent = getIntent();
//
//        barchart = ((BarChart) findViewById(R.id.chart));
//
//        List<BarEntry> group1 = new ArrayList<>();
//        group1.add(new BarEntry(2f,0));
//        group1.add(new BarEntry(10f,1));
//        group1.add(new BarEntry(14f,2));
//        group1.add(new BarEntry(4f,3));
//        group1.add(new BarEntry(15f,4));
//
//        BarDataSet group1DataSet = new BarDataSet(group1, "group1");
//        group1DataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//        List<BarEntry> group2 = new ArrayList<>();
//        group2.add(new BarEntry(21f,0));
//        group2.add(new BarEntry(4f,1));
//        group2.add(new BarEntry(7f,2));
//        group2.add(new BarEntry(18f,3));
//        group2.add(new BarEntry(2f,4));
//
//        BarDataSet group2DataSet = new BarDataSet(group2, "group 2");
//        group2DataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//
//        List<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(group1DataSet);
//        dataSets.add(group2DataSet);
//
//        List<String> labels = new ArrayList<>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//
//        BarData data = new BarData(labels, dataSets);
//
//        System.out.println("foo");
//
//        barchart.setData(data);
//        barchart.animateY(3000);

        setContentView(R.layout.activity_groupedbarchart);

        ID = counter++;
        LogHelper.logDebugMessage("CREATE", this);

        Intent intent = getIntent();
        barchart = (BarChart) findViewById(R.id.chart);

        chartData = (ChartData) getIntent().getSerializableExtra("ChartData");

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            BarDataSet group1 = new BarDataSet(
                                databaseHelper.getBarEntryList(chartData.getSql_query(), 1),
                                "group1");
            BarDataSet group2 = new BarDataSet(
                                databaseHelper.getBarEntryList(chartData.getSql_query(), 2),
                                "group2");

            groups.add(group1);
            groups.add(group2);

            labels = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            description = chartData.getDesc();
            title = chartData.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BarData data = new BarData(labels, groups);
        barchart.setData(data);
        barchart.setDescription(description);
        barchart.setVisibleXRangeMaximum(2);
        barchart.animateXY(3000, 3000);

    }

    @Override
    public void addData(ChartData cd) {
        this.chartData = chartData;
        LogHelper.logDebugMessage("ADD_DATA", this);
    }

    public ChartData getData() {
        return chartData;
    }
}
