package getname.group.project_4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import getname.group.project_4.R;

public class CalenderActivity extends ActivityExtender {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent sendIntent = new Intent(this.getIntent().getAction());
        Calendar cal = Calendar.getInstance();
        sendIntent.setType("vnd.android.cursor.item/event");
        sendIntent.putExtra("beginTime", cal.getTimeInMillis());
        sendIntent.putExtra("allDay", true);
        sendIntent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        sendIntent.putExtra("title", "");
        startActivity(sendIntent);
    }

//    public void send(Intent intent) {
//        Calendar cal = Calendar.getInstance();
//        intent.setType("vnd.android.cursor.item/event");
//        intent.putExtra("beginTime", cal.getTimeInMillis());
//        intent.putExtra("allDay", true);
//        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
//        intent.putExtra("title", "");
//    }

    @Override
    public void onBackPressed() {
        View v = new View(getApplicationContext());
        v.setTag("main");
        changeActivity(v);
    }
}
