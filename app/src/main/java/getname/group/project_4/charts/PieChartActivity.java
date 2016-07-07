package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class PieChartActivity extends ActivityExtender implements Chart {
    PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> labels;
    String title;
    String description;
    ChartData chartData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        Intent intent = getIntent();
        LogHelper.logDebugMessage("CREATE", "piechart");
        pieChart = (PieChart) findViewById(R.id.chart);

        chartData = (ChartData) getIntent().getSerializableExtra("ChartData");

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Gets data from the database.
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            entries = databaseHelper.getEntryList(chartData.getSql_query(), 1);
            labels = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            description = chartData.getDesc();
            title = chartData.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PieDataSet dataset = new PieDataSet(entries, description);
        dataset.setValueTextSize(20); //set text size

        PieData data = new PieData(labels, dataset); // initialize Piedata
        pieChart.setData(data); //set data into chart
        pieChart.setDescription(description);  // set the description
        pieChart.animateXY(3000, 3000);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // set the color
    }


    @Override
    public void addData(ChartData cd) {
        this.chartData = cd;
        LogHelper.logDebugMessage("ADD_DATA", this);
    }

    @Override
    public List<ChartData> getData() {
        List<ChartData> cd = new ArrayList<>();
        cd.add(chartData);
        return cd;
    }
}
