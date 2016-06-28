package getname.group.project_4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import getname.group.project_4.R;

public class CalendarActivity extends ActivityExtender {
    private static int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ID = counter;
        logDebugMessage("CREATE", this);

        setContentView(R.layout.activity_calendar);
    }

    public void showCalendarMenu(View view) {
        Intent sendIntent = new Intent(getIntent().getAction());
        setContentView(R.layout.activity_calendar);
        Calendar cal = Calendar.getInstance();
        sendIntent.setType("vnd.android.cursor.item/event");
        sendIntent.putExtra("beginTime", cal.getTimeInMillis());
        sendIntent.putExtra("allDay", true);
        sendIntent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        sendIntent.putExtra("title", "");
        startActivity(sendIntent);
    }
}
