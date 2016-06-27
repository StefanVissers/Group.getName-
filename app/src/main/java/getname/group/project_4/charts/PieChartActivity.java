package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import getname.group.project_4.activities.ActivityExtender;

public class PieChartActivity extends ActivityExtender implements Chart {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
    }

    @Override
    public void addData(ChartData cd) {
        // TODO: Implement me
    }
}
