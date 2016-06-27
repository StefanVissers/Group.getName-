package getname.group.project_4.charts;


import android.os.Bundle;
import android.support.annotation.Nullable;

import getname.group.project_4.activities.ActivityExtender;

public class GroupedBarChartActivity extends ActivityExtender implements Chart {
    private BarChartActivity[] charts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void addData(ChartData cd) {

    }
}
