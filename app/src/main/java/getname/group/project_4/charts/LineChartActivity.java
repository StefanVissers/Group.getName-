package getname.group.project_4.charts;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.charts.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class LineChartActivity extends ActivityExtender implements Chart {
    LineChart lineChart;
    ArrayList<Entry> entries;
    ArrayList<String> labels;
    String title;
    String description;
    ChartData chartData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent intent = getIntent();
        LogHelper.logDebugMessage("CREATE", this);
        lineChart = (LineChart) findViewById(R.id.chart);
        chartData = (ChartData) getIntent().getSerializableExtra("ChartData");

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            String str = chartData.getSql_query();
            entries = databaseHelper.getEntryList(chartData.getSql_query(), 2);
            labels = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            description = chartData.getDesc();
            title = chartData.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LineDataSet dataSet = new LineDataSet(entries, description);
        LineData data = new LineData(labels, dataSet);
        lineChart.setData(data);
        lineChart.animateY(3000);
        lineChart.setDescription(description);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
    }

    @Override
    public void addData(ChartData cd) {
        this.chartData = cd;
        LogHelper.logDebugMessage("ADD_DATA", this);
    }

    @Override
    public ChartData getData() {
        return chartData;
    }
}
