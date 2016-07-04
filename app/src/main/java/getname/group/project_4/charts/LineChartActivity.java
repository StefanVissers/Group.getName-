package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class LineChartActivity extends ActivityExtender implements Chart {
    LineChart lineChart;
    ArrayList<Entry> entries;
    ArrayList<String> labels;
    String title;
    String description;
    ChartData chartData;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent intent = getIntent();
        LogHelper.logDebugMessage("CREATE", this);
        lineChart = (LineChart) findViewById(R.id.chart);
        chartData = (ChartData) getIntent().getSerializableExtra("ChartData");

        listView = (ListView) findViewById(R.id.listView);

        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });


        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            String str = chartData.getSql_query();
            entries = databaseHelper.getEntryList(chartData.getSql_query(), 2);
            labels = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            description = chartData.getDesc();
            title = chartData.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LineDataSet dataSet = new LineDataSet(entries, description);
        LineData data = new LineData(labels, dataSet);
        lineChart.setData(data);
        lineChart.animateY(3000);
        lineChart.setDescription(description);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
    }

    @Override
    public void addData(ChartData cd) {
        this.chartData = cd;
        LogHelper.logDebugMessage("ADD_DATA", this);
    }

    @Override
    public ChartData getData() {
        return chartData;
    }
}
