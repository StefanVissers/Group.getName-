package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;

public class BarChartActivity extends ActivityExtender implements Chart {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        Intent intent = getIntent();

        GraphView graph = (GraphView) findViewById(R.id.barchart);

//      Making datapoints for in the graph with value: (X, Y)

        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, -1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        graph.addSeries(series);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.barchart_activity);
        layout.removeAllViews();
        layout.addView(graph);
    }

    @Override
    public void addData(ChartData cd) {

    }
}
