package getname.group.project_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by stefan on 22-Jun-16.
 */
public class ThirdActivity extends AppCompatActivity {
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);


        Log.d(msg, " The onCreate() event");
    }


}
