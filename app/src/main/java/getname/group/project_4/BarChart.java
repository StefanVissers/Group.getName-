package getname.group.project_4;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class BarChart extends Chart {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart_activity);

        Intent intent = getIntent();

        GraphView graph = (GraphView) findViewById(R.id.barchart);
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
    public void draw() {

    }
}
