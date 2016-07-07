package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class BarChartActivity extends ActivityExtender implements Chart {
    private BarChart barchart;
    ArrayList<BarEntry> entries;
    ArrayList<String> labels;
    String title;
    String description;
    ChartData chartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        Intent intent = getIntent();
        barchart = (BarChart) findViewById(R.id.chart);

        chartData = (ChartData) getIntent().getSerializableExtra("ChartData");

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Gets data from the database.
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            entries = databaseHelper.getBarEntryList(chartData.getSql_query(), 1);
            labels = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            description = chartData.getDesc();
            title = chartData.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BarDataSet dataSet = new BarDataSet(entries, description);
        dataSet.setValueTextSize(20);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(labels, dataSet);
        barchart.setData(data);
        barchart.setDescription(description);
        barchart.setVisibleXRangeMaximum(2);
        barchart.animateXY(3000, 3000);
    }

    @Override
    public void addData(ChartData cd) {
        this.chartData = cd;
    }

    public List<ChartData> getData() {
        List<ChartData> cd = new ArrayList<>();
        cd.add(chartData);
        return cd;
    }
}
