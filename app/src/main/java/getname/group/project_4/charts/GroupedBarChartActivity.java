package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;

public class GroupedBarChartActivity extends ActivityExtender implements Chart {
    private ArrayList<BarChart> charts;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent intent = getIntent();

        charts.add((BarChart) findViewById(R.id.chart));

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
//
//        lineChart.setData(data);
//
//        lineChart.setDescription("Lorem ipsum");

//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    @Override
    public void addData(ChartData cd) {

    }
}
