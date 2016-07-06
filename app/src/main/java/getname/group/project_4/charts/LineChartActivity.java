package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

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

        String[] values = new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006",
                "2007", "2008", "2009", "2010", "2011", "2012"
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
                String filter = "";

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                if (itemPosition == 0){
                    // Filter 1
                    filter = "Cast(jaar AS INTEGER) = 2000";
                } else if (itemPosition == 1) {
                    // Filter 2
                    filter = "Cast(jaar AS INTEGER) = 2001";
                } else if (itemPosition == 2) {
                    // Filter 3
                    filter = "Cast(jaar AS INTEGER) = 2002";
                } else if (itemPosition == 3) {
                    // Filter 4
                    filter = "Cast(jaar AS INTEGER) = 2003";
                } else if (itemPosition == 4) {
                    // Filter 5
                    filter = "Cast(jaar AS INTEGER) = 2004";
                } else if (itemPosition == 5) {
                    // Filter 6
                    filter = "Cast(jaar AS INTEGER) = 2005";
                } else if (itemPosition == 6) {
                    // Filter 7
                    filter = "Cast(jaar AS INTEGER) = 2006";
                } else if (itemPosition == 7) {
                    // Filter 8
                    filter = "Cast(jaar AS INTEGER) = 2007";
                } else if (itemPosition == 8) {
                    // Filter 9
                    filter = "Cast(jaar AS INTEGER) = 2008";
                } else if (itemPosition == 9) {
                    // Filter 10
                    filter = "Cast(jaar AS INTEGER) = 2009";
                } else if (itemPosition == 10) {
                    // Filter 11
                    filter = "Cast(jaar AS INTEGER) = 2010";
                } else if (itemPosition == 11) {
                    // Filter 12
                    filter = "Cast(jaar AS INTEGER) = 2011";
                } else if (itemPosition == 12) {
                    // Filter 13
                    filter = "Cast(jaar AS INTEGER) = 2012";
                } else {
                    // Error Message
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                }

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                ChartData.ChartDataBuilder builder =
                        new ChartData.ChartDataBuilder(chartData.getSql_query())
                                .setNestedTitle(chartData.getTitle())
                                .setNestedDesc(chartData.getDesc())
                                .setNestedColor(chartData.getColor())
                                .setNestedRelativeTime(chartData.getRelativeTime())
                                .setNestedFilter(filter)
                        ;

                getIntent().putExtra("ChartData", builder.createChartData());
                recreate();


            }

        });


        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            if (chartData.isFiltered()) {
                entries = databaseHelper.getEntryList(chartData.getFiltered_query(), 2);
                labels = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 0);
            } else {
                entries = databaseHelper.getEntryList(chartData.getSql_query(), 2);
                labels = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            }
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
    public List<ChartData> getData() {
        List<ChartData> cd = new ArrayList<>();
        cd.add(chartData);
        return cd;
    }
}
