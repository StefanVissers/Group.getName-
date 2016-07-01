package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.debug.LogHelper;

public class BarChartActivity extends ActivityExtender implements Chart {
    private static int counter = 0;
    private BarChart barchart;
    ArrayList<BarEntry> entries;
    ArrayList<String> labels;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        ID = counter++;
        LogHelper.logDebugMessage("CREATE", this);
        Intent intent = getIntent();
        barchart = (BarChart) findViewById(R.id.chart);
        addFromSQL(new ArrayList<BarEntry>(), new ArrayList<String>(), new String());

        BarDataSet dataSet = new BarDataSet(entries, description);
        dataSet.setValueTextSize(20);

        BarData data = new BarData(labels, dataSet);
        barchart.setData(data);
        barchart.setDescription(description);
        barchart.setVisibleXRangeMaximum(2);
        barchart.animateXY(3000, 3000);

//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    public void addFromSQL(ArrayList<BarEntry> entries, ArrayList<String> labels, String description) {
        if(entries.isEmpty()) {
            entries.add(new BarEntry(2f,0));
            entries.add(new BarEntry(5f,1));
            entries.add(new BarEntry(8f,2));
            entries.add(new BarEntry(0f,3));
        }
        this.entries = entries;

        if(labels.isEmpty()) {
            labels.add("January");
            labels.add("February");
            labels.add("March");
            labels.add("April");
        }
        this.labels = labels;

        if(description.isEmpty()) {

        }
        this.description = description;
    }

    @Override
    public void addData(ChartData cd) {
        LogHelper.logDebugMessage("ADD_DATA", this);
    }
}
