package getname.group.project_4.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import getname.group.project_4.charts.BarChart;
import getname.group.project_4.charts.GroupedBarChart;
import getname.group.project_4.charts.LineChart;
import getname.group.project_4.charts.PieChart;

public abstract class ActivityExtender extends AppCompatActivity{


    public void changeActivity(View view) {
        Intent intent = new Intent();
        boolean gotDestinationIntent = false;

        /**
         * tag is an array of Strings, which represent values used with the view
         */

        String[] tag = view.getTag().toString().split(",");
        Activities destination = Activities.valueOf(tag[0].toUpperCase());

        switch (destination) {
            case MAIN:
                gotDestinationIntent = true;
                intent = new Intent(this, MainActivity.class);
                break;
            case CHARTMENU:
                gotDestinationIntent = true;
                intent = new Intent(this, ChartMenuActivity.class);
                break;
            case BARCHART:
                gotDestinationIntent = true;
                intent = new Intent(this, BarChart.class);
                break;
            case GROUPEDBARCHART:
                gotDestinationIntent = true;
                intent = new Intent(this, GroupedBarChart.class);
                break;
            case PIECHART1:
                gotDestinationIntent = true;
                intent = new Intent(this, PieChart.class);
                break;
            case PIECHART2:
                gotDestinationIntent = true;
                intent = new Intent(this, PieChart.class);
                break;
            case LINECHART:
                gotDestinationIntent = true;
                intent = new Intent(this, LineChart.class);
                break;
            case CALENDER:
                gotDestinationIntent = true;
                intent = new Intent(this, CalenderActivity.class);
                break;
            case NOTE:
                gotDestinationIntent = true;
                intent = new Intent(this, NoteActivity.class);
                break;
            default: break;
        }


        if (!gotDestinationIntent) {
            Log.d("[Main.changeActivity()]", "Didn't get destination intent");
            intent = new Intent();
            startActivity(intent);
        } else {
            Log.d("[Main.changeActivity()]", "Got destination intent");
            startActivity(intent);
        }
    }
}