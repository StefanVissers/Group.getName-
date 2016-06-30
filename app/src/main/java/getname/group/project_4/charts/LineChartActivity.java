package getname.group.project_4.charts;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.debug.LogHelper;

public class LineChartActivity extends ActivityExtender implements Chart {
    LineChart lineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent intent = getIntent();

        LogHelper.logDebugMessage("CREATE", this);

        lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(2f,0));
        entries.add(new Entry(5f,1));
        entries.add(new Entry(8f,2));
        entries.add(new Entry(0f,3));

        LineDataSet dataSet = new LineDataSet(entries, "# of Calls");


        ArrayList<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");

        LineData data = new LineData(labels, dataSet);

        lineChart.setData(data);
        lineChart.animateY(3000);
        lineChart.setDescription("Lorem ipsum");

//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    @Override
    public void addData(ChartData cd) {
        LogHelper.logDebugMessage("ADD_DATA", this);
    }
}
