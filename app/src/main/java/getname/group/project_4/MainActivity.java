package getname.group.project_4;

import android.os.Bundle;
import android.view.View;
import getname.group.project_4.activities.ActivityExtender;
import getname.group.project_4.debug.LogHelper;

public class MainActivity extends ActivityExtender {

    // Main activity, Main menu.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT",false)) {
            finish();
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {

        View v = new View(this);
        //  Kill the app.
        v.setTag("KILLAPP");
        changeActivity(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

