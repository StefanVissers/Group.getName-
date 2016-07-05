package getname.group.project_4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import getname.group.project_4.debug.LogHelper;

import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.debug.LogHelper;

public class MainActivity extends ActivityExtender {
    private static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT",false)) {
            finish();
        }

        ID = counter++;
        LogHelper.logDebugMessage("CREATE", this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogHelper.logDebugMessage("RESUME", this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
       LogHelper.logDebugMessage("BACK_PRESS", this);

        View v = new View(this);
        //  Kill the app.
        v.setTag("KILLAPP");
        changeActivity(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogHelper.logDebugMessage("DESTROY", this);
        counter = 0;
    }
}

