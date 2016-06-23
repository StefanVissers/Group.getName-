package getname.group.project_4.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import getname.group.project_4.Chart;

public class ChartActivity extends ActivityExtender {
    private Chart chart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("[ChartAct]", "Creating ChartAct");
    }
}

