package getname.group.project_4.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

//import com.evernote.edam.type.Note;
//import com.evernote.edam.type.Notebook;

import java.util.NoSuchElementException;

import Data.Queries;
import getname.group.project_4.MainActivity;
import getname.group.project_4.charts.BarChartActivity;
import getname.group.project_4.charts.GroupedBarChartActivity;
import getname.group.project_4.charts.LineChartActivity;
import getname.group.project_4.charts.PieChartActivity;
import getname.group.project_4.R;
import getname.group.project_4.charts.factory.BarChartFactory;
import getname.group.project_4.charts.factory.GroupedBarChartFactory;
import getname.group.project_4.charts.factory.LineChartFactory;
import getname.group.project_4.charts.factory.PieChartFactory;


public abstract class ActivityExtender extends AppCompatActivity {
    protected int ID;

    public int getID() {
        return ID;
    }

    /**
     * Finds new Activity according to tag attached to view and switches to it.
     * @param view
     */
    public void changeActivity(View view) {
        Intent intent = new Intent();
        boolean gotDestinationIntent = false;
//        Queries queries = new Data.Queries();

        BarChartFactory barChartFactory;
        GroupedBarChartFactory groupedBarChartFactory;
        PieChartFactory pieChartFactory;
        LineChartFactory lineChartFactory;

        BarChartActivity barChart;
        GroupedBarChartActivity groupedBarChartActivity;
        PieChartActivity pieChart;
        LineChartActivity lineChart;



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
                barChartFactory = new BarChartFactory();
                barChart = barChartFactory.create(Queries.getBarStat1());
                intent = new Intent(this, barChart.getClass());
                intent.putExtra("ChartData", barChart.getData());
                break;
            case GROUPEDBARCHART:
                gotDestinationIntent = true;
                groupedBarChartFactory = new GroupedBarChartFactory();
                groupedBarChartActivity = groupedBarChartFactory.create(Queries.getGroupedBarStat1());
                intent = new Intent(this, groupedBarChartActivity.getClass());
                intent.putExtra("ChartData", groupedBarChartActivity.getData());
                break;
            case PIECHART1:
                gotDestinationIntent = true;
                pieChartFactory = new PieChartFactory();
                pieChart = pieChartFactory.create(Queries.getPieStat1());
                intent = new Intent(this, pieChart.getClass());
                intent.putExtra("ChartData", pieChart.getData());
                break;
            case PIECHART2:
                gotDestinationIntent = true;
                pieChartFactory = new PieChartFactory();
                pieChart = pieChartFactory.create(Queries.getPieStat2());
                intent = new Intent(this, pieChart.getClass());
                intent.putExtra("ChartData", pieChart.getData());
                break;
            case LINECHART:
                gotDestinationIntent = true;
                lineChartFactory = new LineChartFactory();
                lineChart = lineChartFactory.create(Queries.getLineStat1(2012, Queries.RelativeTime.CURRENT));
                intent = new Intent(this, lineChart.getClass());
                intent.putExtra("ChartData", lineChart.getData());
                break;
            case CALENDAR:
                gotDestinationIntent = true;
                setContentView(R.layout.activity_calendar);
                intent = new Intent(this, CalendarActivity.class);
                intent.setAction(Intent.ACTION_EDIT);
                break;
            case KILLAPP:
                gotDestinationIntent = true;
                intent = getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
            case LOCATION:
                gotDestinationIntent = true;
                intent = new Intent(this, MyLocation.class);
                break;
            default: break;
        }


        if (!gotDestinationIntent) {
            Log.d("[Main.changeActivity()]", "Didn't get destination intent");
        } else {
            Log.d("[Main.changeActivity()]", "Got destination intent" + intent.toString());
            startActivity(intent);
        }
    }


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