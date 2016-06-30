package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.debug.LogHelper;

public class PieChartActivity extends ActivityExtender implements Chart {
    PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        Intent intent = getIntent();

        LogHelper.logDebugMessage("CREATE", this);

        pieChart = (PieChart) findViewById(R.id.chart);

        // creating data values
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            entries = databaseHelper.getEntryList("select [kleur], Count(*) as cnt\n" +
                    "from fietsdiefstal\n" +
                    "where [kleur]<>\"\"\n" +
                    "Group By [kleur]\n" +
                    "Order By cnt Desc\n" +
                    "limit 5");
            labels = databaseHelper.getEntryListLabels("select [kleur], Count(*) as cnt\n" +
                    "from fietsdiefstal\n" +
                    "where [kleur]<>\"\"\n" +
                    "Group By [kleur]\n" +
                    "Order By cnt Desc\n" +
                    "limit 5");


        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        entries.add(new Entry(4f, 0));
//        entries.add(new Entry(8f, 1));
//        entries.add(new Entry(6f, 2));
//        entries.add(new Entry(12f, 3));
//        entries.add(new Entry(18f, 4));
//        entries.add(new Entry(9f, 5));

        PieDataSet dataset = new PieDataSet(entries, "Gestolen fietsen per kleur");

        dataset.setValueTextSize(20); //set text size

        // creating labels
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");

        PieData data = new PieData(labels, dataset); // initialize Piedata
        pieChart.setData(data); //set data into chart

        pieChart.setDescription("Description");  // set the description

        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // set the color
    }

    @Override
    public void addData(ChartData cd) {
        LogHelper.logDebugMessage("ADD_DATA", this);
    }
}
