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

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.debug.LogHelper;

public class PieChartActivity extends ActivityExtender implements Chart {
    PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> labels;
    String description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        Intent intent = getIntent();
        LogHelper.logDebugMessage("CREATE", this);
        pieChart = (PieChart) findViewById(R.id.chart);
        addFromSQL(new ArrayList<Entry>(), new ArrayList<String>(), new String());

        PieDataSet dataset = new PieDataSet(entries, description);
        dataset.setValueTextSize(30); //set text size

        PieData data = new PieData(labels, dataset); // initialize Piedata
        pieChart.setData(data); //set data into chart
        pieChart.setDescription(description);  // set the description
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // set the color
    }

    public void addFromSQL(ArrayList<Entry> entries, ArrayList<String> labels, String description) {
        if(entries.isEmpty()) {
            entries.add(new Entry(4f, 0));
            entries.add(new Entry(8f, 1));
            entries.add(new Entry(6f, 2));
            entries.add(new Entry(12f, 3));
            entries.add(new Entry(18f, 4));
            entries.add(new Entry(9f, 5));
        }

        this.entries = entries;

        if(labels.isEmpty()) {
            labels.add("January");
            labels.add("February");
            labels.add("March");
            labels.add("April");
            labels.add("May");
            labels.add("June");
        }

        this.labels = labels;

        if(description.isEmpty()) {
            description = "Description";
        }

        this.description = description;
    }

    @Override
    public void addData(ChartData cd) {
        LogHelper.logDebugMessage("ADD_DATA", this);
    }
}
