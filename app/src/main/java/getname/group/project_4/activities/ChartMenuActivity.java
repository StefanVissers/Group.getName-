package getname.group.project_4.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.debug.LogHelper;

public class ChartMenuActivity extends ActivityExtender {
    private static int counter = 0;
    private int ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ID = counter;

        LogHelper.logDebugMessage("CREATE", this);

        setContentView(R.layout.activity_chartmenu);

        LinearLayout layout = (LinearLayout) findViewById(R.id.graphmenu);

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            databaseHelper.executeQuery("SELECT * FROM 'diefstal'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
