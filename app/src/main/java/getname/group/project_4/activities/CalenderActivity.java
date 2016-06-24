package getname.group.project_4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import getname.group.project_4.R;

public class CalenderActivity extends ActivityExtender {

    public void send(Intent intent) {
        Calendar cal = Calendar.getInstance();
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "");
    }
    @Override
    public void onBackPressed() {
        View v = new View(getApplicationContext());
        v.setTag("main");
        changeActivity(v);
    }
}
