package getname.group.project_4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import getname.group.project_4.R;

public class ThirdActivity extends ActivityExtender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);

        Log.e("[Thrid]", "Creating Third");

        setContentView(R.layout.third_activity);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.third_activity);
    }
}
