package getname.group.project_4.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import getname.group.project_4.R;

public class ChartMenuActivity extends ActivityExtender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("[chartmenu]", "Creating chartmenu");

        setContentView(R.layout.fourth_activity);

        LinearLayout layout = (LinearLayout) findViewById(R.id.graphmenu);
    }
}
