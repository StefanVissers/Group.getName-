package getname.group.project_4;

import android.os.Bundle;
import android.util.Log;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;

public class MainActivity extends ActivityExtender {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("[Main]", "Creating Main");
    }
}

