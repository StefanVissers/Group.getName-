package getname.group.project_4.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import getname.group.project_4.R;

public class ChartMenuActivity extends ActivityExtender {
    private static int counter = 0;
    private int ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ID = counter;

        logDebugMessage("CREATE", this);

        setContentView(R.layout.activity_chartmenu);

        LinearLayout layout = (LinearLayout) findViewById(R.id.graphmenu);
    }
}
