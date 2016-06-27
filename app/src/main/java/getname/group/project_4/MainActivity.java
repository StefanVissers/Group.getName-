package getname.group.project_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;

public class MainActivity extends ActivityExtender {
    static int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("[Main]", "Creating Main" + counter++);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

