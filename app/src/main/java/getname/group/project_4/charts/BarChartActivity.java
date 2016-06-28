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
import getname.group.project_4.activities.ActivityExtender;

public class BarChartActivity extends ActivityExtender implements Chart {
    private static int counter = 0;
    private BarChart barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        ID = counter++;
        logDebugMessage("CREATE", this);

        Intent intent = getIntent();

        barchart = (BarChart) findViewById(R.id.chart);

        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(2f,0));
        entries.add(new BarEntry(5f,1));
        entries.add(new BarEntry(8f,2));
        entries.add(new BarEntry(0f,3));

        BarDataSet dataSet = new BarDataSet(entries, "# of Calls");


        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");

        BarData data = new BarData(labels, dataSet);

        barchart.setData(data);

        barchart.setDescription("Lorem ipsum");

//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    @Override
    public void addData(ChartData cd) {
        logDebugMessage("ADD_DATA", this);
    }
}
