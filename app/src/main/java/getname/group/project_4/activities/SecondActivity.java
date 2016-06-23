package getname.group.project_4.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import getname.group.project_4.R;

public class SecondActivity extends ActivityExtender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);

        Log.e("[Second]", "Creating Second");

        setContentView(R.layout.secondary_activity);

        GraphView graph = (GraphView) findViewById(R.id.chart);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 1.5),
                new DataPoint(2, 1.5),
                new DataPoint(3, 2),
                new DataPoint(4, 1.0)
        });
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.second_activity);
        layout.removeAllViews();
        layout.addView(graph);
    }

}
