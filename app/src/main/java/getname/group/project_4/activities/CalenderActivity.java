package getname.group.project_4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import java.util.Calendar;

import getname.group.project_4.R;

public class CalenderActivity extends ActivityExtender {
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_activity);
        Log.e("[Calender]", "Creating Calender");
        Log.d(msg, " The onCreate() event");

        send();
    }

    public void send() {
        Calendar cal = Calendar.getInstance();
        Intent intent = getIntent().putExtra("", Intent.ACTION_EDIT);
//        Intent intent =new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "");
//        startActivity(intent);
    }
}
