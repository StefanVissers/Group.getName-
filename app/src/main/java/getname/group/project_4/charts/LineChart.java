package getname.group.project_4.charts;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import getname.group.project_4.R;

public class LineChart extends Chart {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);

        Log.e("[LineChart]", "Creating LineChart");

        setContentView(R.layout.activity_linechart);

        GraphView graph = (GraphView) findViewById(R.id.chart);

//      Making datapoints for in the graph with value: (X, Y)

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 1.5),
                new DataPoint(2, 1.5),
                new DataPoint(3, 2),
                new DataPoint(4, 1.0)
        });
        series.setColor(Color.GREEN);   // set color
        graph.addSeries(series);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_linechart);
        layout.removeAllViews();
        layout.addView(graph);
    }

    @Override
    public void draw() {

    }
}
