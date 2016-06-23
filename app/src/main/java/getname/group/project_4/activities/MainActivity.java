package getname.group.project_4.activities;

import android.os.Bundle;
import android.util.Log;

import getname.group.project_4.R;

public class MainActivity extends ActivityExtender {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("[Main]", "Creating Main");
    }
}

