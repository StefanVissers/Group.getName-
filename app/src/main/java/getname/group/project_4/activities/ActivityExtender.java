package getname.group.project_4.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import getname.group.project_4.MainActivity;
import getname.group.project_4.charts.BarChartActivity;
import getname.group.project_4.charts.GroupedBarChartActivity;
import getname.group.project_4.charts.LineChartActivity;
import getname.group.project_4.charts.PieChartActivity;
import getname.group.project_4.R;

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
                intent = new Intent(this, BarChartActivity.class);
                break;
            case GROUPEDBARCHART:
                gotDestinationIntent = true;
                intent = new Intent(this, GroupedBarChartActivity.class);
                break;
            case PIECHART1:
                gotDestinationIntent = true;
                intent = new Intent(this, PieChartActivity.class);
                break;
            case PIECHART2:
                gotDestinationIntent = true;
                intent = new Intent(this, PieChartActivity.class);
                break;
            case LINECHART:
                gotDestinationIntent = true;
                intent = new Intent(this, LineChartActivity.class);
                break;
            case CALENDAR:
                gotDestinationIntent = true;
                setContentView(R.layout.activity_calendar);
                intent = new Intent(this, CalendarActivity.class);
                intent.setAction(Intent.ACTION_EDIT);
                break;
            case NOTE:
                gotDestinationIntent = true;
                setContentView(R.layout.activity_calendar);
                intent = new Intent(this, NoteActivity.class);
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
}