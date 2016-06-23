package getname.group.project_4;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;

public abstract class ActivityExtender extends AppCompatActivity{
    public void changeActivity(View view) {
        Intent intent = new Intent();
        boolean gotDestinationIntent = false;

        /**
         * tag is an array of Strings, which represent values used with the view
         */

        String[] tag = view.getTag().toString().split(",");
        Activities destination = Activities.valueOf(tag[0]);

        switch (destination) {
            case MAIN:
                gotDestinationIntent = true;
                intent = new Intent(this, MainActivity.class);
                break;
            case CHARTMENU:
                gotDestinationIntent = true;
                intent = new Intent(this, ChartActivity.class);
                break;
            case CHART1:
                gotDestinationIntent = true;
                intent = new Intent(this, SecondActivity.class);
                break;
            case CHART2:
                gotDestinationIntent = true;
                intent = new Intent(this, ThirdActivity.class);
                break;
            case CHART3: break;
            case CHART4: break;
            case CHART5: break;
            case CHART6: break;
            case CHART7: break;
            case CHART8: break;
            case CALENDER:
                gotDestinationIntent = true;
                intent = new Intent(this, CalenderActivity.class);
                break;
            case NOTE: break;
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