package getname.group.project_4.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.debug.LogHelper;

public class ChartMenuActivity extends ActivityExtender {

    // Chart menu.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chartmenu);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.graphmenu);
    }
}
