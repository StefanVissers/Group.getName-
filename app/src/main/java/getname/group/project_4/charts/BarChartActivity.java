package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;

public class BarChartActivity extends ActivityExtender implements Chart {
    private BarChart barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        Intent intent = getIntent();

        barchart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(2f,0));
        entries.add(new BarEntry(5f,1));
        entries.add(new BarEntry(8f,2));
        entries.add(new BarEntry(0f,3));

        BarDataSet dataSet = new BarDataSet(entries, "# of Calls");


        ArrayList<String> labels = new ArrayList<>();
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

    }
}
