package getname.group.project_4;

import android.os.Bundle;
import android.view.View;

import getname.group.project_4.activities.ActivityExtender;

public class MainActivity extends ActivityExtender {
    private static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT",false)) {
            finish();
        }

        ID = counter++;
        super.logDebugMessage("CREATE", this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.logDebugMessage("RESUME", this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        logDebugMessage("BACK_PRESS", this);

        View v = new View(this);
        v.setTag("KILLAPP");
        changeActivity(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.logDebugMessage("DESTROY", this);
        counter = 0;
    }
}

