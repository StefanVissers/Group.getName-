package getname.group.project_4.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;

import java.util.NoSuchElementException;

import Data.Queries;
import getname.group.project_4.MainActivity;
import getname.group.project_4.charts.BarChartActivity;
import getname.group.project_4.charts.GroupedBarChartActivity;
import getname.group.project_4.charts.LineChartActivity;
import getname.group.project_4.charts.PieChartActivity;
import getname.group.project_4.R;
import getname.group.project_4.charts.factory.BarChartFactory;
import getname.group.project_4.charts.factory.Factory;
import getname.group.project_4.charts.factory.GroupedBarChartFactory;
import getname.group.project_4.charts.factory.LineChartFactory;
import getname.group.project_4.charts.factory.PieChartFactory;


public abstract class ActivityExtender extends AppCompatActivity {
    /**
     * Finds new Activity according to tag attached to view and switches to it.
     * @param view
     */
    public void changeActivity(View view) {
        Intent intent = new Intent();
        boolean gotDestinationIntent = false;
        Factory factory;

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
                factory = new BarChartFactory();
                BarChartActivity BCactivity = (BarChartActivity) factory.create(Queries.getBarStat1());
                intent = new Intent(this, BCactivity.getClass());
                intent.putExtra("ChartData", BCactivity.getData().get(0));
                break;
            case GROUPEDBARCHART:
                gotDestinationIntent = true;
                factory = new GroupedBarChartFactory();
                GroupedBarChartActivity GBCactivity = (GroupedBarChartActivity) factory.create(Queries.getGroupedBarStat1());
                intent = new Intent(this, GBCactivity.getClass());
                int cdAmount = 0;
                for (int i = 0; i < GBCactivity.getData().size(); i++) {
                    intent.putExtra("ChartData" + i, GBCactivity.getData().get(i));
                    cdAmount++;
                }
                intent.putExtra("cdAmount", cdAmount);
                break;
            case PIECHART1:
                gotDestinationIntent = true;
                factory = new PieChartFactory();
                PieChartActivity PC1activity = (PieChartActivity) factory.create(Queries.getPieStat1());
                intent = new Intent(this, PC1activity.getClass());
                intent.putExtra("ChartData", PC1activity.getData().get(0));
                break;
            case PIECHART2:
                gotDestinationIntent = true;
                factory = new PieChartFactory();
                PieChartActivity PC2activity = (PieChartActivity) factory.create(Queries.getPieStat2());
                intent = new Intent(this, PC2activity.getClass());
                intent.putExtra("ChartData", PC2activity.getData().get(0));
                break;
            case LINECHART:
                gotDestinationIntent = true;
                factory = new LineChartFactory();
                LineChartActivity LCactivity = (LineChartActivity) factory.create(Queries.getLineStat1());
                intent = new Intent(this, LCactivity.getClass());
                intent.putExtra("ChartData", LCactivity.getData().get(0));
                break;
            case CALENDAR:
                gotDestinationIntent = true;
                setContentView(R.layout.activity_calendar);
                intent = new Intent(this, CalendarActivity.class);
                intent.setAction(Intent.ACTION_EDIT);
                break;
            case MAP:
                gotDestinationIntent = true;
                setContentView(R.layout.activity_maps);
                intent = new Intent(this,MapsActivity.class);
                break;
            case KILLAPP:
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
            case LOCATION:
                gotDestinationIntent = true;
                intent = new Intent(this, MyLocation.class);
                break;
            default: break;
        }


        if (gotDestinationIntent) {
            startActivity(intent);
        }
    }


    // All the activities as Enum.

    public enum Activities {
        MAIN,
        CHARTMENU,
        BARCHART,
        GROUPEDBARCHART,
        PIECHART1,
        PIECHART2,
        LINECHART,
        CALENDAR,
        NOTE,
        MAP,
        LOCATION,
        KILLAPP;

        @Override
        public String toString() {
            switch (this) {
                case MAIN:
                    return "Main";
                case CHARTMENU:
                    return "ChartMenu";
                case BARCHART:
                    return "BarChart";
                case GROUPEDBARCHART:
                    return "GroupedBarChart";
                case PIECHART1:
                    return "PieChart1";
                case PIECHART2:
                    return "PieChart2";
                case LINECHART:
                    return "LineChart";
                case CALENDAR:
                    return "Calendar";
                case NOTE:
                    return "Note";
                case LOCATION:
                    return "Location";
                case KILLAPP:
                    return "KillApp";
                default: throw new NoSuchElementException();
            }
        }
    }
}