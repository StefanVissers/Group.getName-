package getname.group.project_4;

import android.os.Bundle;
import android.widget.LinearLayout;

public class ChartMenuActivity extends ActivityExtender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);

        LinearLayout layout = (LinearLayout) findViewById(R.id.graphmenu);
    }
}
