package getname.group.project_4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import getname.group.project_4.R;
import getname.group.project_4.activities.ActivityExtender;

public class MainActivity extends ActivityExtender {
    static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getBooleanExtra("EXIT",false)) {
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("[Main]", "Creating Main" + counter++);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("[MAIN]", " Resuming Main");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        View v = new View(this);
        v.setTag("killme");
        changeActivity(v);
    }
}

