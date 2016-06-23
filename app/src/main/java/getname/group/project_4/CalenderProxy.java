package getname.group.project_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by jacob on 23-6-2016.
 */
public class CalenderProxy extends AppCompatActivity {
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_activity );
        Log.d(msg, " The onCreate() event");

        send();
    }

    public void send(){
        Calendar cal = Calendar.getInstance();
        Intent intent = getIntent().putExtra("", Intent.ACTION_EDIT);
//        Intent intent =new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "");
//        startActivity(intent);
    }
}
