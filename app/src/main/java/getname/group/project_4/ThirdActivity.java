package getname.group.project_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by stefan on 22-Jun-16.
 */
public class ThirdActivity extends AppCompatActivity {
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "");
        startActivity(intent);

        Log.d(msg, " The onCreate() event");
    }


}
