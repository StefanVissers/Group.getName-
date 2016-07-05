package getname.group.project_4.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.List;

import getname.group.project_4.R;
import getname.group.project_4.debug.LogHelper;

public class CalendarActivity extends ActivityExtender {
    private static int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // debug message
        ID = counter;
        LogHelper.logDebugMessage("CREATE", this);

        // set activity to activity_calendar
        setContentView(R.layout.activity_calendar);
    }

    public void showCalendarMenu(View view) {
        // Creating new Intent with the action given from ActivityExtender
        Intent sendIntent = new Intent(getIntent().getAction());

        // Get Calendar Instance
        Calendar cal = Calendar.getInstance();
        sendIntent.setType("vnd.android.cursor.item/event");

        // Creating user input for time and titel
        sendIntent.putExtra("beginTime", cal.getTimeInMillis());
        sendIntent.putExtra("allDay", true);
        sendIntent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        sendIntent.putExtra("title", "");
        startActivity(sendIntent);
    }
}