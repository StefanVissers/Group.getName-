package getname.group.project_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by stefan on 21-Jun-16.
 */
public class SecondActivity extends AppCompatActivity {
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);

        Log.d(msg, " The onCreate() event");
    }


}
